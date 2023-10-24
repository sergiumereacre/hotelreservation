
import Home from "@/components/Home";

export default function MainPage() {
  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-24">
      <Home />  {/* includes Home component and all its contents */}
    </main>
  );
}
