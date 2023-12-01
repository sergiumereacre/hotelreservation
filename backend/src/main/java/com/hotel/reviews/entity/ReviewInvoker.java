package com.hotel.reviews.entity;

import com.hotel.reviews.interfaces.IReviewCommand;

// Invoker (Command Design Pattern)
public class ReviewInvoker {
    private IReviewCommand command;

    public void setCommand(IReviewCommand command) {
        this.command = command;
    }

    public void executeCommand() {
        command.execute();
    }
}