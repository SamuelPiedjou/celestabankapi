package com.celestabank.celestabankapi.service;

import com.celestabank.celestabankapi.entity.Souscrit;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
@AllArgsConstructor
public class SouscritServiceImpl implements SouscritService {
    @Override
    public Souscrit addNominee(Souscrit nominee) {
        return null;
    }

    @Override
    public Souscrit updateNominee(Souscrit nominee) {
        return null;
    }

    @Override
    public List<Souscrit> deleteNominee(int nomineeId) {
        return null;
    }

    @Override
    public Souscrit findNomineeById(int nomineeId) {
        return null;
    }

    @Override
    public Set<Souscrit> listAllNominees(int accountId) {
        return null;
    }
}
