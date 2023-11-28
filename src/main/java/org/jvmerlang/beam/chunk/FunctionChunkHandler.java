package org.jvmerlang.beam.chunk;

import org.jvmerlang.beam.BeamReader;
import org.jvmerlang.beam.type.EFunction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class FunctionChunkHandler implements ChunkHandler {
    public List<EFunction> getFunctions(int chunkSize, BeamReader reader) throws IOException {
        int count = reader.readInt();
        List<EFunction> functions = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            int moduleName = reader.readInt();
            int functionName = reader.readInt();
            int arity = reader.readInt();

            functions.add(new EFunction(moduleName, functionName, arity));
        }

        return functions;
    }
}
