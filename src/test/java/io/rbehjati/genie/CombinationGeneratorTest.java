package io.rbehjati.genie;

import io.rbehjati.genie.model.Combination;
import io.rbehjati.genie.model.Factor;
import io.rbehjati.genie.model.rules.Conditional;
import io.rbehjati.genie.model.rules.Formula;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class CombinationGeneratorTest {

    @Test
    public void testFeatureCombinationGenerator() {
        Factor nameFactor = new Factor("name", "Valid", "Invalid");
        Factor ageFactor = new Factor("age", "Old", "Young");
        List<Combination> result = new CombinationGenerator().generateCombinations(2, Arrays.asList(nameFactor, ageFactor));

        Assertions.assertThat(result).hasSize(4);

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                Combination combo = new Combination();
                combo.addEquivalenceClassForFactor(nameFactor, nameFactor.getClassLabel(i));
                combo.addEquivalenceClassForFactor(ageFactor, ageFactor.getClassLabel(j));
                Assertions.assertThat(result.contains(combo));
            }
        }
    }

    @Test
    public void testFeatureCombinationGeneratorWithRules() {
        Factor nameFactor = new Factor("name", "Valid", "Invalid");
        Factor ageFactor = new Factor("age", "Old", "Young");
        List<Combination> result = new CombinationGenerator()
            .generateCombinations(2,
                Arrays.asList(nameFactor, ageFactor),
                new Conditional().given(ageFactor.in("Old")).then(nameFactor.in("Valid")));

        Assertions.assertThat(result).hasSize(3);

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (i != 1 || j != 0) {
                    Combination combo = new Combination();
                    combo.addEquivalenceClassForFactor(nameFactor, nameFactor.getClassLabel(i));
                    combo.addEquivalenceClassForFactor(ageFactor, ageFactor.getClassLabel(j));
                    Assertions.assertThat(result.contains(combo));
                }
            }
        }
    }

    @Test
    public void combinationGenerator_returnsNoTuples_forInfeasibleModel() {
        Factor nameFactor = new Factor("name", "Valid", "Invalid");
        Factor ageFactor = new Factor("age", "Old", "Young");

        Conditional constraint = new Conditional().given(nameFactor.in("Valid")).then(ageFactor.in("Old"));
        Conditional negatedConstraint = new Conditional().given(
            Formula.and(nameFactor.in("Valid"), ageFactor.notIn("Old")));
        List<Combination> result = new CombinationGenerator()
            .generateCombinations(2, Arrays.asList(nameFactor, ageFactor), constraint, negatedConstraint);

        Assertions.assertThat(result).hasSize(0);
    }

}