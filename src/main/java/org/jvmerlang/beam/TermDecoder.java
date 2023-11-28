package org.jvmerlang.beam;

import lombok.extern.slf4j.Slf4j;
import org.jvmerlang.beam.exception.CorruptedBeamFileException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
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
        TermType termType = getTermType(firstByte);

        if (termType == TermType.EXT) {
            if (TermExtType.isLiteral(firstByte)) {
                Term xReg = decodeSingleTerm();
                Term index = decodeSingleTerm();

                return Term.builder()
                  .termType(termType)
                  .embeddedTerms(List.of(xReg, index))
                  .build();
            }

            throw new UnsupportedOperationException("Extended tag not supported");
        }

        if ((firstByte >> 3) % 2 == 0) {
            return Term.builder()
              .termType(termType)
              .value( new short[] { (short) (firstByte >> 4) })
              .build();
        }

        if (((firstByte << 3) & 0b11000) == 0b11000) {
            if ((firstByte & 0b11111000) == 0b11111000) {
                throw new UnsupportedOperationException("Nested encoding not supported");
            }

            throw new UnsupportedOperationException("Multiple continuation bytes not supported");
        }

        if (((firstByte >> 3) & 0b11) == 0b01) {
            int nextByte = in.read();
            return Term.builder()
              .termType(termType)
              .value( new short[] { (short) ((firstByte & 0b11100000) >> 5), (short) nextByte })
              .build();
        }

        throw new UnsupportedOperationException("Unsupported operation");
    }

    private TermType getTermType(int b) {
        byte tag = (byte) (b & 0b111);

        if (!termTypeMap.containsKey(tag)) {
            throw new CorruptedBeamFileException(String.format("Unrecognized tag %d", tag));
        }

        return termTypeMap.get(tag);
    }

    private Term decodeSingleTerm() throws IOException {
        int b = in.read();
        TermType termType = getTermType(b);

        if ((b >> 3) % 2 == 1) {
            throw new UnsupportedOperationException("Carry over not supported");
        }

        return Term.builder()
          .termType(termType)
          .value( new short[] { (short) (b >> 4) })
          .build();
    }
}
