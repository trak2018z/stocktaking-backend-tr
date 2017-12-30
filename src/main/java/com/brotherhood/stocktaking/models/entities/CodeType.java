package com.brotherhood.stocktaking.models.entities;

public enum CodeType {
    QR,
    BARCODE,
    NONE;

    public static CodeType getByName(String name) {
        for (CodeType codeType : values()) {
            if (codeType.name().toLowerCase().equals(name.toLowerCase())) {
                return codeType;
            }
        }
        return null;
    }
}
