package io.rbehjati.jenny;

import java.util.HashMap;
import java.util.Map;

public class Combination {

	private Map<Feature, String> equivalenceClassPerFeature = new HashMap<>();

	public void addEquivalenceClassForFeature(Feature feature, String classLabel) {
		equivalenceClassPerFeature.put(feature, classLabel);
	}

	public Map<Feature, String> getEquivalenceClassPerFeature() {
		return equivalenceClassPerFeature;
	}

	public String getEquivalenceClass(Feature feature) {
		return equivalenceClassPerFeature.get(feature);
	}

}
