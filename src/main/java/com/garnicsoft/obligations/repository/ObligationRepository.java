package com.garnicsoft.obligations.repository;

import com.garnicsoft.obligations.models.entitys.Obligation;
import com.garnicsoft.obligations.models.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObligationRepository extends JpaRepository<Obligation, Long> {
    List<Obligation> findByPlayerIdAndStatus(Long jugadorId, Status status);

}
