package io.rbehjati.genie.model.rules;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import io.rbehjati.genie.model.Feature;

public class ValueForFeature implements Literal {

    private final Feature feature;
    private final List<String> values;

    public ValueForFeature(Feature feature, List<String> values) {
        this.feature = feature;
        this.values = values;
    }

    @Override
    public Term negate() {
        return new ValueForFeature(
            feature,
            feature.getEquivalenceClassLabels().stream()
                .filter(v -> !values.contains(v))
                .collect(Collectors.toList()));
    }

    public Feature getFeature() {
        return feature;
    }

    public List<String> getValues() {
        return values;
    }

    @Override
    public int compareTo(Object o) {
        return feature.getName().compareTo(((ValueForFeature) o).feature.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValueForFeature that = (ValueForFeature) o;
        return Objects.equals(feature, that.feature);
    }

    @Override
    public int hashCode() {
        return Objects.hash(feature);
    }
}
