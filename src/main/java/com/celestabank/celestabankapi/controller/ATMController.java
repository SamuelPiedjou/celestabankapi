package com.celestabank.celestabankapi.controller;

import com.celestabank.celestabankapi.dto.AtmDTO;
import com.celestabank.celestabankapi.entity.ATM;
import com.celestabank.celestabankapi.exeption.*;
import com.celestabank.celestabankapi.service.ATMServiceImp;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/atm")
@RestController
@AllArgsConstructor
public class ATMController {
    private final ATMServiceImp atmServiceImp;

    @PostMapping("/add")
    @ApiOperation(value = "Ajout d'un ATM")
    public AtmDTO addATM(@RequestBody AtmDTO atmDTO)  {
        return atmServiceImp.addAtm(atmDTO);
    }

    @PutMapping("/update/{idAtm}")
    @ApiOperation(value = "MAJ d'un ATM")
    public AtmDTO updateAtm(@PathVariable long idAtm ,@RequestBody AtmDTO atmDTO) throws ATMAlreadyExistsException  {
            return atmServiceImp.updateAtm(idAtm,atmDTO);
    }

    @DeleteMapping("/delete/{atmId}")
    @ApiOperation(value = "SUPPRESSION D'UN ATM")
    public boolean deleteAtm(@PathVariable long atmId) throws ATMNotExistException {
            atmServiceImp.deleteAtm(atmId);
        return true;

    }

    @GetMapping("/find/{atmId}")
    @ApiOperation(value = "RECHERCHER UN  ATM")
    public ATM findAtmByID(@PathVariable long atmId) throws DetailsNotFoundException {
        return atmServiceImp.getAtmById(atmId);
    }
    @GetMapping("/allAtm")
    @ApiOperation(value = "LISTER LES ATM")
    public List<ATM> getAll()  {
        List<ATM> n = atmServiceImp.getAllAtm();
        return n;

    }


}
