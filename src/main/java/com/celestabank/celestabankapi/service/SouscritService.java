package com.celestabank.celestabankapi.service;

import com.celestabank.celestabankapi.entity.Souscrit;

import java.util.List;
import java.util.Set;

public interface SouscritService {
    public Souscrit addNominee(Souscrit nominee);

    public Souscrit updateNominee(Souscrit nominee);

    public List<Souscrit> deleteNominee(int nomineeId);

    public Souscrit findNomineeById(int nomineeId);

    public Set<Souscrit> listAllNominees(int accountId);
}
