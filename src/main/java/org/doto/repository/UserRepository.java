package org.doto.repository;

import org.doto.entity.Role;
import org.doto.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
//    @Modifying
//    @Query("UPDATE user SET role = :role where id = :id")
//    void updateRole(@Param(value = "id") Integer id, @Param(value = "role") Role role);

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);
}
