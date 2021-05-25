package com.example.demo.repository;

import com.example.demo.model.Product;
import com.path.to.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Pageable;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "select product.id as id ,product.name as productName, COALESCE(receiving_detail.receive, 0) receive , COALESCE(delivery_detail.delivery, 0) delivery,  (COALESCE(receive,0) - COALESCE(delivery,0)) AS balance \n" +
            "from product\n" +
            "left join (\n" +
            "    select product_id, sum(receiving_detail.quantity) as receive\n" +
            "    from receiving_detail, receiving_note\n" +
            "    where receiving_detail.receivingnote_id = receiving_note.id\n" +
            "    and receiving_note.date >= :startDate\n" +
            "    and receiving_note.date <  :endDate\n" +
            "    group by receiving_detail.product_id\n" +
            ") receiving_detail on product.id = receiving_detail.product_id\n" +
            "\n" +
            "left join (\n" +
            "    select product_id, sum(delivery_detail.quantity) as delivery\n" +
            "    from delivery_detail, delivery_note\n" +
            "    where delivery_detail.deliverynote_id = delivery_note.id\n" +
            "      and delivery_note.date >= :startDate\n" +
            "      and delivery_note.date <  :endDate\n" +
            "    group by delivery_detail.product_id\n" +
            ") delivery_detail on delivery_detail.product_id = product.id",
            nativeQuery = true)
    List<Inventory> productInventoryBetween(@Param("startDate") Date startDate,
                                            @Param("endDate") Date endDate);

    @Query(value = "select product.id as id ,product.name as productName, COALESCE(receiving_detail.receive, 0) receive , COALESCE(delivery_detail.delivery, 0) delivery,  (COALESCE(receive,0) - COALESCE(delivery,0)) AS balance \n" +
            "from product\n" +
            "left join (\n" +
            "    select product_id, sum(receiving_detail.quantity) as receive\n" +
            "    from receiving_detail, receiving_note\n" +
            "    where receiving_detail.receivingnote_id = receiving_note.id\n" +
            "    and receiving_note.date >= :startDate\n" +
            "    and receiving_note.date <  :endDate\n" +
            "    group by receiving_detail.product_id\n" +
            ") receiving_detail on product.id = receiving_detail.product_id\n" +
            "\n" +
            "left join (\n" +
            "    select product_id, sum(delivery_detail.quantity) as delivery\n" +
            "    from delivery_detail, delivery_note\n" +
            "    where delivery_detail.deliverynote_id = delivery_note.id\n" +
            "      and delivery_note.date >= :startDate\n" +
            "      and delivery_note.date <  :endDate\n" +
            "    group by delivery_detail.product_id\n" +
            ") delivery_detail on delivery_detail.product_id = product.id",
            nativeQuery = true)
    Page<Inventory> productInventoryBetween(@Param("startDate") Date startDate,
                                            @Param("endDate") Date endDate, Pageable pageable);
}
