package ru.vinogradov.kataBoot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.vinogradov.kataBoot.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("Select u from User u left join fetch u.roles where u.name=:name")
    User findByUsername (String name);

    @Override
    Optional<User> findById(Long id);
}
