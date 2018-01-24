package io.rbehjati.genie.model.rules;

import io.rbehjati.genie.model.Feature;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;


public class MapperToJennyTest {

    @Test
    public void conditionalMissingARightHandSide_isCorrectlyMappedToJennyWithout(){
        Feature feature = new Feature("Feature", "T", "F");
        Conditional rule = new Conditional().given(feature.in("T"));

        Assertions.assertThat(MapperToJenny.mapToJennyWithout(Collections.singletonList(feature), rule))
        .contains("-w1b");
    }

    @Test
    public void conditionalWithBothSides_isCorrectlyMappedToJennyWithout(){
        Feature featureIf = new Feature("if", "T", "F");
        Feature featureThen = new Feature("then", "T", "F");

        Conditional rule = new Conditional()
            .given(featureIf.in("T"))
            .then(featureThen.in("T"));

        Assertions.assertThat(MapperToJenny.mapToJennyWithout(Arrays.asList(featureIf, featureThen), rule))
            .contains("-w1a2b");
    }

}