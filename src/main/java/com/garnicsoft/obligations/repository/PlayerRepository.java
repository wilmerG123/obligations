package com.garnicsoft.obligations.repository;

import com.garnicsoft.obligations.models.entitys.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    public List<Player> findByCategoryId(Long id);
}
