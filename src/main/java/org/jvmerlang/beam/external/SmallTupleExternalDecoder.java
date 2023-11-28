package org.jvmerlang.beam.external;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jvmerlang.beam.BeamReader;
import org.jvmerlang.beam.type.ETuple;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class SmallTupleExternalDecoder implements TypedExternalDecoder<ETuple> {
    private final ExternalDecoder externalDecoder;

    @Override
    public int getTag() {
        return 104;
    }

    @Override
    public ETuple decode(BeamReader reader) throws IOException {
        short arity = reader.readByte();
        List<Object> elements = new ArrayList<>();

        for (int i = 0; i < arity; i++) {
            Object elem = externalDecoder.decodeBody(reader);
            elements.add(elem);
        }

        return new ETuple(arity, Collections.unmodifiableList(elements));
    }
}
