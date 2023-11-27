package org.jvmerlang.beam;

import lombok.RequiredArgsConstructor;
import org.jvmerlang.beam.exception.CorruptedBeamFileException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class BeamReader {
    private static final int CHUNK_ALIGN = 4;

    private final InputStream in;
    private final TermDecoder termDecoder;

    public BeamReader(InputStream in) {
        InputStream bufferedStream = new BufferedInputStream(in);

        this.in = bufferedStream;
        this.termDecoder = new TermDecoder(bufferedStream);
    }

    public short readByte() throws IOException {
        return (short) ((short) readBytesSafe(1)[0]);
    }

    public int readInt() throws IOException {
        return ByteBuffer.wrap(readBytesSafe(4)).getInt();
    }

    public String readString() throws IOException {
        return new String(readBytesSafe(4));
    }

    public String readUtf8String(int num) throws IOException {
        byte[] bytes = readBytesSafe(num);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public int getAvailableBytes() throws IOException {
        return in.available();
    }

    public void skipChunk(int chunkSize) throws IOException {
        in.skip(CHUNK_ALIGN * ((chunkSize + CHUNK_ALIGN - 1) / CHUNK_ALIGN));
    }

    public void beginReadingChunk(int chunkSize) {
        in.mark(chunkSize);
    }

    public void endReadingChunk(int chunkSize) throws IOException {
        in.reset();
        skipChunk(chunkSize);
    }

    public Term getNextTerm() throws IOException {
        return termDecoder.getNextTerm();
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
