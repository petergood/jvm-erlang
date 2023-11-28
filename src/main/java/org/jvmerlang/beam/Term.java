package org.jvmerlang.beam;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@Builder
@ToString
public class Term {
    private final TermType termType;
    private short[] value;
    private List<Term> embeddedTerms;
}
