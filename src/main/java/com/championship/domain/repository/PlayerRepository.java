package com.championship.domain.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.championship.domain.model.Player;


@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
  public List<Player> findByNameContaining(String name);
  public Page<Player> findByNameContaining(@Param("name") String name, Pageable page);
}
