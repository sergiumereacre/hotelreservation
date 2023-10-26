

 import Link from 'next/link';
export default function MainPage() {
  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-24">
                  <div className="flex flex-col gap-2 items-center">
                  <Link href="/Guest">
                      <button  className="bg-blue-400 p-2 rounded-lg">
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
