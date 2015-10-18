package com.statefarm.codingcomp.model;

import java.util.Date;

/**
 * A class representing an email.
 */
public class Email {

    private String from;
    private String to;
    private Date sent;

    public Email( String from, String to, Date sent ) {
        super();
        this.from = from;
        this.to = to;
        this.sent = sent;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom( String from ) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo( String to ) {
        this.to = to;
    }

    public Date getSent() {
        return sent;
    }

    public void setSent( Date sent ) {
        this.sent = sent;
    }

}
