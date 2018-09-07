package io.rbehjati.genie.model.rules;

import io.rbehjati.genie.model.Factor;

import java.util.List;
import java.util.stream.Collectors;

public class MapperToJenny {

    public static String[] mapToJennyWithout(List<Factor> factors, Conditional rule) {
        return rule.toDisjunctiveNormalFormOfNegate().stream()
            .map(conjunction -> "-w" + conjunction.stream()
                .map(literal -> toJennyWithout(factors, literal))
                .sorted()
                .collect(Collectors.joining("")))
            .toArray(String[]::new);
    }

    private static String toJennyWithout(List<Factor> factors, Literal literal) {
        if (literal instanceof ValueForFactor) {
            ValueForFactor valueForFactor = (ValueForFactor) literal;
            Factor factor = valueForFactor.getFactor();
            // Jenny represents the first value of the first factor as "1a"
            return String.valueOf(factors.indexOf(factor) + 1)
                + valueForFactor.getValues().stream().map(value -> (char) (factor.indexOf(value) + 'a'))
                .map(String::valueOf)
                .collect(Collectors.joining(""));
        } else {
            throw new IllegalArgumentException("Don't know how to map instances of " + literal.getClass() + "to Jenny without.");
        }
    }
}
