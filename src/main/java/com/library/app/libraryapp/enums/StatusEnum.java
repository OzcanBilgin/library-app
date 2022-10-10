package com.library.app.libraryapp.enums;

public enum StatusEnum {
    SUCCESS(200),
    FAIL(500);

    private final int value;

    StatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
