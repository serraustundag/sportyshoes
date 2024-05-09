package ex.repository;

import java.util.List;

import ex.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ex.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

    @Query("select p.pname,p.price,o.oid,o.ldt,p.category,l.id from Product p, Orders o ,Login l where p.pid=o.pid ")
    public List<Object[]> orderDetails();

    @Query("select distinct category from Product ")
    List<String> findDistinctByCategory();

    @Query("select pid from Product where category = :category ")
    List<Integer> findPidByCategory(@Param("category") String category);


}