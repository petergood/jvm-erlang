package org.jvmerlang.beam.exception;

public class CorruptedBeamFileException extends RuntimeException {
    public CorruptedBeamFileException() {
    }

    public CorruptedBeamFileException(String message) {
        super(message);
    }
}
