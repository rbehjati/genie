package io.rbehjati.genie.model;

import io.rbehjati.genie.model.rules.ValueForFactor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Factor {

    private final String name;
    private final List<String> equivalenceClassLabels;

    public Factor(String name, String... equivalenceClassLabels) {
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

    public int indexOf(String equivalenceClassLabel) {
        return equivalenceClassLabels.indexOf(equivalenceClassLabel);
    }

    public ValueForFactor in(String... values) {
        return new ValueForFactor(this, values == null ? Collections.emptyList() : Arrays.asList(values));
    }

    public ValueForFactor notIn(String... values) {
        List<String> valuesList = values == null ? Collections.emptyList() : Arrays.asList(values);
        return new ValueForFactor(
            this,
            equivalenceClassLabels.stream().filter(l -> !valuesList.contains(l)).collect(Collectors.toList()));
    }

    public String getName() {
        return name;
    }

    public List<String> getEquivalenceClassLabels() {
        return new ArrayList<>(equivalenceClassLabels);
    }

}
