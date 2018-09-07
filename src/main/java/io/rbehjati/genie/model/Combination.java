package io.rbehjati.genie.model;

import java.util.HashMap;
import java.util.Map;

public class Combination {

	private Map<Factor, String> equivalenceClassPerFactor = new HashMap<>();

	public void addEquivalenceClassForFactor(Factor factor, String classLabel) {
		equivalenceClassPerFactor.put(factor, classLabel);
	}

	public Map<Factor, String> getEquivalenceClassPerFactor() {
		return equivalenceClassPerFactor;
	}

	public String getEquivalenceClass(Factor factor) {
		return equivalenceClassPerFactor.get(factor);
	}

}
