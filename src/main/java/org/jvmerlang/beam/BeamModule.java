package org.jvmerlang.beam;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@RequiredArgsConstructor
@ToString
public class BeamModule {
    private final List<EAtom> atomMap;

    public static class Builder {
        private List<EAtom> atomMap;

        public Builder setAtomMap(List<EAtom> atoms) {
            // There can only be one atom map per module
            if (this.atomMap != null) {
                throw new IllegalArgumentException("Atom map already set");
            }

            this.atomMap = atoms;

            return this;
        }

        public BeamModule build() {
            return new BeamModule(
              atomMap
            );
        }
    }
}
