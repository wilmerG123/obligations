package com.garnicsoft.obligations.services;

import com.garnicsoft.obligations.models.dtos.ObligationDTO;
import com.garnicsoft.obligations.models.entitys.Category;
import com.garnicsoft.obligations.models.entitys.Obligation;
import com.garnicsoft.obligations.models.entitys.Player;
import com.garnicsoft.obligations.models.enums.Status;
import com.garnicsoft.obligations.repository.ObligationRepository;
import com.garnicsoft.obligations.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ObligationServiceImpl implements ObligationService {

    private ObligationRepository obligationRepository;
    private PlayerRepository playerRepository;

    public ObligationServiceImpl(ObligationRepository obligationRepository,
                                 PlayerRepository playerRepository) {
        this.obligationRepository = obligationRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public void createObligation(ObligationDTO obligation) {

        if (obligation.getPlayers() != null && !obligation.getPlayers().isEmpty()) {
            for (Player player : obligation.getPlayers()) {
                obligationRepository.save(new Obligation(obligation, player));
            }
        } else if (obligation.getCategories() != null && !obligation.getCategories().isEmpty()) {
            for (Category category : obligation.getCategories()) {
                List<Player> playersOnCategory = playerRepository.findByCategoryId(category.getId());
                for (Player player : playersOnCategory) {
                    obligationRepository.save( new Obligation(obligation, player));
                }
            }
        }
    }

    @Override
    public Obligation editObligation(Obligation obligation, Long id) {
        obligation.setStatus(Status.MORA);
        obligation.setId(id);
        return obligationRepository.save(obligation);
    }

    @Override
    public List<Obligation> getAllObligation() {
        List<Obligation> obligationsResult = obligationRepository.findAll();
        if(obligationsResult != null && !obligationsResult.isEmpty()) {
            obligationsResult = obligationsResult.stream().filter(obligation -> obligation.getStatus() != null
                    && obligation.getStatus()== Status.MORA).collect(Collectors.toList());
        }
        return obligationsResult;
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
