package com.licenta.app.Model;

public class Affirmation {
    public Affirmation() { }

    public Affirmation(String note, String date, String key) {
        this.note = note;
        this.date = date;
        this.key = key;
    }
    public Affirmation(String note, String date, String key ,String mode,String feeling) {
        this.note = note;
        this.date = date;
        this.key = key;
        this.mode=mode;
        this.feeling=feeling;
    }


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    public String getFeeling() {
        return feeling;
    }

    public String getMode() {
        return mode;
    }
    public String note;
    public String date;
    public String key;
    public String mode;
    public String feeling;
}
