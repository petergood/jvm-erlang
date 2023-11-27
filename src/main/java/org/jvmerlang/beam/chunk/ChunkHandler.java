package org.jvmerlang.beam.chunk;

import org.jvmerlang.beam.BeamModule;
import org.jvmerlang.beam.BeamReader;

import java.io.IOException;
import java.io.InputStream;

public interface ChunkHandler {
    void handleChunk(int chunkSize, BeamReader reader, BeamModule beamModule) throws IOException;
}
