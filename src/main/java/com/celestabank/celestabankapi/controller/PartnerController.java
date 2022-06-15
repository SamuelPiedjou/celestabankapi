package com.celestabank.celestabankapi.controller;

import com.celestabank.celestabankapi.entity.Customer;
import com.celestabank.celestabankapi.entity.Partner;
import com.celestabank.celestabankapi.exeption.DetailsNotFoundException;
import com.celestabank.celestabankapi.exeption.PartnerAlreadyExistsException;
import com.celestabank.celestabankapi.exeption.PartnerNotExistException;
import com.celestabank.celestabankapi.service.CustomerServiceImpl;
import com.celestabank.celestabankapi.service.PartnerServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/partner")
@AllArgsConstructor
@RestController
public class PartnerController {
    private final PartnerServiceImpl partnerService;
    @PostMapping("/add")
    @ApiOperation(value = "AJOUTER UN PARTENAIRE")
    public Partner addPartner(@RequestBody Partner partner) throws PartnerAlreadyExistsException {
        Partner n = null;
        try {
            n = partnerService.addPartner(partner);
        } catch (Exception e) {
            e.getMessage();
        }
        return n;

    }

    @PutMapping("/update")
    @ApiOperation(value = "MAJ D'UN PARTNER")
    public Partner updateAtm(@RequestBody Partner partner) throws PartnerAlreadyExistsException  {
        Partner n = null;
        try {
            n = partnerService.updatePartner(partner);
        } catch (Exception e) {
            e.getMessage();
        }
        return n;
    }

    @DeleteMapping("/delete/{partnerId}")
    @ApiOperation(value = "SUPPRIMER UN PARTNER")
    public boolean deletePartner(@PathVariable long partnerId) throws PartnerNotExistException {
        boolean n = false;
        try {
            partnerService.deletePartner(partnerId);
        } catch (Exception e) {

        }
        return true;

    }

    @GetMapping("/find/{partnerId}")
    @ApiOperation(value = "RECHERCHER UN PARTENAIRE SOUS LA REFERENCE DE SON ID")
    public Partner findPartnerByID(@PathVariable long partnerId) throws DetailsNotFoundException {
        Partner n =  partnerService.findPartnerById(partnerId);
        return n;

    }
    @GetMapping("/allPartner")
    @ApiOperation(value = "LISTER TOUS LES PARTENAIRES")
    public List<Partner> allPartner()  {
        List<Partner> n =  partnerService.getAll();
        return n;

    }


}
