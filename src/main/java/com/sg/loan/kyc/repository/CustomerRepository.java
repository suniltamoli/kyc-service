package com.sg.loan.kyc.repository;

import com.sg.loan.kyc.entity.CustomerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerInfo, Long> {

    @Query("SELECT u FROM CustomerInfo u WHERE u.kycNumber = ?1")
    List<CustomerInfo> getCustomerByKYCNumber(String kycNumber);

    @Query("SELECT u FROM CustomerInfo u WHERE u.addressProofId = ?1")
    List<CustomerInfo> getCustomerByAddressProofId(String addressProofId);


}
