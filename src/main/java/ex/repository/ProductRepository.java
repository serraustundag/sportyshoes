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

    @Query("select p from Product p where p.pname = :pname")
    public 	List<Product> findProductByName(@Param("pname") String pname);

    @Query("select p from Product p where p.price >= :price")
    public 	List<Product> findProductByPrice(@Param("price") float price);

    @Query("select p.pname,p.price,o.oid,o.ldt,p.category,l.id from Product p, Orders o ,Login l where p.pid=o.pid")
    public List<Object[]> orderDetails();

    @Query("select p.pname, p.price, o.oid, o.ldt ,p.category from Product p, Orders o where p.pid = o.pid and o.login.id = :userId")
    List<Object[]> findOrdersByUserId(@Param("userId") int userId);

    @Query("select p.pname, p.price, o.oid, o.ldt ,p.category, l.id from Product p, Orders o, Login l where p.pid = o.pid and o.login.id=l.id")
    List<Object[]> findOrdersByUserId2();

    @Query("select o.pid, p.pname, p.price,o.oid,o.ldt, p.category,l.id from Login l, Orders o, Product p where p.pid=o.oid")
    List<Object[]> findProductsByCategories();



}