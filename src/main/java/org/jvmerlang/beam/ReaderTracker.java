package org.jvmerlang.beam;

public class ReaderTracker {
    private final int target;
    private int current;

    public ReaderTracker(int target) {
        this.target = target;
        this.current = 0;
    }

    public void increment(int inc) {
        this.current += inc;
    }

    public boolean hasReachedTarget() {
        if (this.current > this.target) {
            throw new IllegalStateException(String.format("Tracking target missed - target: %s, current: %s", target, current));
        }

        return this.current == this.target;
    }
}
