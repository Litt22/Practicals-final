package com.example.lib.exception;

public class PrescriptionRequiredException extends RuntimeException {
    public PrescriptionRequiredException(String message) {
        super(message);
    }
}

