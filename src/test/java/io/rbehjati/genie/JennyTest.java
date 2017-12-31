package io.rbehjati.genie;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class JennyTest {

				@Test
				public void testGenerator() {
								String[] params = {"-n2", "2", "3", "4", "3"};
								String[] result = new Jenny().generator(params);
								Assertions.assertThat(result).hasSize(14);
				}

				@Test
				public void testGeneratorCombinationsOfOneFeature() {
								String[] params = {"-n1", "2", "3", "4", "3"};
								String[] result = new Jenny().generator(params);
								Assertions.assertThat(result).hasSize(4);
				}

}
