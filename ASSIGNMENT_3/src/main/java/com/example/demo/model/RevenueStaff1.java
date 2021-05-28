package com.example.demo.model;
import com.path.to.RevenueStaff;

public class RevenueStaff1 implements RevenueStaff {

    private Long id;
    private Float revenue;

    public RevenueStaff1(Long id, Float revenue) {
        this.id = id;
        this.revenue = revenue;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Float getRevenue() {
        return revenue;
    }
}
