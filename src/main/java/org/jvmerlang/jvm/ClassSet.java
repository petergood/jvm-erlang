package org.jvmerlang.jvm;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@RequiredArgsConstructor
@Getter
@ToString
public class ClassSet {
    private final List<ClassDesc> classes;
}
