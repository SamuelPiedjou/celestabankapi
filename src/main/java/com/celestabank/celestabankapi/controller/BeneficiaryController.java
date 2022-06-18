package com.celestabank.celestabankapi.controller;

import com.celestabank.celestabankapi.entity.Beneficiary;
import com.celestabank.celestabankapi.service.BeneficiaryService;
import org.springframework.web.bind.annotation.*;


import java.util.Set;

@RequestMapping("/beneficiary")
@RestController
public class BeneficiaryController {
	
    private final BeneficiaryService beneficiaryService;
    
    public BeneficiaryController(BeneficiaryService beneficiaryService) {
		super();
		this.beneficiaryService = beneficiaryService;
	}

	@PostMapping("/add")
    public Beneficiary addBeneficiary(@RequestBody Beneficiary beneficiary) throws Exception {
        return beneficiaryService.addBeneficiary(beneficiary);
    }

    @PutMapping("update")
    public Beneficiary updateBenficiary(@RequestBody Beneficiary beneficiary) throws Exception {
        return beneficiaryService.updateBeneficiary(beneficiary);
    }

    @DeleteMapping("delete/{beneficiaryId}")
    public Beneficiary deleteBeneficiary(@PathVariable long beneficiaryId) throws Exception {
        return beneficiaryService.deleteBeneficiary(beneficiaryId);
    }

    @GetMapping("find/{beneficiaryId}")
    public Beneficiary findBeneficiaryById(@PathVariable long beneficiaryId) throws Exception {
        return beneficiaryService.findBeneficiaryById(beneficiaryId);
    }

    @GetMapping("all/{accountId}")
    public Set<Beneficiary> listAllBeneficiaries(@PathVariable long accountId) throws Exception  {
        return beneficiaryService.listAllBeneficiaries(accountId);
    }
}
