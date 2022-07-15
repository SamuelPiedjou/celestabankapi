package com.celestabank.celestabankapi.service;

import com.celestabank.celestabankapi.dto.AccountDto;
import com.celestabank.celestabankapi.dto.BeneficiaryDTO;
import com.celestabank.celestabankapi.dto.CustomerDto;
import com.celestabank.celestabankapi.entity.Account;
import com.celestabank.celestabankapi.entity.Beneficiary;
import com.celestabank.celestabankapi.entity.Customer;
import com.celestabank.celestabankapi.exeption.BeneficiaryNotFoundException;
import com.celestabank.celestabankapi.exeption.BeneficiaryNotHaveAnAccount;
import com.celestabank.celestabankapi.mappers.BankServiceMapper;
import com.celestabank.celestabankapi.repository.AccountRepository;
import com.celestabank.celestabankapi.repository.BeneficiaryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class BeneficiaryServiceImpl implements BeneficiaryService {
     private final  BeneficiaryRepository db;
     private final AccountRepository accountRepository;
    private final CustomerServiceImpl customerService;
    private final AccountServiceImp accountServiceImp;
    private final BankServiceMapper dtoMappers;

     public  boolean foundMatchBenf(Beneficiary beneficiary){
         Account benf= accountRepository.findById(beneficiary.getBeneficiaryAccNo())
                 .orElseThrow(()-> new BeneficiaryNotHaveAnAccount("BENEFICIARY NOT HAVE AN ACCOUNT  "));
         return true;
     }

    @Override
    public BeneficiaryDTO addBeneficiary(long customerId, BeneficiaryDTO beneficiaryDTO) {
        CustomerDto customerDto  ;
        customerDto = customerService.findCustById(customerId);
        AccountDto accountDto = accountServiceImp.viewAcc(beneficiaryDTO.getBeneficiaryAccNo());
        Beneficiary beneficiary  ;
         if (customerDto!=null){
            if (accountDto != null){
                beneficiary = dtoMappers.fromBeneficiaryDto(beneficiaryDTO);
                beneficiary.setCustomer(dtoMappers.fromCustomerDto(customerDto));
                beneficiary.setBeneficiaryName(beneficiaryDTO.getBeneficiaryName());
                beneficiary.setBeneficiaryAccNo(beneficiaryDTO.getBeneficiaryAccNo());
                beneficiary.setBeneficiaryId(beneficiaryDTO.getBeneficiaryId());
                db.save(beneficiary);
                beneficiaryDTO =dtoMappers.fromBeneficiary(beneficiary);
                beneficiaryDTO.setCustomerId(customerId);
                return beneficiaryDTO ;
            }
         }
         return null;
    }

    @Override
    public BeneficiaryDTO updateBeneficiary(long idBeneficiary, BeneficiaryDTO beneficiaryDTO) {
        if(findBeneficiaryById(idBeneficiary) !=null){
            Beneficiary beneficiary =  findBeneficiaryById(idBeneficiary);
              beneficiary.setBeneficiaryName(beneficiaryDTO.getBeneficiaryName());

              db.save(beneficiary);
              return dtoMappers.fromBeneficiary(beneficiary);
        }
        return  null;
    }

    @Override
    public boolean deleteBeneficiary(long beneficiaryId) {
        if (findBeneficiaryById(beneficiaryId)!=null){
            db.deleteById(beneficiaryId);
            return true;
        }
        return  false;
    }

    @Override
    public Beneficiary findBeneficiaryById(long beneficiaryId) {
         Beneficiary beneficiary= db.findById(beneficiaryId).orElseThrow(()-> new BeneficiaryNotFoundException("Beneficiary not Exit !"));
        log.info("HEYYYYYYYYYYYYYY "+beneficiary);
        beneficiary.setCustomer(beneficiary.getCustomer());
         return  beneficiary;
    }

    public List<Beneficiary> getAllBenef(long idCust){
            Customer customer= customerService.findCustomerById(idCust);
            List<Beneficiary> beneficiaries = (List<Beneficiary>) new  Beneficiary();
            db.findAll().forEach(beneficiary -> { if (beneficiary.getCustomer().getUserId()== customer.getUserId())beneficiaries.add(beneficiary);});
            return beneficiaries;
    }

}
