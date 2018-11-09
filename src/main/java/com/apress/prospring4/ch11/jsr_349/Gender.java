package com.apress.prospring4.ch11.jsr_349;

public enum Gender {
    MALE("M"), FEMALE("F");

    private String code;
    Gender(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return this.code;
    }
}
