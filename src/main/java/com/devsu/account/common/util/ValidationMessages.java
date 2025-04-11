package com.devsu.account.common.util;

public class ValidationMessages {

    private ValidationMessages() {
        throw new IllegalStateException("Utility class");
    }

    public static final String REQUIRED_FIELD = "Este campo es obligatorio.";
    public static final String INVALID_AGE = "La edad debe ser mayor o igual a 0.";
    public static final String INVALID_PHONE = "El formato del teléfono no es válido.";
    public static final String PASSWORD_REQUIRED = "La contraseña no puede estar vacía.";
    public static final String STATE_REQUIRED = "El estado del cliente es requerido.";
    public static final String PERSON_REQUIRED = "Los datos personales son requeridos.";
}
