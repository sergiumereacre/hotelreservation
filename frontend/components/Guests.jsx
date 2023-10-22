"use client"

import { useState, useEffect } from "react";

function GuestList(){
    const [guests, setGuests] = useState([]);
    
    const fetchData = async () => {
        try {
            const response = await fetch("/api/guests", {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                },
            });
            const guests = await response.json();
            setGuests(guests);
            console.log("fetched users.")
        } catch (error) {
            console.log(error);
        }
    };

    return (
        <div>
            {guests.length === 0 && <button onClick={fetchData} className="bg-blue-400 p-2 rounded-lg">Fetch Users</button>}
            {guests.length > 0 &&
                <ul className="flex flex-col items-center bg-slate-800 rounded-lg p-5">
                    {guests.map(guest => (
                        <li key={guest.id}>{guest.name} - {guest.email}</li>
                    ))}
                </ul>
            }
        </div>
    );
};

export default GuestList;