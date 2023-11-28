package org.jvmerlang.beam.chunk;

import org.jvmerlang.beam.BeamModule;
import org.jvmerlang.beam.BeamReader;
import org.jvmerlang.beam.EExport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExportTableChunkHandler implements ChunkHandler {
    @Override
    public void handleChunk(int chunkSize, BeamReader reader, BeamModule.Builder beamModuleBuilder) throws IOException {
        int count = reader.readInt();
        List<EExport> exports = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            int functionNameLabel = reader.readInt();
            int arity = reader.readInt();
            int label = reader.readInt();

            exports.add(new EExport(functionNameLabel, arity, label));
        }

        beamModuleBuilder.setExports(exports);
    }
}
