package ex.repository;

import ex.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer> {

    public Optional<Login> findByUsername(String username);

    @Query("SELECT l FROM Login l where l.role='USER'")
    List<Login> findAllLoggedInUsers();
}