package org.jvmerlang.beam.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class EString {
    private final byte[] bytes;
}
