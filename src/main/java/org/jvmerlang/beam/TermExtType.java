package org.jvmerlang.beam;

import lombok.Getter;

@Getter
public enum TermExtType {
    LIST(0b0100),
    FP_REG(0b0110),
    ALLOC_LIST(0b1000),
    LITERAL(0b1010);

    private final byte tag;

    TermExtType(int tag) {
        this.tag = (byte) tag;
    }

    public static boolean isList(int b) {
        return (b >> 3) == LIST.tag;
    }

    public static boolean isFpReg(int b) {
        return (b >> 3) == FP_REG.tag;
    }

    public static boolean isAllocList(int b) {
        return (b >> 3) == ALLOC_LIST.tag;
    }

    public static boolean isLiteral(int b) {
        return (b >> 3) == LITERAL.tag;
    }
}
