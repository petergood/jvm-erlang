package org.jvmerlang.beam.external;

import org.jvmerlang.beam.BeamReader;
import org.jvmerlang.beam.type.EList;

import java.io.IOException;

public class EmptyListExternalDecoder implements TypedExternalDecoder<EList> {
    @Override
    public int getTag() {
        return 106;
    }

    @Override
    public EList decode(BeamReader reader) throws IOException {
        return EList.emptyList();
    }
}
