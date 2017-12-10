package io.rbehjati.jenny;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Jenny {

	static {
		System.loadLibrary("jenny");
	}

	public native String[] generator(int argc, String[] argv);

	public List<Combination> generateCombinations(int n, List<Feature> features) {
		if(features.size() > 26) {
			throw new RuntimeException("Don't know how to handle more that 26 features yet!");
		}
		List<String> params = new ArrayList<>();
		params.add("n" + n);
		features.forEach(f -> params.add("" + f.getClassCount()));
		String[] combos = this.generator(params.size(), params.toArray(new String[] {}));
		
		List<Combination> featureCombos = new ArrayList<>();
		for(String combo : combos) {
			Combination featureCombination = new Combination();
			Iterator<Feature> featuresIterator = features.iterator();
			combo.chars().forEach(c -> {
				Feature feature = featuresIterator.next();
				featureCombination.addEquvalenceClassForFeature(feature, feature.getClassLabel(c - 'a'));
			});
			featureCombos.add(featureCombination);
		}
		return featureCombos;
	}
}
