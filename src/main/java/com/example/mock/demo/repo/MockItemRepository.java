package com.example.mock.demo.repo;

import com.example.mock.demo.model.MockItem;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MockItemRepository extends JpaRepository<MockItem, Long> {
    Boolean existsByName(String name);
    Optional<MockItem> findByName(String name);
}
