package org.jvmerlang.beam.chunk;

import lombok.extern.slf4j.Slf4j;
import org.jvmerlang.beam.BeamModule;
import org.jvmerlang.beam.BeamReader;

import java.io.IOException;
import java.util.Map;

@Slf4j
public class ChunkHandlerDispatcher {
    private final Map<ChunkType, ChunkHandler> chunkHandlers;

    public ChunkHandlerDispatcher() {
        this.chunkHandlers = Map.of(
          ChunkType.Code, new CodeChunkHandler()
        );
    }

    public void handleChunk(ChunkType type, int chunkSize, BeamReader reader, BeamModule beamModule) throws IOException {
        if (!this.chunkHandlers.containsKey(type)) {
            log.info("Ignoring chunk type " + type);
            reader.skipChunk(chunkSize);
            return;
        }

        this.chunkHandlers.get(type).handleChunk(chunkSize, reader, beamModule);
    }
}