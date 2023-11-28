package org.jvmerlang.jvm;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Instruction {
    private final JOpCode opCode;
}
