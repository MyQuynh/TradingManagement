package com.example.demo.repository;

import com.example.demo.manager.DateManager;
import com.example.demo.model.*;
import com.path.to.Inventory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import com.example.demo.manager.DateManager;
import com.example.demo.model.DeliveryNote;
import java.util.Date;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    DateManager dateManager = new DateManager();

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void productInventoryBetween() {
        String startDate = "2023-01-01";
        String endDate  = "2024-01-01";
        String date = "2023-08-03";

        int quantityForDelivery = 20;
        int quantityForReceiving = 100;

        Product product = new Product();
        DeliveryNote deliveryNote = new DeliveryNote();
        DeliveryDetail deliveryDetail = new DeliveryDetail();
        ReceivingDetail receivingDetail = new ReceivingDetail();
        ReceivingNote receivingNote = new ReceivingNote();

        product = entityManager.persistAndFlush(product);
        deliveryNote = entityManager.persistAndFlush(deliveryNote);
        deliveryDetail = entityManager.persistAndFlush(deliveryDetail);
        receivingDetail = entityManager.persistAndFlush(receivingDetail);
        receivingNote = entityManager.persistAndFlush(receivingNote);

        product.setName("This is the toys");

        receivingDetail.setProduct(product);
        receivingDetail.setQuantity(quantityForReceiving);
        receivingDetail.setReceivingNote(receivingNote);

        deliveryDetail.setProduct(product);
        deliveryDetail.setQuantity(quantityForDelivery);
        deliveryDetail.setDeliveryNote(deliveryNote);

        deliveryNote.setDate(dateManager.convertStringToDate(date));
        receivingNote.setDate(date);

        List<DeliveryDetail> deliveryDetails = new ArrayList<>();
        deliveryDetails.add(deliveryDetail);

        List<ReceivingDetail> receivingDetails = new ArrayList<>();
        receivingDetails.add(receivingDetail);

        deliveryNote.setDeliveryDetails(deliveryDetails);
        receivingNote.setReceivingDetailList(receivingDetails);

        List<DeliveryNote> deliveryNotes = new ArrayList<>();
        deliveryNotes.add(deliveryNote);

        List<ReceivingNote> receivingNotes = new ArrayList<>();
        receivingNotes.add(receivingNote);

        product.setDeliveryDetails(deliveryDetails);
        product.setReceivingDetails(receivingDetails);


        List<Inventory> inventoryList = productRepository.productInventoryBetween(dateManager.convertStringToDate(startDate), dateManager.convertStringToDate(endDate));
        int indexInventor = 0;
        //Testing to see
        for (Inventory inventory: inventoryList) {
            if (inventory.getProductName().equals("This is the toys")){
                indexInventor = inventoryList.indexOf(inventory);
                break;
            }
        }
        assertEquals(inventoryList.get(indexInventor).getBalance(), quantityForReceiving - quantityForDelivery);
    }
}