package com.codewithshubham.fullstackbackend1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codewithshubham.fullstackbackend1.model.User;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
