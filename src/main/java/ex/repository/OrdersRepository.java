package ex.repository;

import ex.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    public List<Object[]> findOrdersByLoginId(int id);
    /*
    @Query("SELECT o FROM Orders o WHERE o.login.id = :userId")
    List<Object[]> findOrdersByUserId(int userId);
    */

}
