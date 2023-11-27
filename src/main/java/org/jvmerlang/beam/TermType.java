package org.jvmerlang.beam;

import lombok.Getter;

@Getter
public enum TermType {
    LITERAL(0b000),
    INTEGER(0b001),
    ATOM(0b010),
    X_REG(0b011),
    Y_REG(0b100),
    LABEL(0b101),
    CHAR(0b110),
    EXT(0b111);

    private final byte tag;

    TermType(int tag) {
        this.tag = (byte) tag;
    }
}
