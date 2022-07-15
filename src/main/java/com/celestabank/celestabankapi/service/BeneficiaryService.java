package com.celestabank.celestabankapi.service;

import com.celestabank.celestabankapi.dto.BeneficiaryDTO;
import com.celestabank.celestabankapi.entity.Beneficiary;
import com.celestabank.celestabankapi.exeption.BeneficiaryNotFoundException;

public interface BeneficiaryService {

    BeneficiaryDTO addBeneficiary(long customerId, BeneficiaryDTO beneficiaryDTO);

    BeneficiaryDTO updateBeneficiary(long idBeneficiary, BeneficiaryDTO beneficiaryDTO);

    boolean deleteBeneficiary(long beneficiaryId);

    Beneficiary findBeneficiaryById(long beneficiaryId);
}
