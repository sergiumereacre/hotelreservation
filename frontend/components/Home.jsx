       "use client"
       import { useState } from 'react';
       import GuestList from "@/pages/Guests";
       import StaffManagement from "@/pages/StaffManagement";  // Import the new component
       import Link from 'next/link';

       export default function Home() {
       //  const [showStaffManagement, setShowStaffManagement] = useState(false);  // State to toggle staff management

     return (
         <main className="flex min-h-screen flex-col items-center justify-between p-24">
             <div className="flex flex-col gap-2 items-center">
             <Link href="/Guests">
                 <button onClick={() => setShowGuestForm(true)} className="bg-blue-400 p-2 rounded-lg">
                     Guest Login
                 </button>
             </Link>
              <Link href="/StaffManagement">
                             <button className="bg-blue-500 p-2 rounded-lg">
                                 Staff Login
                             </button>
               </Link>
             </div>
         </main>
     );
     }
