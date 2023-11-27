package org.jvmerlang.beam;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
public class Term {
    private final TermType termType;
    private final short[] value;
}
