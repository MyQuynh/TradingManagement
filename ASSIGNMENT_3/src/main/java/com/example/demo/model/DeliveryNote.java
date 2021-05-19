package com.example.demo.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="delivery_note")
public class DeliveryNote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="date")
    private Date date;

    @Column(name="staff")
    private long staff_id;

    // Getter, setter and constructor

    public DeliveryNote(){
        super();
    }


    public DeliveryNote(Date date, long staff_id) {
        this.date = date;
        this.staff_id = staff_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(long staff_id) {
        this.staff_id = staff_id;
    }
}
