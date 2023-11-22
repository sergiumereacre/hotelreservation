package com.hotel.reviews.entity;

import com.hotel.reviews.interfaces.ReviewCommand;

//@Entity
// Invoker (Command Design Pattern)
public class ReviewInvoker {
    private ReviewCommand command;

    public void setCommand(ReviewCommand command) {
        this.command = command;
    }

    public void executeCommand() {
        command.execute();
    }
}