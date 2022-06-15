package com.celestabank.celestabankapi.service;

import com.celestabank.celestabankapi.entity.ATM;

import java.util.List;

public interface ATMservice {

     ATM addAtm(ATM atm);
     ATM updateAtm(ATM atm);
     boolean deleteAtm(long atmId);
     List<ATM> getAllAtm();
     ATM getAtmById(long atmId);
}
