package com.sg.loan.kyc.services;

import com.sg.loan.kyc.commons.KYCException;
import com.sg.loan.kyc.model.CustomerDetails;

import java.security.KeyException;
import java.util.List;

public interface CustomerService {
    CustomerDetails saveCustomerData(CustomerDetails customerDetails ) throws KeyException, KYCException;
    CustomerDetails updateCustomerData(CustomerDetails customerDetails) throws KeyException;
    List<CustomerDetails> getAllCustomerList() throws KeyException;
    CustomerDetails getCustomerByAddressProofId(String addressProofId) throws KeyException;
}
