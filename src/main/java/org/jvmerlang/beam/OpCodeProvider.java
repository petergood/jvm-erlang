package org.jvmerlang.beam;

import org.jvmerlang.beam.exception.InvalidOpCodeException;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OpCodeProvider {
    private Map<Short, OpCode> lookupTable;

    public OpCodeProvider() {
        this.lookupTable = Arrays.stream(OpCode.values())
          .collect(Collectors.toMap(OpCode::getId, Function.identity()));
    }

    public OpCode getByCode(short opCode) {
        if (!lookupTable.containsKey(opCode)) {
            throw new InvalidOpCodeException();
        }

        return lookupTable.get(opCode);
    }
}
