package io.rbehjati.genie;

import io.rbehjati.genie.model.Combination;
import io.rbehjati.genie.model.Factor;
import io.rbehjati.genie.model.rules.Conditional;
import io.rbehjati.genie.model.rules.MapperToJenny;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class CombinationGenerator {

    public List<Combination> generateCombinations(int n, List<Factor> factors, Conditional... rules) {
        if (factors.stream().anyMatch(f -> f.getClassCount() > 26)) {
            throw new RuntimeException("Don't know how to handle more that 26 equivalence classes in a feature yet!");
        }
        List<String> params = new ArrayList<>();
        params.add("-n" + n);
        factors.forEach(f -> params.add("" + f.getClassCount()));
        Stream.of(rules)
            .map(conditional -> MapperToJenny.mapToJennyWithout(factors, conditional))
            .flatMap(Stream::of)
            .forEach(without -> params.add(without));
        String[] combos = new Jenny().generator(params.toArray(new String[]{}));

        List<Combination> featureCombos = new ArrayList<>();
        for (String combo : combos) {
            Combination featureCombination = new Combination();
            Iterator<Factor> featuresIterator = factors.iterator();
            combo.chars().forEach(c -> {
                Factor factor = featuresIterator.next();
                featureCombination.addEquivalenceClassForFactor(factor, factor.getClassLabel(c - 'a'));
            });
            featureCombos.add(featureCombination);
        }
        return featureCombos;
    }
}
