package org.jvmerlang.beam;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@RequiredArgsConstructor
@ToString
public class ECode {
    private final List<EInstruction> instructions;
}
