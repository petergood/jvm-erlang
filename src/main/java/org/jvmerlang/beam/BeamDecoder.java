package org.jvmerlang.beam;

import org.jvmerlang.beam.chunk.ChunkHandlerDispatcher;
import org.jvmerlang.beam.chunk.ChunkType;
import org.jvmerlang.beam.exception.CorruptedBeamFileException;

import java.io.IOException;
import java.io.InputStream;

public class BeamDecoder {
    private static final String IFF_HEADER = "FOR1";
    private static final String BEAM_HEADER = "BEAM";

    private final ChunkHandlerDispatcher chunkHandlerDispatcher;
    private final BeamReader reader;

    public BeamDecoder(InputStream in) {
        this.chunkHandlerDispatcher = new ChunkHandlerDispatcher();
        this.reader = new BeamReader(in);
    }

    public void read() throws IOException {
        String iffContainerHeader = reader.readString();

        if (!iffContainerHeader.equals(IFF_HEADER)) {
            throw new CorruptedBeamFileException("Invalid file type");
        }

        int fileSize = reader.readInt();

        if (reader.getAvailableBytes() != fileSize) {
            throw new CorruptedBeamFileException("Invalid file length");
        }

        String beamSectionHeader = reader.readString();

        if (!beamSectionHeader.equals(BEAM_HEADER)) {
            throw new CorruptedBeamFileException("Invalid BEAM header");
        }

        BeamModule module = new BeamModule();

        while (reader.getAvailableBytes() > 0) {
            String chunkName = reader.readString();

            try {
                ChunkType chunkType = ChunkType.valueOf(chunkName);

                int chunkSize = reader.readInt();

                chunkHandlerDispatcher.handleChunk(chunkType, chunkSize, reader, module);
            } catch (IllegalArgumentException ex) {
                throw new CorruptedBeamFileException(String.format("Invalid chunk type %s", chunkName));
            }
        }
    }
}