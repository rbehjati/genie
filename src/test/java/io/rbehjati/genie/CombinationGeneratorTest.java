package io.rbehjati.genie;

import io.rbehjati.genie.model.Combination;
import io.rbehjati.genie.model.Feature;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class CombinationGeneratorTest {

    @Test
    public void testFeatureCombinationGenerator() {
        Feature nameFeature = new Feature("name", "Valid", "Invalid");
        Feature ageFeature = new Feature("age", "Old", "Young");
        List<Combination> result = new CombinationGenerator().generateCombinations(2, Arrays.asList(nameFeature, ageFeature));

        Assertions.assertThat(result).hasSize(4);

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                Combination combo = new Combination();
                combo.addEquivalenceClassForFeature(nameFeature, nameFeature.getClassLabel(i));
                combo.addEquivalenceClassForFeature(ageFeature, ageFeature.getClassLabel(j));
                Assertions.assertThat(result.contains(combo));
            }
        }
    }

}