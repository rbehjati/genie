package io.rbehjati.genie.model;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class FeatureTest {

    @Test
    public void getLabelIndexReturnsTheCorrectIndex() {
        Feature feature = new Feature("Feature", "Value1", "Yes", "No");
        Assertions.assertThat(feature.getLabelIndex("Value1")).isEqualTo(0);
        Assertions.assertThat(feature.getLabelIndex("Yes")).isEqualTo(1);
        Assertions.assertThat(feature.getLabelIndex("No") + 'a').isEqualTo('c');
    }

    @Test
    public void notInWithNoArgument_retainsAllClassLabels() {
        Feature feature = new Feature("Feature", "Value1", "Yes", "No");
        Assertions.assertThat(feature.notIn().getValues()).hasSize(3);
    }

    @Test
    public void notInWith_excludesTheGivenArgument() {
        Feature feature = new Feature("Feature", "Value1", "Yes", "No");
        Assertions.assertThat(feature.notIn("Yes").getValues()).doesNotContain("Yes");
    }

    @Test
    public void notInWith_retainsNoneOfTheValuesIfAllAreExcluded() {
        Feature feature = new Feature("Feature", "Value1", "Yes", "No");
        Assertions.assertThat(feature.notIn("Value1", "Yes", "No").getValues()).isEmpty();
    }
}