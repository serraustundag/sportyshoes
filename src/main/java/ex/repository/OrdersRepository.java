package ex.repository;

import ex.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    public List<Orders> findOrdersByPid(int pid);

    List<Orders> findByLdtAfter(LocalDateTime startDate);
    @Query("select p.pname, p.price, o.oid, o.ldt ,p.category from Product p, Orders o where p.pid = o.pid and o.login.id = :userId")
    List<Object[]> findOrdersByUserId(@Param("userId") int userId);

    @Query("select p.pname, p.price, o.oid, o.ldt ,p.category,l.id from Product p, Orders o, Login l where p.pid = o.pid and o.login.id=l.id")
    List<Object[]> findOrdersByUserId2();

}
