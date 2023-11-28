package org.jvmerlang.beam.chunk;

import lombok.extern.slf4j.Slf4j;
import org.jvmerlang.beam.BeamModule;
import org.jvmerlang.beam.BeamReader;
import org.jvmerlang.beam.type.EString;

import java.io.IOException;
import java.util.List;

@Slf4j
public class StringTableChunkHandler implements ChunkHandler {
    @Override
    public void handleChunk(int chunkSize, BeamReader reader, BeamModule.Builder beamModuleBuilder) throws IOException {
        if (chunkSize == 0) {
            return;
        }

        byte[] bytes = reader.readBytes(chunkSize);

        // TODO: splitting
        beamModuleBuilder.setStrings(List.of(new EString(bytes)));
    }
}
