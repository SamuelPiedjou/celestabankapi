package com.celestabank.celestabankapi.service;

import com.celestabank.celestabankapi.entity.Partner;
import com.celestabank.celestabankapi.exeption.CustomerAlreadyExistsException;
import com.celestabank.celestabankapi.exeption.NoSuchCustomerExistsException;
import com.celestabank.celestabankapi.exeption.PartnerAlreadyExistsException;
import com.celestabank.celestabankapi.repository.PartnerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class PartnerServiceImpl implements  PartnerService {
    private final PartnerRepository db;
    @Override
    public Partner addPartner(Partner partner) throws CustomerAlreadyExistsException {
        Partner existingPartner
                = db.findById(partner.getId())
                .orElse(null);
        if (existingPartner == null) {
            db.save(partner);
            return  partner;
        }else if( existingPartner.getPartnerName().equals(partner.getPartnerName())) throw new CustomerAlreadyExistsException("Partner already exixts!!");
        else throw new PartnerAlreadyExistsException("Partner already exixts!!");
    }

    @Override
    public Partner updatePartner(Partner partner)   {
        Partner existingPartner
                = db.findById(partner.getId())
                .orElse(null);
        if (existingPartner == null) {
            db.save(partner);
            return  partner;
        }else if( existingPartner.getPartnerName().equals(partner.getPartnerName())) throw new CustomerAlreadyExistsException("Partner already exixts!!");
        else throw new PartnerAlreadyExistsException("Partner already exixts!!");
    }

    @Override
    public boolean deletePartner(long partnerId)  {
        log.info("Suppression du partenaire  : "+partnerId + "  effectué avec succès");
        Partner existingPartner
                = db.findById(partnerId)
                .orElse(null);
        if (existingPartner == null)
            throw new NoSuchCustomerExistsException(
                    "No Such partner Exists!!");
        else {

            db.deleteById(partnerId);
            return true;
        }
    }

    @Override
    public Partner findPartnerById(long partnerId)  {
        log.info("Recherche du partner  : "+partnerId + "  effectué avec succès");
        Partner existingPartner
                = db.findById(partnerId)
                .orElse(null);
        if (existingPartner == null)
            throw new NoSuchCustomerExistsException(
                    "No Such partner Exists!!");
        else {

            db.findById(partnerId);
            return existingPartner;
        }
    }

    @Override
    public List<Partner> getAll() {
        return db.findAll();
    }
}
