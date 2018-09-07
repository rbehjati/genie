package io.rbehjati.genie.model;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class FactorTest {

    @Test
    public void indexOf_ReturnsTheCorrectIndex() {
        Factor factor = new Factor("Factor", "Value1", "Yes", "No");
        Assertions.assertThat(factor.indexOf("Value1")).isEqualTo(0);
        Assertions.assertThat(factor.indexOf("Yes")).isEqualTo(1);
        Assertions.assertThat(factor.indexOf("No") + 'a').isEqualTo('c');
    }

    @Test
    public void notInWithNoArgument_retainsAllClassLabels() {
        Factor factor = new Factor("Factor", "Value1", "Yes", "No");
        Assertions.assertThat(factor.notIn().getValues()).hasSize(3);
    }

    @Test
    public void notInWith_excludesTheGivenArgument() {
        Factor factor = new Factor("Factor", "Value1", "Yes", "No");
        Assertions.assertThat(factor.notIn("Yes").getValues()).doesNotContain("Yes");
    }

    @Test
    public void notInWith_retainsNoneOfTheValuesIfAllAreExcluded() {
        Factor factor = new Factor("Factor", "Value1", "Yes", "No");
        Assertions.assertThat(factor.notIn("Value1", "Yes", "No").getValues()).isEmpty();
    }
}