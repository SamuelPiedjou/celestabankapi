package com.celestabank.celestabankapi.service;

import com.celestabank.celestabankapi.entity.Beneficiary;
import com.celestabank.celestabankapi.exeption.BeneficiaryNotFoundException;

import java.util.Set;

public interface BeneficiaryService {
    Beneficiary addBeneficiary(Beneficiary beneficiary) throws Exception;

    Beneficiary updateBeneficiary(Beneficiary beneficiary) throws Exception;

    Beneficiary deleteBeneficiary(long beneficiaryId) throws BeneficiaryNotFoundException;

    Beneficiary findBeneficiaryById(long beneficiaryId) throws Exception;

    Set<Beneficiary> listAllBeneficiaries(long accountid) throws Exception;
}
