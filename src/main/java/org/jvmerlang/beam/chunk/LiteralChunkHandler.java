package org.jvmerlang.beam.chunk;

import org.jvmerlang.beam.BeamModule;
import org.jvmerlang.beam.BeamReader;
import org.jvmerlang.beam.type.ELiteral;
import org.jvmerlang.beam.exception.CorruptedBeamFileException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public class LiteralChunkHandler implements ChunkHandler {
    private static final int BUFFER_SIZE = 1024;

    @Override
    public void handleChunk(int chunkSize, BeamReader reader, BeamModule.Builder beamModuleBuilder) throws IOException {
        InputStream inflatedChunk = inflateChunk(chunkSize, reader);
        List<ELiteral> literals = getLiterals(new BeamReader(inflatedChunk));

        beamModuleBuilder.setLiterals(literals);
    }

    private List<ELiteral> getLiterals(BeamReader reader) throws IOException {
        int count = reader.readInt();
        List<ELiteral> literals = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            int size = reader.readInt();
            byte[] literal = reader.readBytes(size);
            literals.add(new ELiteral(literal));
        }

        return literals;
    }

    private InputStream inflateChunk(int chunkSize, BeamReader reader) throws IOException {
        int uncompressedSize = reader.readInt();
        byte[] uncompressedBytes = new byte[uncompressedSize];
        int end = 0;
        byte[] compressedLiterals = reader.readBytes(chunkSize);

        try {
            Inflater inflater = new Inflater();
            inflater.setInput(compressedLiterals);

            byte[] buffer = new byte[BUFFER_SIZE];

            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                System.arraycopy(buffer, 0, uncompressedBytes, end, count);

                end += count;
            }

            return new ByteArrayInputStream(uncompressedBytes);
        } catch (DataFormatException e) {
            throw new CorruptedBeamFileException("Corrupted ZIP format");
        }
    }
}
