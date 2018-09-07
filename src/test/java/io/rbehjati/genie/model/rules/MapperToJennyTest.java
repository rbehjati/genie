package io.rbehjati.genie.model.rules;

import io.rbehjati.genie.model.Factor;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;


public class MapperToJennyTest {

    @Test
    public void conditionalMissingARightHandSide_isCorrectlyMappedToJennyWithout(){
        Factor factor = new Factor("Factor", "T", "F");
        Conditional rule = new Conditional().given(factor.in("T"));

        Assertions.assertThat(MapperToJenny.mapToJennyWithout(Collections.singletonList(factor), rule))
        .contains("-w1b");
    }

    @Test
    public void conditionalWithBothSides_isCorrectlyMappedToJennyWithout(){
        Factor factorIf = new Factor("if", "T", "F");
        Factor factorThen = new Factor("then", "T", "F");

        Conditional rule = new Conditional()
            .given(factorIf.in("T"))
            .then(factorThen.in("T"));

        Assertions.assertThat(MapperToJenny.mapToJennyWithout(Arrays.asList(factorIf, factorThen), rule))
            .contains("-w1a2b");
    }

}