package org.jvmerlang.beam.chunk;

import lombok.extern.slf4j.Slf4j;
import org.jvmerlang.beam.*;
import org.jvmerlang.beam.exception.CorruptedBeamFileException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        int expectedLabelCount = reader.readInt();
        int expectedFunctionCount = reader.readInt();

        int functionCount = 0;
        int labelCount = 0;

        List<EInstruction> instructions = new ArrayList<>();

        reader.beginByteTracking(chunkSize - subSize);

        while (!reader.isTrackingFinished()) {
            short code = reader.readByte();

            OpCode opCode = opCodeProvider.getByCode(code);

            if (opCode.equals(OpCode.op_int_code_end)) {
                break;
            }

            if (opCode.equals(OpCode.op_func_info)) {
                functionCount++;
            }

            if (opCode.equals(OpCode.op_label)) {
                labelCount++;
            }

            List<Term> terms = new ArrayList<>();
            for (int i = 0; i < opCode.getArity(); i++) {
                Term term = reader.getNextTerm();
                terms.add(term);
            }

            instructions.add(new EInstruction(opCode, terms));
        }

        if (functionCount != expectedFunctionCount) {
            throw new CorruptedBeamFileException(String.format("Expected %d functions, found %d", expectedFunctionCount, functionCount));
        }

        if (labelCount != expectedLabelCount - 1) {
            throw new CorruptedBeamFileException(String.format("Expected %d labels, found %d", expectedLabelCount, labelCount));
        }

        reader.endByteTracking();

        beamModuleBuilder.setCode(new ECode(instructions));
    }
}
