package io.rbehjati.jenny;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class JennyTest {

	@Test
	public void testGenerator()  {
		String[] params = { "n2", "2", "3", "4", "3" };
		String[] result = new Jenny().generator(params.length, params);
		Assertions.assertThat(result).hasSize(14);
	}

}
