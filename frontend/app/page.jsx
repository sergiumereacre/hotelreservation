export default function Home() {
    return (
        <>
            <main
                className="flex flex-col flex-grow justify-start items-center relative"
                style={{
                    backgroundImage: "url('/cs4125.jpeg')",
                    backgroundSize: 'cover',
                    backgroundPosition: 'center'
                }}
            >
                <div className="w-full p-6 bg-opacity-70 bg-black mt-10">
                    <p className="text-6xl text-white font-semibold text-center mb-4">Landing page</p>
                </div>
                <div className="mt-20 p-6 bg-opacity-70 bg-black rounded-lg">
                    <img
                        src="amog.gif"
                        alt="Description of GIF"
                        className="w-32 h-auto mx-auto" // Adjust size with w-[size] and h-[size]
                    />
                </div>
            </main>
        </>
    );
}

