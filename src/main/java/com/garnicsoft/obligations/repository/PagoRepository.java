package com.garnicsoft.obligations.repository;

import com.garnicsoft.obligations.models.entitys.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {
    List<Pago> findByObligationPlayerName(String playerName);
}
