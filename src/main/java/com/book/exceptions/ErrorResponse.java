package com.book.exceptions;

public class ErrorResponse {

    private int status;
    private String title;
    private String description;
    private long timestamp;

    public ErrorResponse() {
    }

    public ErrorResponse(int status, String title, String description, long timeStamp) {
        this.status = status;
        this.title = title;
        this.description = description;
        this.timestamp = timeStamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getTimeStamp() {
        return timestamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timestamp = timeStamp;
    }

}