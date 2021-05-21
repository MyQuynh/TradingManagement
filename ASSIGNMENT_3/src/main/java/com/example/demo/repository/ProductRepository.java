package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "select product.id ,product.name, COALESCE(receiving_detail.receive, 0) receive, COALESCE(delivery_detail.delivery, 0) delivery, COALESCE(sale_detail.balance, 0) balance\n" +
            "from product\n" +
            "left join (\n" +
            "    select product_id, count(receiving_detail) as receive\n" +
            "    from receiving_detail, receiving_note\n" +
            "    where receiving_detail.receivingnote_id = receiving_note.id\n" +
            "    and receiving_note.date >= :startDate\n" +
            "    and receiving_note.date <=  :endDate\n" +
            "    group by receiving_detail.product_id\n" +
            ") receiving_detail on product.id = receiving_detail.product_id\n" +
            "\n" +
            "left join (\n" +
            "    select product_id, count(delivery_detail) as delivery\n" +
            "    from delivery_detail, delivery_note\n" +
            "    where delivery_detail.deliverynote_id = delivery_note.id\n" +
            "      and delivery_note.date >= :startDate\n" +
            "      and delivery_note.date <=  :endDate\n" +
            "    group by delivery_detail.product_id\n" +
            ") delivery_detail on delivery_detail.product_id = product.id\n" +
            "\n" +
            "left join (\n" +
            "    select sale_detail.product_id, sum(sale_detail.price * sale_detail.quantity) as balance\n" +
            "    from sale_detail, sales_invoice\n" +
            "    where sale_detail.salesinvoice = sales_invoice.id\n" +
            "      and sales_invoice.date >= :startDate\n" +
            "      and sales_invoice.date <=  :endDate\n" +
            "    group by sale_detail.product_id\n" +
            ") sale_detail on sale_detail.product_id = product.id",
            nativeQuery = true)
    List<Object[]> productInventoryBetween(@Param("startDate") Date startDate,
                                           @Param("endDate") Date endDate);
}
