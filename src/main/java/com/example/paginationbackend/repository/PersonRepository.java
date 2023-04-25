package com.example.paginationbackend.repository;

import com.example.paginationbackend.Entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}
