package org.jvmerlang.beam;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
public class EExport {
    private final int functionName;
    private final int arity;
    private final int label;
}
