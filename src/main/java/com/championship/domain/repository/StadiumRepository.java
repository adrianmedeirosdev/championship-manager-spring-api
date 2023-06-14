package com.championship.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.championship.domain.model.Stadium;

@Repository
public interface StadiumRepository extends JpaRepository<Stadium, Integer>{
  List<Stadium> findByNameContaining(String name);
}
