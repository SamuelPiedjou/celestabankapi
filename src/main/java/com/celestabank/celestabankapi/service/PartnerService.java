package com.celestabank.celestabankapi.service;

import com.celestabank.celestabankapi.entity.Customer;
import com.celestabank.celestabankapi.entity.Partner;
import com.celestabank.celestabankapi.exeption.CustomerAlreadyExistsException;
import com.celestabank.celestabankapi.exeption.NoSuchCustomerExistsException;

import java.util.List;

public interface PartnerService {
      Partner addPartner(Partner partner)  ;

    Partner updatePartner(Partner partner)  ;

    boolean deletePartner(long partnerId)  ;

    Partner findPartnerById(long partnerId)  ;

    List<Partner> getAll();

}
