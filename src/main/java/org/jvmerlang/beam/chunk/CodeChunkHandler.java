package org.jvmerlang.beam.chunk;

import lombok.extern.slf4j.Slf4j;
import org.jvmerlang.beam.BeamModule;
import org.jvmerlang.beam.BeamReader;
import org.jvmerlang.beam.OpCode;
import org.jvmerlang.beam.OpCodeProvider;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class CodeChunkHandler implements ChunkHandler {
    private final OpCodeProvider opCodeProvider;

    public CodeChunkHandler() {
        this.opCodeProvider = new OpCodeProvider();
    }

    @Override
    public void handleChunk(int chunkSize, BeamReader reader, BeamModule beamModule) throws IOException {
        short opCodeByte = reader.readByte();
        OpCode opCode = opCodeProvider.getByCode(opCodeByte);
    }
}
