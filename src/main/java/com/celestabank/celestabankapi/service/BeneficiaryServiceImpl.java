package com.celestabank.celestabankapi.service;

import com.celestabank.celestabankapi.entity.Beneficiary;
import com.celestabank.celestabankapi.repository.BeneficiaryRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {

	private BeneficiaryRepository db;

	@Override
	public Beneficiary addBeneficiary(Beneficiary beneficiary) {
		db.save(beneficiary);
		return beneficiary;
	}

	@Override
	public Beneficiary updateBeneficiary(Beneficiary beneficiary) {
		db.save(beneficiary);
		return beneficiary;
	}

	@Override
	public Beneficiary deleteBeneficiary(long beneficiaryId) {
		db.deleteById(beneficiaryId);
		return db.findById(beneficiaryId).get(); // danger, tu cherches ce que tu as supprimer !!!
	}

	@Override
	public Beneficiary findBeneficiaryById(long beneficiaryId) {
		return db.findById(beneficiaryId).orElse(null);
	}

	@Override
	public Set<Beneficiary> listAllBeneficiaries(long accountid) {
		return db.listAllBeneficiaries(accountid);
	}
}
