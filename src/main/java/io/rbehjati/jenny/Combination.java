package io.rbehjati.jenny;

import java.util.HashMap;
import java.util.Map;

public class Combination {

	private Map<Feature, String> equvalenceClassPerFeature = new HashMap<>();

	public void addEquvalenceClassForFeature(Feature feature, String classLabel) {
		equvalenceClassPerFeature.put(feature, classLabel);
	}

	public Map<Feature, String> getEquvalenceClassPerFeature() {
		return equvalenceClassPerFeature;
	}

	public String getEquvalenceClass(Feature feature) {
		return equvalenceClassPerFeature.get(feature);
	}

}
