package org.jvmerlang.beam;

import org.jvmerlang.beam.exception.CorruptedBeamFileException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class BeamReader {
    private static final int CHUNK_ALIGN = 4;
    private static final String IFF_HEADER = "FOR1";
    private static final String BEAM_HEADER = "BEAM";

    public void read(InputStream in) throws IOException {
        String iffContainerHeader = new String(readBytesSafe(4, in));

        if (!iffContainerHeader.equals(IFF_HEADER)) {
            throw new CorruptedBeamFileException("Invalid file type");
        }

        int fileSize = ByteBuffer.wrap(readBytesSafe(4, in)).getInt();

        if (in.available() != fileSize) {
            throw new CorruptedBeamFileException("Invalid file length");
        }

        String beamSectionHeader = new String(readBytesSafe(4, in));

        if (!beamSectionHeader.equals(BEAM_HEADER)) {
            throw new CorruptedBeamFileException("Invalid BEAM header");
        }

        while (in.available() > 0) {
            String chunkName = new String(readBytesSafe(4, in));
            int chunkSize = ByteBuffer.wrap(readBytesSafe(4, in)).getInt();

            System.out.println("Chunk: " + chunkName + "; size: " + chunkSize);
            in.skip(CHUNK_ALIGN * ((chunkSize + CHUNK_ALIGN - 1) / CHUNK_ALIGN));
        }
    }

    private byte[] readBytesSafe(int num, InputStream in) throws IOException {
        byte[] bytes = new byte[num];
        int bytesRead = in.read(bytes);

        if (bytesRead != num) {
            throw new CorruptedBeamFileException(
              String.format("Invalid number of bytes - expected %d, got %d", num, bytesRead)
            );
        }

        return bytes;
    }
}
