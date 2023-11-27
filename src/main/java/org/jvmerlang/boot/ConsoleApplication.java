package org.jvmerlang.boot;

import lombok.extern.slf4j.Slf4j;
import org.jvmerlang.beam.BeamDecoder;
import org.jvmerlang.beam.BeamModule;

import java.io.*;

@Slf4j
public class ConsoleApplication {
    public static void main(String[] args) throws IOException {
        File initialFile = new File("test_module.beam");

        try (InputStream targetStream = new FileInputStream(initialFile)) {
            BeamDecoder beamDecoder = new BeamDecoder(targetStream);
            BeamModule module = beamDecoder.read();

            log.info(module.toString());
        }
    }
}
