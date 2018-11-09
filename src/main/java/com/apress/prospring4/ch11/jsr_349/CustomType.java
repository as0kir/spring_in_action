package com.apress.prospring4.ch11.jsr_349;

public enum CustomType {
    INDIVIDUAL("I"), CORPORATE("C");

    private String code;
    CustomType(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return this.code;
    }
}
