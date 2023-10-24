"use client"

import { useState } from "react";

function StaffList() {
    const [staff, setStaff] = useState([]);

    const fetchData = async () => {
        try {
            console.log(process.env.NEXT_PUBLIC_ADMIN_USERNAME, process.env.NEXT_PUBLIC_ADMIN_PASSWORD)
            const response = await fetch("/api/staff", {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": "Basic " + btoa(process.env.NEXT_PUBLIC_ADMIN_USERNAME + ":" + process.env.NEXT_PUBLIC_ADMIN_PASSWORD),
                },
            });
            const staff = await response.json();
            setStaff(staff);
        } catch (error) {
            console.log(error);
        }
    };

    return (
        <div>
            {staff.length === 0 && <button onClick={fetchData} className="bg-blue-400 p-2 rounded-lg">Fetch Staff</button>}
            {staff.length > 0 &&
                <ul className="flex flex-col items-center bg-slate-800 rounded-lg p-5">
                    {staff.map(staff => (
                        <li key={staff.id}>{staff.name} - {staff.email}</li>
                    ))}
                </ul>
            }
        </div>
    );
};

export default StaffList;