package com.minichatapp.android.minichatapp;

import android.graphics.Bitmap;

import java.util.Date;

/**
 * Created by mery on 27/03/2018.
 */

public class Message {

    protected int id;
    protected String message;
    protected String senderName;
    protected Date sendDate;
    protected int stat;
    protected Bitmap photo;
    public Message(int id, String message, String senderName) {
        this.id = id;
        this.message = message;
        this.senderName = senderName;

        Date date = new Date();
        setSendDate(date);
        stat=1;
    }

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }

    public String getSenderName() {
        return senderName;
    }



    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }
}