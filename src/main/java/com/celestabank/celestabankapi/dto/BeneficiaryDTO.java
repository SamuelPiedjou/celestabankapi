package com.celestabank.celestabankapi.dto;

import com.celestabank.celestabankapi.entity.Beneficiary;
import com.celestabank.celestabankapi.entity.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.ManyToOne;
@Data
public class BeneficiaryDTO {
    private long beneficiaryId;
    private String beneficiaryName;
    private long beneficiaryAccNo;
    private long customerId;

    public BeneficiaryDTO(Beneficiary beneficiary, long customerId) {
        this.beneficiaryId = beneficiary.getBeneficiaryId();
        this.beneficiaryName = beneficiary.getBeneficiaryName();
        this.beneficiaryAccNo = beneficiary.getBeneficiaryAccNo();
        this.customerId=customerId;
    }
    public BeneficiaryDTO() {

    }


}
