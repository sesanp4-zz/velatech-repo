package com.example.vela.model;

import javax.persistence.*;

@Entity(name = "payload")
public class Payload {

    @Id
    @SequenceGenerator(name ="REQUEST_SEQUENCE", sequenceName = "REQUEST_PRIMARY_SEQ", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "REQUEST_SEQUENCE")
    @Basic(optional = false)
    private Long size;
    String scheme;
    String type;
    String bank;
    String card;

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }
}
