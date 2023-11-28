package org.jvmerlang.beam.chunk;

import lombok.extern.slf4j.Slf4j;
import org.jvmerlang.beam.BeamModule;
import org.jvmerlang.beam.BeamReader;
import org.jvmerlang.beam.exception.CorruptedBeamFileException;
import org.jvmerlang.beam.external.ExternalDecoder;
import org.jvmerlang.beam.type.EList;

import java.io.IOException;

@Slf4j
public class MetaChunkHandler implements ChunkHandler {
    private final ExternalDecoder externalDecoder;

    public MetaChunkHandler() {
        this.externalDecoder = new ExternalDecoder();
    }

    @Override
    public void handleChunk(int chunkSize, BeamReader reader, BeamModule.Builder beamModuleBuilder) throws IOException {
        Object decoded = externalDecoder.decode(reader);

        if (!(decoded instanceof EList)) {
            throw new CorruptedBeamFileException(String.format("Expected EList in meta chunk, got %s", decoded.getClass().getName()));
        }

        beamModuleBuilder.setMetadata((EList) decoded);
    }
}
