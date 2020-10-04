
package com.sg.loan.kyc.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@Table(name = "address")
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="address_line1", nullable=false, length = 100)
    private String addressLine1;
    @Column(name="address_line2", nullable=false, length = 100)
    private String addressLine2;
    @Column(name="address_line3", nullable=false, length = 100)
    private String addressLine3;
    @Column(name="city", nullable=false, length = 50)
    private String city;
    @Column(name="postal", nullable=false, length = 10)
    private String postal;
    @Column(name="district", nullable=false, length = 50)
    private String district;
    @Column(name="state", nullable=false, length = 50)
    private String state;
    @Column(name="country", nullable=false, length = 50)
    private String country;

}
