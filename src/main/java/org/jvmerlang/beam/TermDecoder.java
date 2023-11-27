package org.jvmerlang.beam;

import lombok.RequiredArgsConstructor;

import java.io.InputStream;

@RequiredArgsConstructor
public class TermDecoder {
    private static final byte LITERAL_TAG = 0b000;
    private static final byte INTEGER_TAG = 0b001;
    private static final byte ATOM_TAG =    0b010;
    private static final byte X_REG_TAG =   0b011;
    private static final byte Y_REG_TAG =   0b100;
    private static final byte LABEL_TAG =   0b101;
    private static final byte CHAR_TAG =    0b110;
    private static final byte EXT_TAG =     0b111;

    private final InputStream in;
}
