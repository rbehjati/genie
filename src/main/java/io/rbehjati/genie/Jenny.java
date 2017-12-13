package io.rbehjati.genie;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Jenny {

	static {
		System.loadLibrary("jenny");
	}

	public native String[] generator(String[] argv);

	public List<Combination> generateCombinations(int n, List<Feature> features) {
		if(features.stream().filter(f -> f.getClassCount() > 26).findAny().isPresent()) {
			throw new RuntimeException("Don't know how to handle more that 26 equivalence classes in a feature yet!");
		}
		List<String> params = new ArrayList<>();
		params.add("-n" + n);
		features.forEach(f -> params.add("" + f.getClassCount()));
		String[] combos = this.generator(params.toArray(new String[] {}));
		
		List<Combination> featureCombos = new ArrayList<>();
		for(String combo : combos) {
			Combination featureCombination = new Combination();
			Iterator<Feature> featuresIterator = features.iterator();
			combo.chars().forEach(c -> {
				Feature feature = featuresIterator.next();
				featureCombination.addEquivalenceClassForFeature(feature, feature.getClassLabel(c - 'a'));
			});
			featureCombos.add(featureCombination);
		}
		return featureCombos;
	}
}
