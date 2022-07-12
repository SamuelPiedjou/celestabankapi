package com.celestabank.celestabankapi.service;

import com.celestabank.celestabankapi.dto.AtmDTO;
import com.celestabank.celestabankapi.entity.ATM;
import com.celestabank.celestabankapi.exeption.ATMAlreadyExistsException;
import com.celestabank.celestabankapi.exeption.ATMNotExistException;
import com.celestabank.celestabankapi.mappers.BankServiceMapper;
import com.celestabank.celestabankapi.repository.ATMrepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ATMServiceImp implements ATMservice {

    private  final ATMrepository db;
    private final BankServiceMapper dtoMappers;
    private final AccountServiceImp accountServiceImp;



    @Override
    public AtmDTO addAtm(AtmDTO atmDTO){
        ATM existingAtm  = dtoMappers.fromAtmDTO(atmDTO);
            existingAtm.setAccount(accountServiceImp.savingAccount(200000000,existingAtm.getId()));
            db.save(existingAtm);
            return  dtoMappers.fromATM(existingAtm);
    }

    @Override
    public AtmDTO updateAtm(long atmId, AtmDTO atmDTO) {
        ATM atm = getAtmById(atmId);

        if (atm!=null){
            AtmDTO atmDTO1= dtoMappers.fromATM(atm);
            atmDTO1.setAdresse(atmDTO.getAdresse());
            atm= dtoMappers.fromAtmDTO(atmDTO1);
            db.save(atm);
            return atmDTO1;
        }
        return  null;
    }

    @Override
    public boolean deleteAtm(long atmId) {
        log.info("Suppression de l'ATM  : "+atmId + "  effectué avec succès");
        if (getAtmById(atmId)!=null){
            db.deleteById(atmId);
            return  true;
        }
        return  false;
    }

    @Override
    public List<ATM> getAllAtm() {
        return db.findAll();
    }

    @Override
    public ATM getAtmById(long atmId) {
        ATM atm = db.findById(atmId).orElseThrow(()-> new ATMNotExistException("ATM NOT FOUND"));
        log.info("Recherche de l'ATM  : "+atmId + "  effectué avec succès");
        return atm;
    }
    @Override
    public AtmDTO viewAtmDTO(long atmId){
        ATM atm = db.findById(atmId).orElseThrow(()-> new ATMNotExistException("ATM NOT FOUND"));
        log.info("Recherche de l'ATM  : "+atmId + "  effectué avec succès");
        return dtoMappers.fromATM(atm);
    }
}
