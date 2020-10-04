package com.sg.loan.kyc.commons;

import com.sg.loan.kyc.model.CustomerDetails;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class Validator {

    public void validateRequest(CustomerDetails customerDetails) throws KYCException {
        if(customerDetails.getAddressDetails() == null ) {
            throw new KYCException("Address details can not be null", "100002");
        }

        if(customerDetails.getAddressDetails()!= null &&  StringUtils.isEmpty(customerDetails.getAddressDetails().getAddressProofId())) {
            throw new KYCException("Address proof id is mandatory", "100003");
        }

        if(customerDetails.getIdentityDetails() == null) {
            throw new KYCException("Identity details can not be null", "100004");
        }

        if(customerDetails.getIdentityDetails() != null && StringUtils.isEmpty(customerDetails.getIdentityDetails().getIdentityNumber())) {
            throw new KYCException("Identity number is mandatory", "100005");
        }
        if(customerDetails.getPersonalDetails() == null) {
            throw new KYCException("Personal details can not be null", "100006");
        }

        if(StringUtils.isBlank(customerDetails.getPersonalDetails().getFirstName())) {
            throw new KYCException("First name can not be null", "100007");
        }
    }

}
