/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;
import model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
/**
 *
 * @author JUAN JOSE
 */
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
