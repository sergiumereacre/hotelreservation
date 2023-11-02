"use client"
import { useState, useEffect } from "react";

export default function Guest() {
    const [guests, setGuests] = useState([]);
    const [selectedGuest, setSelectedGuest] = useState(null);
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [showLoginForm, setShowLoginForm] = useState(false);
    const [showRegisterForm, setShowRegisterForm] = useState(false);
    const [password, setPassword] = useState('');
    const [loginError, setLoginError] = useState('');
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [showManageAccount, setShowManageAccount] = useState(false);
    const [currentGuest, setCurrentGuest] = useState(null);
    const [accountCreated, setAccountCreated] = useState(false);
    const [loyaltyStatus, setLoyaltyStatus] = useState('');

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
        try {
            const response = await fetch("/api/guests", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ name, email, password })
            });
            if (response.ok) {
                fetchData();
                setAccountCreated(true);
            }
        } catch (error) {
            console.log(error);
        }
    };

    // Fetch loyalty function that calls the loyalty API with the passed in ID.
    const fetchLoyaltyStatus = async (id) => {
        try {
            const response = await fetch(`/api/loyalty/${id}`);
            // Response should be a string of the loyalty status.
            const loyalty = await response.text();
            setLoyaltyStatus(loyalty);
        } catch (error) {
            console.log(error);
        }
    }

    const handleUpdateGuest = async () => {
        try {
            const response = await fetch(`/api/guests/${currentGuest}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ email, password }) // update password and email
            });
            if (response.ok) {
                fetchData(); // refresh list

            }
        } catch (error) {
            console.log(error);
        }
    };

    const handleDeleteGuest = async () => {
        try {
            const response = await fetch(`/api/guests/${currentGuest}`, {
                method: "DELETE",
            });
            if (response.ok) {
                fetchData(); // refresh list
                setIsLoggedIn(false); // Log out the user since their account is deleted
                setCurrentGuest(null); // Clear current guest ID
            }
        } catch (error) {
            console.log(error);
        }
    };

    const handleGuestLogin = async () => {
        try {
            const response = await fetch('/api/guests/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ email, password })
            });

            if (response.ok) {
                const data = await response.json();
                if (data.isAuthenticated) {
                    // Handle successful login if needed
                    setIsLoggedIn(true);
                    setCurrentGuest(data.id);
                    fetchLoyaltyStatus(data.id);
                } else {
                    setLoginError('Incorrect email or password.');
                }
            } else {
                setLoginError('An error occurred. Please try again.');
            }
        } catch (error) {
            console.error('An error occurred during login:', error);
            setLoginError('An error occurred. Please try again.');
        }
    };



    return (
        <>
            <main className="flex flex-grow flex-col items-center justify-center bg-white gap-5">
                {!isLoggedIn ? (
                    <>
                        {accountCreated && (
                            <div className="text-green-500 mb-4">
                                Account successfully created! Please log in.
                            </div>
                        )}

                        {showLoginForm && (
                            <div className="flex flex-col gap-2 mt-4">
                                <input
                                    placeholder="Email"
                                    value={email}
                                    onChange={(e) => setEmail(e.target.value)}
                                    className="p-2 rounded-md"
                                />
                                <input
                                    type="password"
                                    placeholder="Password"
                                    value={password}
                                    onChange={(e) => setPassword(e.target.value)}
                                    className="p-2 rounded-md"
                                />
                                <button
                                    onClick={handleGuestLogin}
                                    className="bg-green-400 p-2 rounded-lg mt-2"
                                >
                                    Login
                                </button>
                            </div>
                        )}

                        {showRegisterForm && (
                            <div className="flex flex-col gap-2 mt-4">
                                <input
                                    placeholder="Name"
                                    value={name}
                                    onChange={(e) => setName(e.target.value)}
                                    className="p-2 rounded-md"
                                />
                                <input
                                    placeholder="Email"
                                    value={email}
                                    onChange={(e) => setEmail(e.target.value)}
                                    className="p-2 rounded-md"
                                />
                                <input
                                    type="password"
                                    placeholder="Password"
                                    value={password}
                                    onChange={(e) => setPassword(e.target.value)}
                                    className="p-2 rounded-md"
                                />
                                <button
                                    onClick={handleCreateGuest}
                                    className="bg-green-400 p-2 rounded-lg mt-2"
                                >
                                    Sign Up
                                </button>
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
                    </>
                ) : (
                    <>
                        <div className="flex flex-col gap-5 mt-4">
                            <div className="text-center">
                                <p className="text-gray-700">Logged in as {email}</p>
                                <p className="text-gray-700">Loyalty Status: {loyaltyStatus}</p>
                            </div>
                            <button
                                onClick={() => setShowManageAccount(true)}
                                className="bg-blue-400 p-2 rounded-lg"
                            >
                                Manage Account
                            </button>
                            <button className="bg-green-400 p-2 rounded-lg">Manage Booking</button>
                            <button className="bg-yellow-400 p-2 rounded-lg">Make Reservation</button>
                            <button
                                onClick={() => {
                                    setIsLoggedIn(false);
                                    setShowManageAccount(false);
                                }}
                                className="bg-red-400 p-2 rounded-lg mt-2"
                            >
                                Back
                            </button>
                        </div>

                        {showManageAccount && (
                            <div className="flex flex-col gap-5 mt-4">
                                <input
                                    placeholder="Email"
                                    value={email}
                                    onChange={(e) => setEmail(e.target.value)}
                                    className="p-2 rounded-md"
                                />
                                <input
                                    type="password"
                                    placeholder="Password"
                                    value={password}
                                    onChange={(e) => setPassword(e.target.value)}
                                    className="p-2 rounded-md"
                                />
                                <button
                                    onClick={handleUpdateGuest}
                                    className="bg-blue-500 p-2 rounded-lg mt-2"
                                >
                                    Update Details
                                </button>
                                <button
                                    onClick={handleDeleteGuest}
                                    className="bg-red-500 p-2 rounded-lg mt-2"
                                >
                                    Delete Account
                                </button>
                            </div>
                        )}
                    </>
                )}

                {loginError && <p className="text-red-500">{loginError}</p>}
            </main>
        </>
    );

}
