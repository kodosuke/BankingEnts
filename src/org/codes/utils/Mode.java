package org.codes.utils;

public enum Mode {

    CREDIT ('+'),
    DEBIT ('-');

    public final char signature;

    Mode( char signature) {
        this.signature = signature;
    }
}
