package org.jvmerlang.beam;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class EFunction {
    private final int moduleName;
    private final int functionName;
    private final int arity;
}
