package io.rbehjati.genie;

import java.util.Arrays;
import java.util.List;

public class Feature {

	private final String name;
	private final List<String> equivalenceClassLabels;

	public Feature(String name, String... equivalenceClassLabels) {
		this.name = name;
		this.equivalenceClassLabels = Arrays.asList(equivalenceClassLabels);
	}

	public int getClassCount() {
		if (equivalenceClassLabels == null) {
			return 0;
		}
		return equivalenceClassLabels.size();
	}
	
	public String getClassLabel(int i) {
		return equivalenceClassLabels.get(i);
	}

	public String getName() {
		return name;
	}

}
