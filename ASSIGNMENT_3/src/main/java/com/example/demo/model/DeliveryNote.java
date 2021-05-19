package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name="delivery_note")
public class DeliveryNote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="date")
    private String date;

    @Column(name="staff")
    private long staff_id;

    // Getter, setter and constructor

    public DeliveryNote(){
        super();
    }


    public DeliveryNote(String date, long staff_id) {
        this.date = date;
        this.staff_id = staff_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(long staff_id) {
        this.staff_id = staff_id;
    }
}
