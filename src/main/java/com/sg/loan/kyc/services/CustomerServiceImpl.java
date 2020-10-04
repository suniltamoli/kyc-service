package com.sg.loan.kyc.services;

import com.sg.loan.kyc.commons.KYCException;
import com.sg.loan.kyc.commons.Validator;
import com.sg.loan.kyc.entity.CustomerInfo;
import com.sg.loan.kyc.model.*;
import com.sg.loan.kyc.repository.CustomerRepository;
import com.sg.loan.kyc.transformer.CustomerTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.security.KeyException;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    private CustomerTransformer transformer;
    private Validator validator;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerTransformer transformer, Validator validator) {
        this.customerRepository = customerRepository;
        this.transformer = transformer;
        this.validator = validator;
    }

    @Override
    public CustomerDetails saveCustomerData(CustomerDetails customerDetails) throws KYCException{
        try {
            validator.validateRequest(customerDetails);
            CustomerInfo customerInfo = customerRepository.save(transformer.transformCustomerDetails(customerDetails));
            customerDetails.setKyc_number(customerInfo.getKycNumber());
        } catch (Exception e) {
            if(e instanceof DataIntegrityViolationException) {
                log.error("Customer already exist with either identity or address proof id");
                KYCException kycException = new KYCException(e.getMessage(), "200001");
                throw kycException;
            } else {
                throw e;
            }
        }
        return customerDetails;

    }

    @Override
    public CustomerDetails updateCustomerData(CustomerDetails customerDetails) throws KeyException {
         List<CustomerInfo> customerInfoList = customerRepository.getCustomerByKYCNumber(customerDetails.getKyc_number());
         if(!customerInfoList.isEmpty()) {
             CustomerInfo customerInfo = customerInfoList.get(0);
             // need to copy all fields to customerDetails from customerInfo . As of now only updating phone
             customerInfo.setPhone(customerDetails.getPersonalDetails().getPhone());
             customerRepository.save(customerInfo);
         }
         return customerDetails;
    }

    @Override
    public List<CustomerDetails> getAllCustomerList() throws KeyException {
        List<CustomerInfo> customerInfoList =  customerRepository.findAll();
        List<CustomerDetails> customerDetailsList = new ArrayList<>();
        for (CustomerInfo customerInfo : customerInfoList) {
            customerDetailsList.add(getCustomerDetails(customerInfo));
        }
        return customerDetailsList;
    }

    @Override
    public CustomerDetails getCustomerByAddressProofId(String addressProofId) throws KeyException{
        List<CustomerInfo> customerInfoList = customerRepository.getCustomerByAddressProofId(addressProofId);
        CustomerInfo customerInfo =  customerInfoList.get(0);
        CustomerDetails customerDetails = getCustomerDetails( customerInfo);
        return customerDetails;
    }

    private CustomerDetails getCustomerDetails( CustomerInfo customerInfo) {
        Address address = new Address();
        BeanUtils.copyProperties(customerInfo.getAddresse(), address);
        CustomerDetails customerDetails = new CustomerDetails();
        AddressDetails addressDetails = new AddressDetails();
        addressDetails.setAddress(address);
        addressDetails.setAddressProofId(customerInfo.getAddressProofId());
        addressDetails.setAddressType(customerInfo.getAddressType().name());
        addressDetails.setProofOfAddress(customerInfo.getProofOfAddress().name());
        customerDetails.setAddressDetails(addressDetails);
        customerDetails.setKyc_number(customerInfo.getKycNumber());
        IdentityDetails identityDetails = new IdentityDetails();
        identityDetails.setIdentityNumber(customerInfo.getIdentityNumber());
        identityDetails.setIdentityType(customerInfo.getIdentityType().name());
        customerDetails.setIdentityDetails(identityDetails);
        PersonalDetails personalDetails = new PersonalDetails();
        BeanUtils.copyProperties(customerInfo, personalDetails);
        customerDetails.setPersonalDetails(personalDetails);
        return customerDetails;
    }
}
