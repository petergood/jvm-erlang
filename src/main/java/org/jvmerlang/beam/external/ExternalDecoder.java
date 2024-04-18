package org.jvmerlang.beam.external;

import org.jvmerlang.beam.BeamReader;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ExternalDecoder {
    private static final short MAGIC_BYTE = 131;

    private final Map<Integer, TypedExternalDecoder<?>> decoderMap;

    public ExternalDecoder() {
        List<TypedExternalDecoder<?>> decoders = List.of(
          new SmallAtomUtf8ExternalDecoder(),
          new ListExternalDecoder(this),
          new SmallTupleExternalDecoder(this),
          new EmptyListExternalDecoder()
        );

        this.decoderMap = decoders.stream()
          .collect(Collectors.toMap(TypedExternalDecoder::getTag, Function.identity()));
    }

    private TypedExternalDecoder<?> getExternalDecoder(int tag) {
        if (!decoderMap.containsKey(tag)) {
            throw new CorruptedExternalRepresentation(String.format("Decoder for tag %d not found", tag));
        }

        return decoderMap.get(tag);
    }

    public Object decode(BeamReader reader) throws IOException {
        short magicByte = reader.readByte();

        if (magicByte != MAGIC_BYTE) {
            throw new CorruptedExternalRepresentation(String.format("Unrecognised magic byte %d", magicByte));
        }

        return decodeBody(reader);
    }

    public Object decodeBody(BeamReader reader) throws IOException {
        int tag = reader.readByte();
        TypedExternalDecoder<?> decoder = getExternalDecoder(tag);

        return decoder.decode(reader);
    }
}
