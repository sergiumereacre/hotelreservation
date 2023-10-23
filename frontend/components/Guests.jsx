"use client"

import { useState, useEffect } from "react";

function GuestList(){
    const [guests, setGuests] = useState([]);
    const [selectedGuest, setSelectedGuest] = useState(null);
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');

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

        return (
            <div>
                {guests.length === 0 && <button onClick={fetchData} className="bg-blue-400 p-2 rounded-lg">Fetch Guests</button>}
                {guests.length > 0 &&
                    <ul className="flex flex-col items-center bg-slate-800 rounded-lg p-5">
                        {guests.map(guest => (
                            <li key={guest.id}>
                                {guest.name} - {guest.email}
                                <button onClick={() => setSelectedGuest(guest)}>Edit</button>
                                <button onClick={() => handleDeleteGuest(guest.id)}>Delete</button>
                            </li>
                        ))}
                    </ul>
                }
                <div>
                    <input value={name} onChange={(e) => setName(e.target.value)} placeholder="Guest Name" />
                    <input value={email} onChange={(e) => setEmail(e.target.value)} placeholder="Guest Email" />
                    {selectedGuest ? (
                        <button onClick={() => handleUpdateGuest(selectedGuest.id)}>Update Guest</button>
                    ) : (
                        <button onClick={handleCreateGuest}>Add Guest</button>
                    )}
                </div>
            </div>
        );
    };

    export default GuestList;