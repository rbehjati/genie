package io.rbehjati.genie.model.rules;

import java.util.List;
import java.util.Set;

public interface Term {
	
	Term negate();
	
	List<Set<Literal>> toDisjunctiveNormalForm();

}
