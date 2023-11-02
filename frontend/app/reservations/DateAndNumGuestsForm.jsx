"use client";
import { useState } from "react";

export default function DateAndNumGuestsForm() {
  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");
  const [numGuests, setNumGuests] = useState(0);

  function handleSubmit(event) {
    event.preventDefault();


    // handle form submission

    // reset form
    setStartDate("");
    setEndDate("");
    setNumGuests(0);

    
  }

  return (
    <>
      <form onSubmit={handleSubmit} className="max-w-md mx-auto">
        <h2 className="text-2xl font-bold mb-4">Make a Reservation</h2>
        <div className="mb-4">
          <label
            htmlFor="startDate"
            className="block text-gray-700 font-bold mb-2"
          >
            Start Date
          </label>
          <input
            type="date"
            id="startDate"
            name="startDate"
            value={startDate}
            onChange={(event) => setStartDate(event.target.value)}
            className="border border-gray-400 p-2 w-full"
            required
          />
        </div>
        <div className="mb-4">
          <label
            htmlFor="endDate"
            className="block text-gray-700 font-bold mb-2"
          >
            End Date
          </label>
          <input
            type="date"
            id="endDate"
            name="endDate"
            value={endDate}
            onChange={(event) => setEndDate(event.target.value)}
            className="border border-gray-400 p-2 w-full"
            required
          />
        </div>
        <div className="mb-4">
          <label
            htmlFor="numGuests"
            className="block text-gray-700 font-bold mb-2"
          >
            Number of Guests
          </label>
          <input
            type="number"
            id="numGuests"
            name="numGuests"
            value={numGuests}
            onChange={(event) => setNumGuests(event.target.value)}
            className="border border-gray-400 p-2 w-full"
            required
          />
        </div>
        <button
          type="submit"
          className="bg-blue-500 text-white py-2 px-4 rounded"
        >
          Submit
        </button>
      </form>
    </>
  );
}
