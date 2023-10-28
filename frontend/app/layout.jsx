import { Inter } from 'next/font/google'
import './globals.css'
import { Providers } from '@/components/Providers'
import NavbarWithCTAButton from '@/components/Navbar'

const inter = Inter({ subsets: ['latin'] })

export const metadata = {
  title: 'Hotel Reservation System',
  description: 'A hotel reservation system built with Next.js and Java Spring Boot.',
}

export default function RootLayout({ children }) {
  return (
    <html lang="en">
      <body className={inter.className}>
        <Providers>
          <div className='max-w-4xl flex-col flex m-auto min-h-screen'>
            <NavbarWithCTAButton />
            {children}
          </div>
        </Providers>
      </body>
    </html>
  )
}
