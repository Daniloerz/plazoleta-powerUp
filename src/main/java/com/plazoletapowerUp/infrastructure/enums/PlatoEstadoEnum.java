package com.plazoletapowerUp.infrastructure.enums;

public enum PlatoEstadoEnum {
    PENDIENTE("pendiente"),
    EN_PREPARACION("en preparacion"),
    ENTREGADO("entregado");

   private String dbValue;

    private PlatoEstadoEnum(String dbValue) {
        this.dbValue = dbValue;
    }

    public String getDbValue() {
        return dbValue;
    }
}
