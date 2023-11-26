package org.jvmerlang.boot;

import org.jvmerlang.beam.BeamReader;

import java.io.*;

public class ConsoleApplication {
    public static void main(String[] args) throws IOException {
        File initialFile = new File("test_module.beam");

        try (InputStream targetStream = new FileInputStream(initialFile)) {
            BeamReader beamReader = new BeamReader();
            beamReader.read(targetStream);
        }
    }
}
