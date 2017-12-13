package io.rbehjati.genie;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import io.rbehjati.genie.Combination;
import io.rbehjati.genie.Feature;
import io.rbehjati.genie.Jenny;

public class JennyTest {

	@Test
	public void testGenerator() {
		String[] params = { "-n2", "2", "3", "4", "3" };
		String[] result = new Jenny().generator(params);
		Assertions.assertThat(result).hasSize(14);
	}
	
	@Test
	public void testGeneratorCombinationsOfOneFeature() {
		String[] params = { "-n1", "2", "3", "4", "3" };
		String[] result = new Jenny().generator(params);
		Assertions.assertThat(result).hasSize(4);
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
				combo.addEquivalenceClassForFeature(nameFeature, nameFeature.getClassLabel(i));
				combo.addEquivalenceClassForFeature(ageFeature, ageFeature.getClassLabel(j));
				Assertions.assertThat(result.contains(combo));
			}
		}

	}

}
