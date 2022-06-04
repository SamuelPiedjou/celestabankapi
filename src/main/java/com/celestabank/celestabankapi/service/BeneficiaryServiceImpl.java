package com.celestabank.celestabankapi.service;

import com.celestabank.celestabankapi.entity.Beneficiary;
import com.celestabank.celestabankapi.exeption.BeneficiaryNotFoundException;
import com.celestabank.celestabankapi.repository.BeneficiaryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class BeneficiaryServiceImpl implements BeneficiaryService {
    BeneficiaryRepository db;
    @Override
    public Beneficiary addBeneficiary(Beneficiary beneficiary) {
        db.save(beneficiary );
        return  beneficiary;
    }

    @Override
    public Beneficiary updateBeneficiary(Beneficiary beneficiary) {
        db.save(beneficiary);
        return  beneficiary;
    }

    @Override
    public Beneficiary deleteBeneficiary(long beneficiaryId) {
        db.deleteById(beneficiaryId);
        return  db.findById(beneficiaryId).get();
    }

    @Override
    public Beneficiary findBeneficiaryById(long beneficiaryId) {
        return db.findById(beneficiaryId).orElse(null);
    }

    @Override
    public Set<Beneficiary> listAllBeneficiaries(long accountid) {
        return db.listAllBeneficiaries(accountid);
    }
}
