package com.celestabank.celestabankapi.controller;

import com.celestabank.celestabankapi.dto.BeneficiaryDTO;
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
    @PostMapping("/add/{idCust}")
    @ApiOperation(value = "AJOUT D'UN BENEFICIAIRE")
    public BeneficiaryDTO addBeneficiary(@PathVariable long idCust ,@RequestBody BeneficiaryDTO beneficiaryDTO)   {
            return  beneficiaryService.addBeneficiary(idCust,beneficiaryDTO);
    }

    @PutMapping("/update/{idCust}")@ApiOperation(value = "MAJ BENEFICIARE")
    public BeneficiaryDTO updateBenficiary(@PathVariable long idCust,@RequestBody BeneficiaryDTO beneficiary) throws InvalidDetailsOperation {
         return beneficiaryService.updateBeneficiary(idCust,beneficiary);
    }

    @DeleteMapping("delete/{beneficiaryId}")
    @ApiOperation(value = "SUPPRIMER BENEFICIAIRE")
    public boolean deleteBeneficiary(@PathVariable long beneficiaryId) throws DetailsNotFoundException {
         return beneficiaryService.deleteBeneficiary(beneficiaryId);


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
