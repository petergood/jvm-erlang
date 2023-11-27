package org.jvmerlang.beam.chunk;

import lombok.extern.slf4j.Slf4j;
import org.jvmerlang.beam.*;

import java.io.IOException;

@Slf4j
public class CodeChunkHandler implements ChunkHandler {
    private final OpCodeProvider opCodeProvider;

    public CodeChunkHandler() {
        this.opCodeProvider = new OpCodeProvider();
    }

    @Override
    public void handleChunk(int chunkSize, BeamReader reader, BeamModule.Builder beamModuleBuilder) throws IOException {
        int subSize = reader.readInt();
        int instructionSet = reader.readInt();
        int opCodeMax = reader.readInt();
        int labelCount = reader.readInt();
        int functionCount = reader.readInt();

//        int toSkip = subSize * 2 - 4 * 32;
//        reader.skip(toSkip);

//        while (true) {
        for (int j = 0; j < 2; j++) {
            short code = reader.readByte();

            log.info("op: {}", code);

            OpCode opCode = opCodeProvider.getByCode(code);
            log.info("opcode: {}", opCode);

            for (int i = 0; i < opCode.getArity(); i++) {
                Term term = reader.getNextTerm();
                log.info("term: {}", term);
            }
//        }
        }
    }
}
