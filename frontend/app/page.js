import GuestList from "@/components/Guests"
import StaffList from "@/components/Staff"

export default function Home() {
  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-24">

      <div className="flex flex-col gap-2 items-center">
        <p>Test Test Test literally kill me its not as slow as he says</p>
        <p>why the fuck is it not sadddsdsdsdksdkjsdd takes liek a second but it works</p>
        <GuestList />
        <StaffList />
      </div>

    </main>
  )
}
