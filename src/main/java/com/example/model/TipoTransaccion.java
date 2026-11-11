package com.example.model;

public enum TipoTransaccion {
    INGRESO,
    GASTO;

    public boolean isValid(String value) {
        for (TipoTransaccion tipo : TipoTransaccion.values()) {
            if (tipo.name().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}
