package com.sg.loan.kyc.transformer;

import com.sg.loan.kyc.entity.*;
import com.sg.loan.kyc.model.CustomerDetails;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CustomerTransformer {
    public CustomerInfo transformCustomerDetails(CustomerDetails customerDetails) {
        CustomerInfo customerInfo = new CustomerInfo();
        com.sg.loan.kyc.model.Address address = customerDetails.getAddressDetails().getAddress();
        Address addressEntity = new Address();
        BeanUtils.copyProperties(address, addressEntity);
        customerInfo.setPanNumber(customerDetails.getPanNumber());
        customerInfo.setKycNumber("KYC"+ UUID.randomUUID().toString().replace("-", ""));
        customerInfo.setAddresse(addressEntity);
        customerInfo.setAddressProofId(customerDetails.getAddressDetails().getAddressProofId());
        customerInfo.setAddressType(AddressType.valueOf(customerDetails.getAddressDetails().getAddressType()));
        customerInfo.setDob(customerDetails.getPersonalDetails().getDob());
        customerInfo.setFatherName(customerDetails.getPersonalDetails().getFatherName());
        customerInfo.setFirstName(customerDetails.getPersonalDetails().getFirstName());
        customerInfo.setLastName(customerDetails.getPersonalDetails().getLastName());
        customerInfo.setGender(Gender.valueOf(customerDetails.getPersonalDetails().getGender()));
        customerInfo.setIdentityNumber(customerDetails.getIdentityDetails().getIdentityNumber());
        customerInfo.setMaritalStatus(MaritalStatus.valueOf(customerDetails.getPersonalDetails().getMaritalStatus().toUpperCase()));
        customerInfo.setMiddleName(customerDetails.getPersonalDetails().getMiddleName());
        customerInfo.setMotherName(customerDetails.getPersonalDetails().getMotherName());
        customerInfo.setIdentityType(IdentityType.valueOf(customerDetails.getIdentityDetails().getIdentityType().toUpperCase()));
        customerInfo.setNationality(customerDetails.getPersonalDetails().getNationality());
        customerInfo.setOccupation(Occupation.valueOf(customerDetails.getPersonalDetails().getOccupation().toUpperCase()));
        customerInfo.setProofOfAddress(ProofOfAddress.valueOf(customerDetails.getAddressDetails().getProofOfAddress().toUpperCase()));
        customerInfo.setPhone(customerDetails.getPersonalDetails().getPhone());
        return customerInfo;
    }
}
