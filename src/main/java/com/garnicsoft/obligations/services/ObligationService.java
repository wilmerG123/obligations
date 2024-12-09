package com.garnicsoft.obligations.services;

import com.garnicsoft.obligations.models.entitys.Obligation;

import java.util.List;

public interface ObligationService {

    public Obligation createObligation(Obligation obligation);

    public Obligation editObligation(Obligation obligation, Long id);

    public List<Obligation> getAllObligation();

    public Obligation getObigationByPlayerId(Long id);

    public void deleteObligation(Long id);
    public void closeObligation(Long id);
}
