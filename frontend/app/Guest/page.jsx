"use client"
import { useState } from "react";

export default function Guest() {
    const [guests, setGuests] = useState([]);
    const [selectedGuest, setSelectedGuest] = useState(null);
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [showLoginForm, setShowLoginForm] = useState(false);
    const [showRegisterForm, setShowRegisterForm] = useState(false);

    const fetchData = async () => {
        try {
            const response = await fetch("/api/guests");
            const guests = await response.json();
            setGuests(guests);
        } catch (error) {
            console.log(error);
        }
    };

    const handleCreateGuest = async () => {
        // Assuming email is unique, add validation as needed
        try {
            const response = await fetch("/api/guests", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ name, email })
            });
            if (response.ok) {
                fetchData(); // refresh list
            }
        } catch (error) {
            console.log(error);
        }
    };

    const handleUpdateGuest = async (id) => {
        try {
            const response = await fetch(`/api/guests/${id}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ name, email }) // update name and email
            });
            if (response.ok) {
                fetchData(); // refresh list
                setSelectedGuest(null); // deselect
            }
        } catch (error) {
            console.log(error);
        }
    };

    const handleDeleteGuest = async (id) => {
        try {
            const response = await fetch(`/api/guests/${id}`, {
                method: "DELETE",
            });
            if (response.ok) {
                fetchData(); // refresh list
            }
        } catch (error) {
            console.log(error);
        }
    };

    const handleGuestLogin = async () => {
        try {
            const response = await axios.post('/api/guests/login', { email, password });
            if (response.data.isAuthenticated) {

            } else {
                setLoginError('Incorrect email or password.');
            }
        } catch (error) {
            console.error('An error occurred during login:', error);
            setLoginError('An error occurred. Please try again.');
        }
    };

    return (
        <>
            <main className="flex flex-grow flex-col items-center justify-center bg-white gap-5">
                {showLoginForm && (
                    <div className="flex flex-col gap-2 mt-4">
                        <input placeholder="Email" className="p-2 rounded-md" />
                        <input type="password" placeholder="Password" className="p-2 rounded-md" />
                        <button className="bg-green-400 p-2 rounded-lg mt-2">Login</button>
                    </div>
                )}

                {showRegisterForm && (
                    <div className="flex flex-col gap-2 mt-4">
                        <input placeholder="Name" className="p-2 rounded-md" />
                        <input placeholder="Email" className="p-2 rounded-md" />
                        <input type="password" placeholder="Password" className="p-2 rounded-md" />
                        <button className="bg-green-400 p-2 rounded-lg mt-2">Sign Up</button>
                    </div>
                )}

                <div className="flex flex-row gap-5">
                    <button
                        onClick={() => { setShowLoginForm(true); setShowRegisterForm(false); }}
                        className="bg-blue-400 p-2 rounded-lg"
                    >
                        Guest Login
                    </button>
                    <button
                        onClick={() => { setShowRegisterForm(true); setShowLoginForm(false); }}
                        className="bg-yellow-400 p-2 rounded-lg"
                    >
                        Register
                    </button>
                </div>
            </main>
        </>
    );
}
