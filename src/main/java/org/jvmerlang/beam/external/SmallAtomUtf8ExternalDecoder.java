package org.jvmerlang.beam.external;

import org.jvmerlang.beam.BeamReader;
import org.jvmerlang.beam.type.EAtom;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class SmallAtomUtf8ExternalDecoder implements TypedExternalDecoder<EAtom> {
    @Override
    public int getTag() {
        return 119;
    }

    @Override
    public EAtom decode(BeamReader reader) throws IOException {
        short len = reader.readByte();
        byte[] atomBytes = reader.readBytes(len);

        return new EAtom(new String(atomBytes, StandardCharsets.UTF_8));
    }
}
