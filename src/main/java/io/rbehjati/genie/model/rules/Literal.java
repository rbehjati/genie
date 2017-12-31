package io.rbehjati.genie.model.rules;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface Literal extends Term, Comparable {

				@Override
				default List<Set<Literal>> toDisjunctiveNormalForm() {
								List<Set<Literal>> dnf = new ArrayList<>();
								Set<Literal> conj = new HashSet<>();
								conj.add(this);
								dnf.add(conj);
								return dnf;
				}

}
