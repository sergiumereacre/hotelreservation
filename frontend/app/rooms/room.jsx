import React from "react";

export default function Room(props) {
  const { available, capacity, price, roomId, roomNumber, roomType } = props;

  return (
    <div className="bg-white rounded-lg shadow-lg p-6">
      <h2 className="text-2xl font-bold mb-4">{roomType}</h2>
      <p className="text-gray-700 mb-2">Room Number: {roomNumber}</p>
      <p className="text-gray-700 mb-2">Capacity: {capacity}</p>
      <p className="text-gray-700 mb-2">Price per night: €{price.toFixed(2)}</p>
      <p
        className={`text-lg font-bold ${
          available ? "text-green-500" : "text-red-500"
        }`}
      >
        {available ? "Available" : "Not Available"}
      </p>
    </div>
  );
}
