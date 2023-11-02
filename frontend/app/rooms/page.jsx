"use client";
import { useState, useEffect } from "react";

export default function Rooms() {
  const [roomList, setRoomList] = useState([]);

  const fetchAllRooms = async () => {
    try {
      const response = await fetch("/api/hotel/rooms", {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
      });

      if (response.ok) {
        const roomList = await response.json();
        console.log("roomList:", roomList);
        setRoomList(roomList);
      } else {
        console.error("Failed to fetch rooms:", response.statusText);
      }
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    fetchAllRooms();
  }, []);

  return (
    <>
      <main className="flex flex-grow items-center justify-center bg-white">
        <p className="text-3xl">Rooms page</p>

        <div>
          <h1>Rooms</h1>
          <ul>
            {roomList.map((room) => (
              <li key={room.roomId}>
                <h2>{room.roomName}</h2>
                <p>Capacity: {room.capacity}</p>
              </li>
            ))}
          </ul>
        </div>
      </main>
    </>
  );
}
