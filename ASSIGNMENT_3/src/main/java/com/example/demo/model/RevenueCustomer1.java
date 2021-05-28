package com.example.demo.model;

import com.path.to.RevenueCustomer;

public class RevenueCustomer1 implements RevenueCustomer {

    private Long id;
    private float revenue;

    public RevenueCustomer1(Long id, float revenue) {
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
