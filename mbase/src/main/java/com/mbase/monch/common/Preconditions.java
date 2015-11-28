package com.mbase.monch.common;

/**
 * Created by monch on 15/11/11.
 */
public final class Preconditions {

    private Preconditions() {}

    public static <T> boolean isNotNull(T reference) {
        return reference != null;
    }

    public static <T> T checkNotNull(T reference) {
        return checkNotNull(reference, null);
    }

    public static <T> T checkNotNull(T reference, Object errorMessage) {
        if (reference == null) {
            throw new NullPointerException(String.valueOf(errorMessage));
        }
        return reference;
    }

}
