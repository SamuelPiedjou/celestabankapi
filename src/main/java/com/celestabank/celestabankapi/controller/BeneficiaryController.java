package com.celestabank.celestabankapi.controller;

import com.celestabank.celestabankapi.entity.Beneficiary;
import com.celestabank.celestabankapi.exeption.DetailsNotFoundException;
import com.celestabank.celestabankapi.exeption.InvalidDetailsException;
import com.celestabank.celestabankapi.service.BeneficiaryServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.Set;

@RequestMapping("/beneficiary")
@RestController
@AllArgsConstructor
public class BeneficiaryController {
    private final BeneficiaryServiceImpl beneficiaryService;
    @PostMapping("/add")
    public Beneficiary addBeneficiary(@RequestBody Beneficiary beneficiary) throws InvalidDetailsException {
        Beneficiary n = null;
        try {
            n = beneficiaryService.addBeneficiary(beneficiary);
        } catch (Exception e) {
            throw new InvalidDetailsException("The details given are not valid!");
        }
        return n;
    }

    @PutMapping("update")
    public Beneficiary updateBenficiary(@RequestBody Beneficiary beneficiary) throws InvalidDetailsException {
        Beneficiary n = null;
        try {
            n = beneficiaryService.updateBeneficiary(beneficiary);
        } catch (Exception e) {
            throw new InvalidDetailsException("The details given are not valid!");
        }
        return n;
    }

    @DeleteMapping("delete/{beneficiaryId}")
    public Beneficiary deleteBeneficiary(@PathVariable long beneficiaryId) throws DetailsNotFoundException {
        Beneficiary n = null;
        try {
            n = beneficiaryService.deleteBeneficiary(beneficiaryId);
        } catch (Exception e) {
            throw new DetailsNotFoundException("The given ID is not found!");
        }
        return n;
    }

    @GetMapping("find/{beneficiaryId}")
    public Beneficiary findBeneficiaryById(@PathVariable long beneficiaryId) throws DetailsNotFoundException {
        Beneficiary n = null;
        try {
            n = beneficiaryService.findBeneficiaryById(beneficiaryId);
        } catch (Exception e) {
            throw new DetailsNotFoundException("The given ID is not found!");
        }
        return n;
    }

    @GetMapping("all/{accountId}")
    public Set<Beneficiary> listAllBeneficiaries(@PathVariable long accountId)  {
        Set<Beneficiary> n = null;
        try {
            n = beneficiaryService.listAllBeneficiaries(accountId);
            if(n.isEmpty()) {

            }

        }  catch(Exception e) {

        }
        return n;
    }
}
