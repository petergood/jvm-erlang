package org.jvmerlang.beam;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@RequiredArgsConstructor
@ToString
public class EInstruction {
    private final OpCode opCode;
    private final List<Term> terms;
}
