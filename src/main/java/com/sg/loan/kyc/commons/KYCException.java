package com.sg.loan.kyc.commons;

import lombok.Data;

@Data
public class KYCException extends Exception {
    private final String message;
    private final String erroCode;

    public KYCException(String message, String erroCode) {
        super(message);
        this.message = message;
        this.erroCode = erroCode;
    }
}
