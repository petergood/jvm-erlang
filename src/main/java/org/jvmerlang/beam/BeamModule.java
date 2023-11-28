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
    private final List<ELiteral> literals;
    private final List<EExport> exports;
    private final List<EString> strings;
    private final List<EFunction> imports;
    private final List<EFunction> localFunctions;
    private final ECode code;

    public static class Builder {
        private List<EAtom> atomMap;
        private List<ELiteral> literals;
        private List<EExport> exports;
        private List<EString> strings;
        private List<EFunction> imports;
        private List<EFunction> localFunctions;
        private ECode code;

        public Builder setAtomMap(List<EAtom> atoms) {
            // There can only be one atom map per module
            if (this.atomMap != null) {
                throw new IllegalArgumentException("Atom map already set");
            }

            this.atomMap = atoms;

            return this;
        }

        public Builder setLiterals(List<ELiteral> literals) {
            if (this.literals != null) {
                throw new IllegalArgumentException("Literal table already set");
            }

            this.literals = literals;

            return this;
        }

        public Builder setExports(List<EExport> exports) {
            if (this.exports != null) {
                throw new IllegalArgumentException("Export table already set");
            }

            this.exports = exports;

            return this;
        }

        public Builder setStrings(List<EString> strings) {
            if (this.strings != null) {
                throw new IllegalArgumentException("String table already set");
            }

            this.strings = strings;

            return this;
        }

        public Builder setImports(List<EFunction> imports) {
            if (this.imports != null) {
                throw new IllegalArgumentException("Import table already set");
            }

            this.imports = imports;

            return this;
        }

        public Builder setLocalFunctions(List<EFunction> localFunctions) {
            if (this.localFunctions != null) {
                throw new IllegalArgumentException("Local function table already set");
            }

            this.localFunctions = localFunctions;

            return this;
        }

        public Builder setCode(ECode code) {
            if (this.code != null) {
                throw new IllegalArgumentException("Code already set");
            }

            this.code = code;

            return this;
        }

        public BeamModule build() {
            return new BeamModule(
              atomMap,
              literals,
              exports,
              strings,
              imports,
              localFunctions,
              code
            );
        }
    }
}
