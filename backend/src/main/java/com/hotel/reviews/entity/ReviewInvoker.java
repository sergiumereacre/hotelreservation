package com.hotel.reviews.entity;

import javax.persistence.Entity;

import com.hotel.reviews.interfaces.ReviewCommand;

@Entity
// Invoker (Command Design Pattern)
public class ReviewInvoker {
    private ReviewCommand command;

    public void setCommand(ReviewCommand command) {
        this.command = command;
    }

    public boolean executeCommand() {
        return command.execute();
    }
}

