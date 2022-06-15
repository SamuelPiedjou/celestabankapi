package com.celestabank.celestabankapi.service;

import com.celestabank.celestabankapi.entity.Beneficiary;
import com.celestabank.celestabankapi.entity.Customer;
import com.celestabank.celestabankapi.exeption.BeneficiaryNotFoundException;

import java.util.Set;

public interface BeneficiaryService {

    Beneficiary addBeneficiary(Beneficiary beneficiary );

    public Beneficiary updateBeneficiary(Beneficiary beneficiary);

    public Beneficiary deleteBeneficiary(long beneficiaryId) throws BeneficiaryNotFoundException;

    public Beneficiary findBeneficiaryById(long beneficiaryId);

}
