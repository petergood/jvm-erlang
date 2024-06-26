package org.jvmerlang.beam.chunk;

import lombok.extern.slf4j.Slf4j;
import org.jvmerlang.beam.BeamModule;
import org.jvmerlang.beam.BeamReader;
import org.jvmerlang.beam.type.EAtom;
import org.jvmerlang.beam.exception.CorruptedBeamFileException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class AtomUtf8ChunkHandler implements ChunkHandler {
    @Override
    public void handleChunk(int chunkSize, BeamReader reader, BeamModule.Builder beamModuleBuilder) throws IOException {
        int atomCount = reader.readInt();
        List<EAtom> atoms = new ArrayList<>();

        for (int i = 0; i < atomCount; i++) {
            short atomSize = reader.readByte();
            String atom = reader.readUtf8String(atomSize);

            atoms.add(new EAtom(atom));
        }

        if (atoms.size() != atomCount) {
            throw new CorruptedBeamFileException(String.format("Invalid number of atoms: expected %d, got %d", atomCount, atoms.size()));
        }

        beamModuleBuilder.setAtomMap(atoms);
    }
}
