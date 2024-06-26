"use client"
import { useState, useEffect } from "react";

export default function Staff() {

    const [isAdmin, setIsAdmin] = useState(false);
    const [showStaffOptions, setShowStaffOptions] = useState(false);
    const [loggedIn, setLoggedIn] = useState(false);
    const [showLoginForm, setShowLoginForm] = useState(false);
    const [allStaff, setAllStaff] = useState([]);
    const [staffList, setStaffList] = useState([]);
    const [currentStaff, setCurrentStaff] = useState({});

    const [actionType, setActionType] = useState(null);  // 'add', 'update', or 'delete'
    const [staffData, setStaffData] = useState({
        name: '',
        email: '',
        password: '',
        role: ''
    });
    const [staffId, setStaffId] = useState(null);
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [loginError, setLoginError] = useState('');
    const [userRole, setUserRole] = useState('');


    const handleInputChange = (event) => {
        const { name, value } = event.target;
        setStaffData(prevState => ({ ...prevState, [name]: value }));
    };

    const handleLogin = async () => {
        try {
            const response = await fetch("/api/staff/login", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ email, password })
            });


            const data = await response.json();

            if (data.isAuthenticated) {
                setLoggedIn(true);
                setUserRole(data.role);
                setCurrentStaff(data.id);
                setLoginError('');

            } else {
                setLoginError('Incorrect email or password.');
            }
        } catch (error) {
            console.error('An error occurred during login:', error);
            setLoginError('An error occurred. Please try again.');
        }
    };


    const addStaff = async () => {
        try {
            // Check if the user has admin permissions
            if (userRole !== 'Admin') {
                alert('Unauthorized access: You do not have permission to add staff.');
                return;
            }

            const response = await fetch("/api/staff", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(staffData),
            });
            // Only attempt to parse JSON if response is not empty
            const contentType = response.headers.get("content-type");
            let result = {};
            if (contentType && contentType.indexOf("application/json") !== -1) {
                result = await response.json();
            }

            if (response.ok) {
                const savedStaff = await response.json();
                console.log("Staff added successfully!", savedStaff);

                // Reset staffData AFTER successfully adding the staff
                setStaffData({
                    name: '',
                    email: '',
                    password: '',
                    role: ''
                });

                // Fetch all staff after the addition
                fetchAllStaff();
            } else {
                console.error("Failed to add staff:", response.statusText);
            }
        } catch (error) {
            console.error(error);
        }
    };

    const fetchAllStaff = async () => {
        try {
            const response = await fetch("/api/staff", {
                method: "GET",
                headers: {
                    "Content-Type": "application/json"
                }
            });

            if (response.ok) {
                const staffList = await response.json();
                setStaffList(staffList);
            } else {
                console.error("Failed to fetch staff:", response.statusText);
            }
        } catch (error) {
            console.error(error);
        }
    };

    const updateSelfAccount = async () => {
        try {
            const response = await fetch(`/api/staff/${currentStaff}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(staffData)
            });

            if (response.ok) {
                alert("Account updated successfully!");
            } else {
                console.error("Failed to update account");
            }
        } catch (err) {
            console.error("Error updating account:", err);
        }
    };


    const fetchStaffById = async (id) => {
        try {
            const response = await fetch(`/api/staff/${id}`);
            if (response.ok) {
                const data = await response.json();
                if (!data) {
                    alert("No staff found with the provided ID.");
                    return;
                }
                setStaffData(data);
            } else {
                console.error("Failed to fetch staff details");
                alert("Failed to fetch staff details. Please ensure the ID is correct.");
            }
        } catch (err) {
            console.error("Error fetching staff details:", err);
            alert("Error fetching staff details. Please try again later.");
        }
    };



    const handleUpdateClick = () => {
        const id = prompt("Please enter the staff ID:");
        if (id) {
            setStaffId(id);
            fetchStaffById(id);
            setActionType('update');
        }
    };

    const handleSaveUpdate = async () => {
        // Check if the user has admin permissions
        if (userRole !== 'Admin') {
            alert('Unauthorized access: You do not have permission to update staff details.');
            return;
        }

        console.log("Sending update for staff:", staffData);

         try {
                const response = await fetch(`/api/staff/${staffId}`, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(staffData)
                });

                const text = await response.text();  // Get the response as text

                if (response.ok) {
                    if (!text) {
                        console.error("Empty response from the server.");
                        return;
                    }

                    const responseData = JSON.parse(text);


                    if (responseData.name !== staffData.name || responseData.email !== staffData.email) {
                        console.error("Updated data on the server doesn't match the sent data.");
                        return;
                    }

                } else {
                    console.error("Failed to update staff details.");
                }
            } catch (err) {
                console.error("Error updating staff details:", err);
            }
        };

    const handleActionConfirm = () => {
        switch (actionType) {
            case 'add':
                addStaff();
                break;
            case 'update':
                handleSaveUpdate();
                break;
            case 'updateSelf':
                updateSelfAccount();
                break;
            default:
                break;
        }
    };

    const handleDeleteStaff = async (id) => {
        // Check if the user has admin permissions
        if (userRole !== 'Admin') {
            alert('Unauthorized access: You do not have permission to delete staff.');
            return;
        }

        try {
            const response = await fetch(`/api/staff/${id}`, {
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json",
                },
            });

            if (response.ok) {
                alert("Staff deleted successfully!");
                fetchAllStaff();  // Refresh the staff list
            } else {
                console.error("Failed to delete staff:", response.statusText);
            }
        } catch (error) {
            console.error(error);
        }
    };

    return (
        <>
            <main className="flex flex-grow items-center justify-center bg-white">
                <div className="flex flex-col gap-5">
                    {!loggedIn ? (
                        !showLoginForm ? (
                            <>
                                <input
                                    type="text"
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
                                <button onClick={handleLogin} className="bg-blue-500 p-2 rounded-lg">Confirm</button>
                                {loginError && <p className="text-red-500">{loginError}</p>}
                            </>
                        ) : null
                    ) : (
                        <>
                            <button
                                onClick={() => {
                                    if (userRole === 'Admin') {
                                        setIsAdmin(true);
                                        fetchAllStaff(); // Fetch all staff members if the user is an admin
                                    } else {
                                        setIsAdmin(false); // Set isAdmin to false if the user is not an admin
                                        alert('Unauthorized access: You do not have permission to manage staff.');
                                    }
                                }}
                                className="bg-blue-500 p-2 rounded-lg"
                            >
                                Manage Staff
                            </button>

                            {loggedIn && !isAdmin && (
                                <button
                                    onClick={() => {
                                        setActionType('updateSelf');
                                        fetchStaffById(currentStaff); // fetch the current staff's details
                                    }}
                                    className="bg-green-500 p-2 rounded-lg"
                                >
                                    Update Account
                                </button>
                            )}

                            {isAdmin && (
                                <div className="space-y-4">
                                    <div className="flex space-x-4">
                                        <button
                                            onClick={() => setActionType('add')}
                                            className="bg-yellow-500 p-2 rounded-lg"
                                        >
                                            Add Staff
                                        </button>
                                        <button
                                            onClick={handleUpdateClick}
                                            className="bg-orange-500 p-2 rounded-lg"
                                        >
                                            Update Staff
                                        </button>
                                    </div>

                                    {/* Add Staff Section */}
                                    {actionType === 'add' && (
                                        <>
                                            <div className="space-y-4">
                                                <input
                                                    name="name"
                                                    placeholder="Name"
                                                    value={staffData.name}
                                                    onChange={handleInputChange}
                                                    className="p-2 rounded-md"
                                                />
                                                <input
                                                    name="email"
                                                    placeholder="Email"
                                                    value={staffData.email}
                                                    onChange={handleInputChange}
                                                    className="p-2 rounded-md"
                                                />
                                                <input
                                                    name="password"
                                                    type="password"
                                                    placeholder="Password"
                                                    value={staffData.password}
                                                    onChange={handleInputChange}
                                                    className="p-2 rounded-md"
                                                />
                                                <input
                                                    name="role"
                                                    placeholder="Role"
                                                    value={staffData.role}
                                                    onChange={handleInputChange}
                                                    className="p-2 rounded-md"
                                                />
                                            </div>
                                            <button onClick={handleActionConfirm} className="bg-green-500 p-2 rounded-lg">
                                                Confirm
                                            </button>
                                        </>
                                    )}

                                    {/* Update Staff Section */}
                                    {actionType === 'update' && (
                                        <>
                                            <div className="space-y-4">
                                                <input
                                                    name="name"
                                                    placeholder="Name"
                                                    value={staffData.name}
                                                    onChange={handleInputChange}
                                                    className="p-2 rounded-md"
                                                />
                                                <input
                                                    name="email"
                                                    placeholder="Email"
                                                    value={staffData.email}
                                                    onChange={handleInputChange}
                                                    className="p-2 rounded-md"
                                                />
                                                <input
                                                    name="password"
                                                    type="password"
                                                    placeholder="Password"
                                                    value={staffData.password}
                                                    onChange={handleInputChange}
                                                    className="p-2 rounded-md"
                                                />
                                                <input
                                                    name="role"
                                                    placeholder="Role"
                                                    value={staffData.role}
                                                    onChange={handleInputChange}
                                                    className="p-2 rounded-md"
                                                />
                                            </div>
                                            <button onClick={handleActionConfirm} className="bg-green-500 p-2 rounded-lg">
                                                Confirm Update
                                            </button>
                                        </>
                                    )}

                                    {/* Display All Staff Section */}
                                    {staffList && staffList.length > 0 && (
                                        <div className="mt-4">
                                            <ul className="space-y-2">
                                                {staffList.map(staff => (
                                                    <li key={staff.id} className="flex justify-between items-center border p-2 rounded-md">
                                                        <span>{staff.name} - {staff.email}</span>
                                                        <button
                                                            onClick={() => handleDeleteStaff(staff.id)}
                                                            className="bg-red-500 p-2 rounded-lg"
                                                        >
                                                            Delete
                                                        </button>
                                                    </li>
                                                ))}
                                            </ul>
                                        </div>
                                    )}
                                </div>
                            )}
                            {/* Update Self Account Section */}
                            {actionType === 'updateSelf' && (
                                <>
                                    <div className="space-y-4">
                                        <input
                                            name="name"
                                            placeholder="Name"
                                            value={staffData.name}
                                            onChange={handleInputChange}
                                            className="p-2 rounded-md"
                                        />
                                        <input
                                            name="email"
                                            placeholder="Email"
                                            value={staffData.email}
                                            onChange={handleInputChange}
                                            className="p-2 rounded-md"
                                        />
                                        <input
                                            name="password"
                                            type="password"
                                            placeholder="Password"
                                            value={staffData.password}
                                            onChange={handleInputChange}
                                            className="p-2 rounded-md"
                                        />
                                        {/* Role input removed here since staff shouldn't be able to change their own role */}
                                    </div>
                                    <button onClick={handleActionConfirm} className="bg-green-500 p-2 rounded-lg">
                                        Confirm Update
                                    </button>
                                </>
                            )}
                        </>
                    )}
                </div>
            </main>
        </>
    );
}
