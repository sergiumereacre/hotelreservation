import GuestList from "@/components/Guests"
import StaffList from "@/components/Staff"
import Link from "next"

export default function Home() {
  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-24">

      <div className="flex flex-col gap-2 items-center">
        <GuestList />
        <StaffList />
      </div>

    </main>
  )
}
