package io.rbehjati.jenny;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class JennyTest {

	@Test
	public void testGenerator() {
		String[] params = { "n2", "2", "3", "4", "3" };
		String[] result = new Jenny().generator(params.length, params);
		Assertions.assertThat(result).hasSize(14);
	}

	@Test
	public void testFeatureCombinationGenerator() {
		Feature nameFeature = new Feature("name", "Valid", "Invalid");
		Feature ageFeature = new Feature("age", "Old", "Young");
		List<Combination> result = new Jenny().generateCombinations(2, Arrays.asList(nameFeature, ageFeature));
		
		Assertions.assertThat(result).hasSize(4);
		
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				Combination combo = new Combination();
				combo.addEquvalenceClassForFeature(nameFeature, nameFeature.getClassLabel(i));
				combo.addEquvalenceClassForFeature(ageFeature, ageFeature.getClassLabel(j));
				Assertions.assertThat(result.contains(combo));
			}
		}

	}

}
