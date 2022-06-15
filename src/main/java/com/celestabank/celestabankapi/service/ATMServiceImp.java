package com.celestabank.celestabankapi.service;

import com.celestabank.celestabankapi.entity.ATM;
import com.celestabank.celestabankapi.exeption.ATMAlreadyExistsException;
import com.celestabank.celestabankapi.exeption.NoSuchATMExistsException;
import com.celestabank.celestabankapi.exeption.NoSuchCustomerExistsException;
import com.celestabank.celestabankapi.repository.ATMrepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ATMServiceImp implements ATMservice {

    private ATMrepository db;

    @Override
    public ATM addAtm(ATM atm) throws ATMAlreadyExistsException {
        ATM existingAtm  = db.findById(atm.getId()).orElse(null);
        if (existingAtm == null) {
            db.save(atm);
            return  atm;
        }else throw new ATMAlreadyExistsException("ATM already exists!!");
    }

    @Override
    public ATM updateAtm(ATM atm) {
        ATM existingAtm  = db.findById(atm.getId()).orElse(null);
        if (existingAtm == null) {
            db.save(atm);
            return  atm;
        }else throw new ATMAlreadyExistsException("ATM already exists!!");
    }

    @Override
    public boolean deleteAtm(long atmId) {
        log.info("Suppression de l'ATM  : "+atmId + "  effectué avec succès");
        ATM existingPartner
                = db.findById(atmId)
                .orElse(null);
        if (existingPartner == null)
            throw new NoSuchCustomerExistsException(
                    "No Such partner Exists!!");
        else {

            db.deleteById(atmId);
            return true;
        }
    }

    @Override
    public List<ATM> getAllAtm() {
        return db.findAll();
    }

    @Override
    public ATM getAtmById(long atmId) {
        log.info("Recherche de l'ATM  : "+atmId + "  effectué avec succès");
        ATM existingPartner = db.findById(atmId).orElse(null);
        if (existingPartner == null)
            throw new NoSuchATMExistsException(
                    "No Such ATM Exists!!");
        else {

            db.findById(atmId);
            return existingPartner;
        }
    }
}
