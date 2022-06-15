package com.celestabank.celestabankapi.service;

import com.celestabank.celestabankapi.entity.Account;
import com.celestabank.celestabankapi.entity.Beneficiary;
import com.celestabank.celestabankapi.entity.Customer;
import com.celestabank.celestabankapi.exeption.BeneficiaryNotFoundException;
import com.celestabank.celestabankapi.exeption.BeneficiaryNotHaveAnAccount;
import com.celestabank.celestabankapi.repository.AccountRepository;
import com.celestabank.celestabankapi.repository.BeneficiaryRepository;
import com.celestabank.celestabankapi.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class BeneficiaryServiceImpl implements BeneficiaryService {
     private final  BeneficiaryRepository db;
     private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AccountServiceImp accountServiceImp;
     public  boolean foundMatchBenf(Beneficiary beneficiary){
         Account benf= accountRepository.findById(beneficiary.getBeneficiaryAccNo())
                 .orElseThrow(()-> new BeneficiaryNotHaveAnAccount("BENEFICIARY NOT HAVE AN ACCOUNT N°"));
         return true;
     }

    @Override
    public Beneficiary addBeneficiary(Beneficiary beneficiary ) {
         if (foundMatchBenf(beneficiary)){
             Beneficiary beneficiary1 = new Beneficiary();
             beneficiary1.setCustomer(beneficiary.getCustomer());
             beneficiary1.setBeneficiaryName(beneficiary.getBeneficiaryName());
             return  db.save(beneficiary1);
         }else return null;
    }

    @Override
    public Beneficiary updateBeneficiary(Beneficiary beneficiary) {
        if(foundMatchBenf(beneficiary))return db.save(beneficiary );
             else return null;
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

    public List<Beneficiary> getAllBenef(long idCust){
            Customer customer= customerRepository.findById(idCust).orElse(null);
            List<Beneficiary> beneficiaries = (List<Beneficiary>) new  Beneficiary();
            db.findAll().forEach(beneficiary -> { if (beneficiary.getCustomer().getUserId()== customer.getUserId())beneficiaries.add(beneficiary);});
            return beneficiaries;
    }

}
