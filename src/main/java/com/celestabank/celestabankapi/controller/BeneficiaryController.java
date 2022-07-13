package com.celestabank.celestabankapi.controller;

import com.celestabank.celestabankapi.entity.Beneficiary;
import com.celestabank.celestabankapi.exeption.DetailsNotFoundException;
import com.celestabank.celestabankapi.exeption.InvalidDetailsOperation;
import com.celestabank.celestabankapi.service.BeneficiaryServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RequestMapping("/beneficiary")
@RestController
@AllArgsConstructor
public class BeneficiaryController {
    private final BeneficiaryServiceImpl beneficiaryService;
    @PostMapping("/add")
    @ApiOperation(value = "AJOUT D'UN BENEFICIAIRE")
    public Beneficiary addBeneficiary(@RequestBody Beneficiary beneficiary)   {
            return  beneficiaryService.addBeneficiary(beneficiary);
    }

    @PutMapping("update")@ApiOperation(value = "MAJ BENEFICIARE")
    public Beneficiary updateBenficiary(@RequestBody Beneficiary beneficiary) throws InvalidDetailsOperation {
        Beneficiary n = null;
        try {
            n = beneficiaryService.updateBeneficiary(beneficiary);
        } catch (Exception e) {
            throw new InvalidDetailsOperation("The details given are not valid!");
        }
        return n;
    }

    @DeleteMapping("delete/{beneficiaryId}")
    @ApiOperation(value = "SUPPRIMER BENEFICIAIRE")
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
    @ApiOperation(value = "CHERCHER UN BENEFICIAIRE")
    public Beneficiary findBeneficiaryById(@PathVariable long beneficiaryId)   {
           return beneficiaryService.findBeneficiaryById(beneficiaryId);
    }

    @GetMapping("all/{accountId}")
    @ApiOperation(value = "LISTER TOUS LES BENEFICIAIRES D'UN CLIENT")
    public List<Beneficiary> listAllBeneficiaries(@PathVariable long accountId)  {
            return  beneficiaryService.getAllBenef(accountId);

    }
}
