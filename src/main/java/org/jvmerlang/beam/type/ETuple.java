package org.jvmerlang.beam.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class ETuple {
    private final int arity;
    private final List<Object> elements;
}
