package io.rbehjati.genie.model.rules;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import io.rbehjati.genie.model.Factor;

public class ValueForFactor implements Literal {

    private static int cnt = 0;
    private final Factor factor;
    private final List<String> values;

    public ValueForFactor(Factor factor, List<String> values) {
        this.factor = factor;
        this.values = values;
    }

    @Override
    public Term negate() {
        System.out.println(cnt++ + ") In ValueForFactor.negate " + factor.getName() + ": " + values.size() + "-" + factor.getClassCount());
        return new ValueForFactor(
            factor,
            factor.getEquivalenceClassLabels().stream()
                .filter(v -> !values.contains(v))
                .collect(Collectors.toList()));
    }

    public Factor getFactor() {
        return factor;
    }

    public List<String> getValues() {
        return values;
    }

    @Override
    public int compareTo(Object o) {
        return factor.getName().compareTo(((ValueForFactor) o).factor.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValueForFactor that = (ValueForFactor) o;
        return Objects.equals(factor, that.factor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(factor);
    }
}
