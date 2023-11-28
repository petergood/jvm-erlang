package org.jvmerlang.beam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jvmerlang.beam.exception.CorruptedBeamFileException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class TermDecoder {
    private final InputStream in;
    private Map<Byte, TermType> termTypeMap;

    public TermDecoder(InputStream in) {
        this.in = in;
        this.termTypeMap = Arrays.stream(TermType.values())
          .collect(Collectors.toMap(TermType::getTag, Function.identity()));
    }

    public Term getNextTerm() throws IOException {
        int firstByte = in.read();
        int tag = firstByte & 0b111;

        // label: 1
        // 00010000
        // 10011001

        if (tag == 0b111) {
            // TODO

            // 1010111

            log.info("fb {}", firstByte);
//            throw new UnsupportedOperationException("Extended tags not supported");

            if (((firstByte >> 3) & 0b1010) == 0b1010) {
                // extended label
                int next = in.read();
                log.info("ext label next: {}", next);
            }

            throw new UnsupportedOperationException("Extended tags not supported");
        }

        TermType termType = termTypeMap.get((byte) tag);

        if ((firstByte >> 3) % 2 == 0) {
            return new Term(termType, new short[] { (short) ((firstByte & 0b11110000) >> 4) });
        }

        if (((firstByte << 3) & 0b11000) == 0b11000) {
            if ((firstByte & 0b11111000) == 0b11111000) {
                // TODO
                throw new UnsupportedOperationException("Nested encoding not supported");
            }

            // TODO
            throw new UnsupportedOperationException("Multiple continuation bytes not supported");
        }

        if (((firstByte >> 3) & 0b11) == 0b01) {
            int nextByte = in.read();
            return new Term(termType, new short[] { (short) ((firstByte & 0b11100000) >> 5), (short) nextByte });
        }

        throw new UnsupportedOperationException("Unsupported operation");
    }
}
