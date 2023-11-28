package org.jvmerlang.beam.chunk;

import org.jvmerlang.beam.BeamModule;
import org.jvmerlang.beam.BeamReader;

import java.io.IOException;

public class LocalTableChunkHandler extends FunctionChunkHandler {
    @Override
    public void handleChunk(int chunkSize, BeamReader reader, BeamModule.Builder beamModuleBuilder) throws IOException {
        beamModuleBuilder.setLocalFunctions(getFunctions(chunkSize, reader));
    }
}
