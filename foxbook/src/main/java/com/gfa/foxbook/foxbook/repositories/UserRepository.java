package com.gfa.foxbook.foxbook.repositories;

import com.gfa.foxbook.foxbook.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
