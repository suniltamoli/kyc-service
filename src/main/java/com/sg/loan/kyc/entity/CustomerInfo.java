
package com.sg.loan.kyc.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Data
@Entity
//@Table(name = "customer_kyc")
@Table(name = "customer_info",
        indexes = {@Index(name = "address_proof_id_index",  columnList="address_proof_id", unique = true),
                @Index(name = "kyc_number_index",  columnList="kyc_number", unique = true),
                @Index(name = "pan_number_index",  columnList="pan_number", unique = true),
                @Index(name = "identity_number_index", columnList="identity_number",     unique = true)})
@EntityListeners(AuditingEntityListener.class)
public class CustomerInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="pan_number", nullable=false, length = 10)
    private String panNumber;

    @Column(name="kyc_number", nullable=false, length = 100)
    private String kycNumber;

    @Column(name="first_name", nullable=false, length = 100)
    private String firstName;

    @Column(name="middle_name", length = 100)
    private String middleName;

    @Column(name="last_name", length = 100)
    private String lastName;

    @Column(name="father_name", nullable=false, length = 100)
    private String fatherName;

    @Column(name="mother_name", length = 100)
    private String motherName;

    @Column(name="dob", length = 50)
    private String dob;

    @Column(name="phone", length = 10)
    private String phone;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private MaritalStatus maritalStatus;

    @Column(length = 20)
    private String nationality;

    @Column(length = 100)
    @Enumerated(EnumType.STRING)
    private Occupation occupation;

    @Column(length = 30)
    @Enumerated(EnumType.STRING)
    private Sector sector;

    @Column(name="identity_type", length = 100)
    private IdentityType identityType;

    @Column(name="identity_number", nullable=false, length = 100)
    private String identityNumber;

    @Column(length = 30)
    @Enumerated(EnumType.STRING)
    private ProofOfAddress proofOfAddress;

//    @Index(name = "address_proof_id-index")
    @Column(name="address_proof_id", length = 100, nullable=false )
    private String addressProofId;

    @Column(length = 30)
    @Enumerated(EnumType.STRING)
    private AddressType addressType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address addresse;

}
