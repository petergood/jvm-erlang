package org.jvmerlang.jvm;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class MethodInstructionSet {
    private final List<Instruction> instructions;
}
