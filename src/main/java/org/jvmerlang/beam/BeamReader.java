package org.jvmerlang.beam;

import org.jvmerlang.beam.exception.CorruptedBeamFileException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class BeamReader {
    private static final int CHUNK_ALIGN = 4;

    private final InputStream in;
    private final TermDecoder termDecoder;
    private ReaderTracker readerTracker;

    public BeamReader(InputStream in) {
        InputStream bufferedStream = new BufferedInputStream(in);

        this.in = bufferedStream;
        this.termDecoder = new TermDecoder(bufferedStream);
    }

    public byte[] readBytes(int num) throws IOException {
        return readBytesSafe(num);
    }

    public short readByte() throws IOException {
        return readBytesSafeAsShort(1)[0];
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

    public void beginByteTracking(int totalSize) {
        if (this.readerTracker != null) {
            throw new IllegalStateException("Attempted to start tracking when already in tracking state");
        }

        this.readerTracker = new ReaderTracker(totalSize);
    }

    public void endByteTracking() {
        if (this.readerTracker == null) {
            throw new IllegalStateException("Attempted to end tracking when no tracking active");
        }

        this.readerTracker = null;
    }

    public boolean isTrackingFinished() {
        return this.readerTracker.hasReachedTarget();
    }

    private byte[] readBytesSafe(int num) throws IOException {
        byte[] bytes = new byte[num];
        int bytesRead = in.read(bytes);

        if (bytesRead != num) {
            throw new CorruptedBeamFileException(
              String.format("Invalid number of bytes - expected %d, got %d", num, bytesRead)
            );
        }

        if (this.readerTracker != null) {
            this.readerTracker.increment(bytesRead);
        }

        return bytes;
    }

    private short[] readBytesSafeAsShort(int num) throws IOException {
        byte[] bytes = readBytesSafe(num);
        short[] bytesShort = new short[num];

        for (int i = 0; i < bytes.length; i++) {
            bytesShort[i] = (short) (bytes[i] & 0xFF);
        }

        return bytesShort;
    }
}
