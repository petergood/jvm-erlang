package org.jvmerlang.jvm;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class ClassDesc {
    private final List<MethodInstructionSet> methods;
}
