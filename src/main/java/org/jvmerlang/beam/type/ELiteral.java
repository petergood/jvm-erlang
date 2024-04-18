package org.jvmerlang.beam.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ELiteral {
    private final byte[] literal;
}
