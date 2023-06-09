package com.championship.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.championship.domain.model.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match, Integer>{
  List<Match> findByStatusContaining(String status);
}
