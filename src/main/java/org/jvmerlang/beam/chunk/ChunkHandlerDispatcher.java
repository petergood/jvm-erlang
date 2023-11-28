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
          ChunkType.Code, new CodeChunkHandler(),
          ChunkType.AtU8, new AtomUtf8ChunkHandler(),
          ChunkType.LitT, new LiteralChunkHandler(),
          ChunkType.ExpT, new ExportTableChunkHandler(),
          ChunkType.StrT, new StringTableChunkHandler(),
          ChunkType.ImpT, new ImportTableChunkHandler(),
          ChunkType.LocT, new LocalTableChunkHandler()
        );
    }

    public void handleChunk(ChunkType type, int chunkSize, BeamReader reader, BeamModule.Builder beamModuleBuilder) throws IOException {
        if (!this.chunkHandlers.containsKey(type)) {
            log.info("Ignoring chunk type {}", type);
            return;
        }

        log.info("Handling chunk type {}", type);

        this.chunkHandlers.get(type).handleChunk(chunkSize, reader, beamModuleBuilder);
    }
}
