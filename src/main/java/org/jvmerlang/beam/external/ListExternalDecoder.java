package org.jvmerlang.beam.external;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jvmerlang.beam.BeamReader;
import org.jvmerlang.beam.type.EList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class ListExternalDecoder implements TypedExternalDecoder<EList> {
    private final ExternalDecoder externalDecoder;

    @Override
    public int getTag() {
        return 108;
    }

    @Override
    public EList decode(BeamReader reader) throws IOException {
        int length = reader.readInt();
        List<Object> elements = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            Object elem = externalDecoder.decodeBody(reader);
            elements.add(elem);
        }

        return new EList(length, Collections.unmodifiableList(elements));
    }
}
