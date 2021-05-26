package com.example.demo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    void getIdAndSet() {
        int categoryId = 12;
        Category category = new Category(16,"Book");
        category.setId(categoryId);
        assertEquals(categoryId, category.getId());
    }


    @Test
    void getNameAndSet() {
        String categoryName = "test123";
        Category category = new Category(16,"Book");
        category.setName(categoryName);
        assertEquals(categoryName, category.getName());
    }

//    @Test
//    public void testEqualsAndHashCode() {
//        Category category = new Category();
//        Category category1 = new Category();
//        assertEquals(category, category1);
//        assertEquals(category.hashCode(), category1.hashCode());
//    }

}