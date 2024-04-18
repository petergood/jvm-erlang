package org.jvmerlang.beam.external;

import org.jvmerlang.beam.BeamReader;

import java.io.IOException;

public interface TypedExternalDecoder<T> {
    int getTag();
    T decode(BeamReader reader) throws IOException;
}
