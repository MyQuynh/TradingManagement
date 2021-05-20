package com.example.demo.model;


import javax.persistence.*;

@Entity
@Table(name="receiving_note")
public class ReceivingNote {

    @Id
    @GeneratedValue
    private long id;

    @Column(name="date")
    private String date;

    @ManyToOne
    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    private Staff staff;

    // Getter, setter and constucture
    public ReceivingNote(String date, Staff staff) {
        this.date = date;
        this.staff = staff;
    }

    public ReceivingNote() {
        super();
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

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }
}
