package com.sg.loan.kyc.commons;

import lombok.Data;

import java.io.Serializable;

@Data
public class KYCError implements Serializable {
    private final String errorCode;
    private final String errorMessage;

    public KYCError(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
