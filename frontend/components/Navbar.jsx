'use client';
import { Button, Navbar } from 'flowbite-react';
import Hotel from '@/public/hotel.png';
import Image from 'next/image';
import { usePathname } from 'next/navigation';

export default function NavbarWithCTAButton() {
    const pathname = usePathname();

    return (
        <div className="min-w-full">
            <Navbar fluid>
                <Navbar.Brand href="/" className='gap-2'>
                    <Image src={Hotel} alt="Hotel Logo" width={50} height={50} />
                    <span className="py-2 self-center whitespace-nowrap text-xl font-bold">HRS</span>
                </Navbar.Brand>
                <div className="flex md:order-2 gap-4">
                    <Button>Book a Room</Button>
                    <Navbar.Toggle />
                </div>
                <Navbar.Collapse>
                    <Navbar.Link active={pathname === '/'} className="font-semibold" href="/">
                        Home
                    </Navbar.Link>
                    <Navbar.Link active={pathname === '/ttd'} className="font-semibold" href="/ttd">
                        Things To Do
                    </Navbar.Link>
                    <Navbar.Link active={pathname === '/rooms'} className="font-semibold" href="/rooms">
                        Rooms
                    </Navbar.Link>
                    <Navbar.Link active={pathname === '/loyalty'} className="font-semibold" href="/loyalty">
                        Loyalty
                    </Navbar.Link>
                    <Navbar.Link active={pathname === '/staff'} className="font-semibold" href="/staff">
                        Staff
                    </Navbar.Link>
                    <Navbar.Link active={pathname === '/guest'} className="font-semibold" href="/guest">
                        Guest
                    </Navbar.Link>
                </Navbar.Collapse>
            </Navbar>
        </div>
    )
}


