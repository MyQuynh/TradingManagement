package com.example.demo.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="receiving_note")
public class ReceivingNote implements Serializable {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceivingNote that = (ReceivingNote) o;
        return id == that.id &&
                date.equals(that.date) &&
                staff.equals(that.staff);

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, staff);
    }
}
