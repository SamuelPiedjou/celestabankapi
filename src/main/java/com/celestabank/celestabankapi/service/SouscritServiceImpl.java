package com.celestabank.celestabankapi.service;

import com.celestabank.celestabankapi.entity.Souscrit;
import com.celestabank.celestabankapi.repository.SouscritRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
@AllArgsConstructor
public class SouscritServiceImpl implements SouscritService {
    private SouscritRepository db;
    @Override
    public Souscrit addNominee(Souscrit nominee) {
        db.save(nominee);
        return nominee;
    }

    @Override
    public Souscrit updateNominee(Souscrit nominee) {
        db.save(nominee);
        return nominee;
    }

    @Override
    public List<Souscrit> deleteNominee(int nomineeId) {
        db.deleteById(nomineeId);
        return db.findAll();
    }

    @Override
    public Souscrit findNomineeById(int nomineeId) {
        Souscrit souscrit = db.findById(nomineeId).get();
        return souscrit;
    }

    @Override
    public Set<Souscrit> listAllNominees(int accountId) {

        return db.findByAccount(accountId);
    }
}
