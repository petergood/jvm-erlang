package org.jvmerlang.boot;

import org.jvmerlang.beam.BeamDecoder;

import java.io.*;

public class ConsoleApplication {
    public static void main(String[] args) throws IOException {
        File initialFile = new File("test_module.beam");

        try (InputStream targetStream = new FileInputStream(initialFile)) {
            BeamDecoder beamDecoder = new BeamDecoder(targetStream);
            beamDecoder.read();
        }
    }
}
