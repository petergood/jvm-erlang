package org.jvmerlang.beam.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class EList {
    private final int length;
    private final List<Object> objects;

    public static EList emptyList() {
        return new EList(0, Collections.emptyList());
    }
}
