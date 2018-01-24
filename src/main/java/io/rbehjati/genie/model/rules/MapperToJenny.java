package io.rbehjati.genie.model.rules;

import io.rbehjati.genie.model.Feature;

import java.util.List;
import java.util.stream.Collectors;

public class MapperToJenny {

    public static String[] mapToJennyWithout(List<Feature> features, Conditional rule) {
        return rule.toDisjunctiveNormalFormOfNegate().stream()
            .map(conjunction -> "-w" + conjunction.stream()
                .map(literal -> toJennyWithout(features, literal))
                .sorted()
                .collect(Collectors.joining("")))
            .toArray(String[]::new);
    }

    private static String toJennyWithout(List<Feature> features, Literal literal) {
        if (literal instanceof ValueForFeature) {
            ValueForFeature valueForFeature = (ValueForFeature) literal;
            Feature feature = valueForFeature.getFeature();
            return String.valueOf(features.indexOf(feature) + 1)
                + valueForFeature.getValues().stream().map(value -> (char) (feature.getLabelIndex(value) + 'a'))
                .map(String::valueOf)
                .collect(Collectors.joining(""));
        } else {
            throw new IllegalArgumentException("Don't know how to map instances of " + literal.getClass() + "to Jenny without.");
        }
    }
}
