"use client";
import { useState, useEffect } from "react";
import Room from "./room";

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
      <main className="flex flex-grow items-center justify-center bg-white py-8 px-8">
        <div>
          <p className="text-3xl">Rooms page</p>
          <div className="flex flex-wrap">
            {roomList.map((room) => (
              <div key={room.roomId} className="m-4">
                <Room {...room} />
              </div>
            ))}
          </div>
        </div>
      </main>
    </>
  );
}
