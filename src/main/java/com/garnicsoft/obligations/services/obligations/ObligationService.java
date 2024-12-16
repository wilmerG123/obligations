package com.garnicsoft.obligations.services.obligations;

import com.garnicsoft.obligations.models.dtos.ObligationDTO;
import com.garnicsoft.obligations.models.entitys.Obligation;

import java.util.List;

public interface ObligationService {

    public void createObligation(ObligationDTO obligation);
    public Obligation getObligation(Long id);

    public Obligation editObligation(Obligation obligation, Long id);

    public List<Obligation> getAllObligation();

    public Obligation getObigationByPlayerId(Long id);
    public List<Obligation> getObligationByPlayerName(String playerName);
    public List<Obligation> getObligationsByCategory(Long categoryId);

    public void deleteObligation(Long id);
    public void closeObligation(Long id);
}
