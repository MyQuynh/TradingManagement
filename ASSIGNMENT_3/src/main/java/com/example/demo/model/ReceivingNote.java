package com.example.demo.model;


import javax.persistence.*;
import java.util.List;

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

    public ReceivingNote(long id, String date, Staff staff) {
        this.id = id;
        this.date = date;
        this.staff = staff;
    }

    @OneToMany(mappedBy = "receivingNote")
    private List<ReceivingDetail> receivingDetailList;

    // Getter, setter and constucture
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

    public List<ReceivingDetail> getReceivingDetailList() {
        return receivingDetailList;
    }

    public void setReceivingDetailList(List<ReceivingDetail> receivingDetailList) {
        this.receivingDetailList = receivingDetailList;
    }
}
