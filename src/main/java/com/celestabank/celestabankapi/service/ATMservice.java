package com.celestabank.celestabankapi.service;

import com.celestabank.celestabankapi.dto.AtmDTO;
import com.celestabank.celestabankapi.entity.ATM;

import java.util.List;

public interface ATMservice {


    AtmDTO addAtm(AtmDTO atmDTO);

    AtmDTO updateAtm(long atmId, AtmDTO atmDTO);

    boolean deleteAtm(long atmId);

    List<ATM> getAllAtm();

    ATM getAtmById(long atmId);

    AtmDTO viewAtmDTO(long atmId);
}
