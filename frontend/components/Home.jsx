       "use client"
       import { useState } from 'react';
       import GuestList from "@/components/Guests";
       import StaffManagement from "@/components/StaffManagement";  // Import the new component

       export default function Home() {
         const [showGuestForm, setShowGuestForm] = useState(false);
         const [showRegisterForm, setShowRegisterForm] = useState(false);
         const [showStaffManagement, setShowStaffManagement] = useState(false);  // State to toggle staff management

         return (
           <main className="flex min-h-screen flex-col items-center justify-between p-24">
             <div className="flex flex-col gap-2 items-center">
               <button onClick={() => setShowGuestForm(true)} className="bg-blue-400 p-2 rounded-lg">
                 Guest Login
               </button>

               {showGuestForm && (
                 <div className="flex flex-col gap-2 mt-4">
                   <input placeholder="Email" className="p-2 rounded-md" />
                   <input type="password" placeholder="Password" className="p-2 rounded-md" />
                   <button className="bg-green-400 p-2 rounded-lg mt-2">Login</button>
                   <button onClick={() => setShowRegisterForm(true)} className="bg-yellow-400 p-2 rounded-lg mt-2">
                     Register
                   </button>

                   {showRegisterForm && (
                     <div className="flex flex-col gap-2 mt-4">
                       <input placeholder="Name" className="p-2 rounded-md" />
                       <input placeholder="Email" className="p-2 rounded-md" />
                       <input type="password" placeholder="Password" className="p-2 rounded-md" />
                       <button className="bg-green-400 p-2 rounded-lg mt-2">Sign Up</button>
                     </div>
                   )}
                 </div>
               )}

               <button onClick={() => setShowStaffManagement(true)} className="bg-red-400 p-2 rounded-lg mt-4">
                 Staff Login
               </button>

               {showStaffManagement && <StaffManagement />}  {/* Render the Staff Management component when button is clicked */}
             </div>
           </main>
         );
       }