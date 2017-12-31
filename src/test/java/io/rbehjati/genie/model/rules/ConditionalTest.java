package io.rbehjati.genie.model.rules;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class ConditionalTest {

				@Test
				public void testAthenB() {
								Assertions.assertThat(
												new Formula(
																new Conditional()
																				.given(new SimpleLiteral("a"))
																				.then(new SimpleLiteral("b"))
																				.toDisjunctiveNormalFormOfNegate()).toString()).isEqualTo("a.!b");
				}

}