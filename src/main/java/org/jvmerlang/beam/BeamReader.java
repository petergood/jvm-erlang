package org.jvmerlang.beam;

import lombok.RequiredArgsConstructor;
import org.jvmerlang.beam.exception.CorruptedBeamFileException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

@RequiredArgsConstructor
public class BeamReader {
    private static final int CHUNK_ALIGN = 4;

    private final InputStream in;

    public short readByte() throws IOException {
        return (short) ((short) readBytesSafe(1)[0]);
    }

    public int readInt() throws IOException {
        return ByteBuffer.wrap(readBytesSafe(4)).getInt();
    }

    public String readString() throws IOException {
        return new String(readBytesSafe(4));
    }

    public int getAvailableBytes() throws IOException {
        return in.available();
    }

    public void skipChunk(int chunkSize) throws IOException {
        in.skip(CHUNK_ALIGN * ((chunkSize + CHUNK_ALIGN - 1) / CHUNK_ALIGN));
    }

    private byte[] readBytesSafe(int num) throws IOException {
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
