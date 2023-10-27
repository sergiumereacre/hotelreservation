"use client"

import { NextUIProvider } from '@nextui-org/react'
import React from 'react'

export function Providers({ children }) {
    return (
        <NextUIProvider>
            {children}
        </NextUIProvider>
    )
}