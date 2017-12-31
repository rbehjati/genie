package io.rbehjati.genie.model.rules;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Conditional {

				private Term leftHandSide;
				private Term rightHandSide;

				public Conditional given(Term leftHandSide) {
								if (this.leftHandSide != null) {
												throw new IllegalArgumentException("Left hand side is already set");
								}
								this.leftHandSide = leftHandSide;
								return this;
				}

				public Conditional then(Term rightHandSide) {
								if (this.rightHandSide != null) {
												throw new IllegalArgumentException("Right hand side is already set");
								}
								this.rightHandSide = rightHandSide;
								return this;
				}

				public List<Set<Literal>> toDisjunctiveNormalFormOfNegate() {
								if (rightHandSide == null) {
												if (leftHandSide == null) {
																return Collections.emptyList();
												}
												return leftHandSide.negate().toDisjunctiveNormalForm();
								}
								// p /\ ~q
								return Formula.and(leftHandSide, rightHandSide.negate()).toDisjunctiveNormalForm();
				}

}
