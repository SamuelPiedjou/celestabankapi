package com.celestabank.celestabankapi.controller;

import com.celestabank.celestabankapi.entity.Souscrit;
import com.celestabank.celestabankapi.exeption.DetailsNotFoundException;
import com.celestabank.celestabankapi.exeption.InvalidDetailsException;
import com.celestabank.celestabankapi.service.SouscritServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RequestMapping("/souscrit")
@RestController
@AllArgsConstructor
public class SouscritController {

    SouscritServiceImpl souscritService;

    @PostMapping("/add")
    public Souscrit addNominee(@RequestBody Souscrit nominee) throws InvalidDetailsException {
        Souscrit n = null;
        try {
            n = souscritService.addNominee(nominee);
        } catch (Exception e) {
            throw new InvalidDetailsException("The details given are not valid!");
        }
        return n;
    }

    @PutMapping("update")
    public Souscrit updateNominee(@RequestBody Souscrit nominee) throws InvalidDetailsException {
        Souscrit n = null;
        try {
            n = souscritService.updateNominee(nominee);
        } catch (Exception e) {
            throw new InvalidDetailsException("The details given are not valid!");
        }
        return n;
    }

    @DeleteMapping("delete/{nomineeId}")
    public List<Souscrit> deleteNominee(@PathVariable int nomineeId) throws DetailsNotFoundException {
        List<Souscrit> n = null;
        try {
            n = souscritService.deleteNominee(nomineeId);
        } catch (Exception e) {
            throw new DetailsNotFoundException("The given ID is not found!");
        }
        return n;
    }

    @GetMapping("find/{nomineeId}")
    public Souscrit findNomineeById(@PathVariable int nomineeId) throws DetailsNotFoundException {
        Souscrit n = null;
        try {
            n = souscritService.findNomineeById(nomineeId);
        } catch (Exception e) {
            throw new DetailsNotFoundException("The given ID is not found!");
        }
        return n;
    }

    @GetMapping("all/{accountId}")
    public Set<Souscrit> listAllNominees(@PathVariable int accountId)  {
        Set<Souscrit> n = null;
        try {
            n = souscritService.listAllNominees(accountId);
            if (n.isEmpty()) {
               return null;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return n;
    }
}
