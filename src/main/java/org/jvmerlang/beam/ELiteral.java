package org.jvmerlang.beam;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ELiteral {
    private final byte[] literal;
}
