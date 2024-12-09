package com.garnicsoft.obligations.services;

import com.garnicsoft.obligations.models.entitys.Obligation;
import com.garnicsoft.obligations.models.enums.Status;
import com.garnicsoft.obligations.repository.ObligationRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class ObligationServiceImpl implements ObligationService {

    private ObligationRepository obligationRepository;

    public ObligationServiceImpl(ObligationRepository obligationRepository) {
        this.obligationRepository = obligationRepository;
    }

    @Override
    public Obligation createObligation(Obligation obligation) {
        obligation.setStatus(Status.MORA);
        obligation.setDateCreation(new Date());
        return obligationRepository.save(obligation);
    }

    @Override
    public Obligation editObligation(Obligation obligation, Long id) {
        obligation.setId(id);
        return obligationRepository.save(obligation);
    }

    @Override
    public List<Obligation> getAllObligation() {
        return obligationRepository.findAll();
    }

    @Override
    public Obligation getObigationByPlayerId(Long id) {
        return null;
    }

    @Override
    public void deleteObligation(Long id) {
        obligationRepository.deleteById(id);
    }

    @Override
    public void closeObligation(Long id) {
        Optional<Obligation> obligationToclose = obligationRepository.findById(id);
        if (obligationToclose != null && obligationToclose.isPresent() && obligationToclose.get().getStatus() != null) {
            obligationToclose.get().setStatus(Status.CANCELADO);
            obligationRepository.save(obligationToclose.get());
        }
    }
}
