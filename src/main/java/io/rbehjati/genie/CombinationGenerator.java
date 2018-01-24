package io.rbehjati.genie;

import io.rbehjati.genie.model.Combination;
import io.rbehjati.genie.model.Feature;
import io.rbehjati.genie.model.rules.Conditional;
import io.rbehjati.genie.model.rules.MapperToJenny;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class CombinationGenerator {

    public List<Combination> generateCombinations(int n, List<Feature> features, Conditional... rules) {
        if (features.stream().anyMatch(f -> f.getClassCount() > 26)) {
            throw new RuntimeException("Don't know how to handle more that 26 equivalence classes in a feature yet!");
        }
        List<String> params = new ArrayList<>();
        params.add("-n" + n);
        features.forEach(f -> params.add("" + f.getClassCount()));
        Stream.of(rules)
            .map(conditional -> MapperToJenny.mapToJennyWithout(features, conditional))
            .flatMap(Stream::of)
            .forEach(without -> params.add(without));
        String[] combos = new Jenny().generator(params.toArray(new String[]{}));

        List<Combination> featureCombos = new ArrayList<>();
        for (String combo : combos) {
            Combination featureCombination = new Combination();
            Iterator<Feature> featuresIterator = features.iterator();
            combo.chars().forEach(c -> {
                Feature feature = featuresIterator.next();
                featureCombination.addEquivalenceClassForFeature(feature, feature.getClassLabel(c - 'a'));
            });
            featureCombos.add(featureCombination);
        }
        return featureCombos;
    }
}
