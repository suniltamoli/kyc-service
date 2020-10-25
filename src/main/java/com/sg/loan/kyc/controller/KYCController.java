package com.sg.loan.kyc.controller;

import com.sg.loan.kyc.commons.KYCError;
import com.sg.loan.kyc.commons.KYCException;
import com.sg.loan.kyc.model.CustomerDetails;
import com.sg.loan.kyc.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.KeyException;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/carloan/customer")
public class KYCController {

    private final CustomerService customerService;

    public KYCController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/kyc")
    public ResponseEntity<?> createCustomerKYC(@Valid @RequestBody CustomerDetails customerDetails) {
        try {
            customerService.saveCustomerData(customerDetails);
        } catch (Exception e) {
            if(e instanceof KYCException) {
                KYCError kycError = new KYCError(((KYCException) e).getErroCode(), "Invalid request, " + ((KYCException) e).getMessage());
                return new ResponseEntity<KYCError>(kycError, HttpStatus.BAD_REQUEST);
            } else {
                KYCError kycError = new KYCError("5000100",   e.getMessage());
                return new ResponseEntity<KYCError>(kycError, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<CustomerDetails>(customerDetails, HttpStatus.OK);
    }

    @PutMapping("/kyc/{kyc_number}")
    public CustomerDetails updateCustomerKYC(@PathVariable(value = "kyc_number") String kycNumber, @Valid @RequestBody CustomerDetails customerDetails) throws KeyException {
        customerDetails.setKyc_number(kycNumber);
        return customerService.updateCustomerData(customerDetails);
    }

    @GetMapping("/kyc")
    public List<CustomerDetails> getAllCustomerList() throws KeyException {
        return customerService.getAllCustomerList();
    }


    @GetMapping("/kyc/addressId/{id}")
    public CustomerDetails getCustomerByAddressProofId(@PathVariable(value = "id") String addressProofId) throws KeyException {
        CustomerDetails customerDetails =  customerService.getCustomerByAddressProofId(addressProofId);
        return customerDetails;
    }

    @GetMapping("/kyc/{id}")
    public CustomerDetails getCustomerByKYCNumber(@PathVariable(value = "id") String kycNumber) throws KeyException {
        CustomerDetails customerDetails =  customerService.getCustomerByKYCNumber(kycNumber);
        return customerDetails;
    }

}
