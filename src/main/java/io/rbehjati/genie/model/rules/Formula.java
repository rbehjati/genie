package io.rbehjati.genie.model.rules;

import java.util.*;
import java.util.stream.Collectors;

public class Formula implements Term {

    List<Set<Literal>> conjunctions;

    public Formula(List<Set<Literal>> conjunctions) {
        this.conjunctions = conjunctions;
    }

    public static Formula and(Literal... literals) {
        List<Set<Literal>> conjunctions = new ArrayList<>();
        if (literals == null) {
            return new Formula(conjunctions);
        }
        Set<Literal> conjunction = new HashSet<>(Arrays.asList(literals));
        conjunctions.add(conjunction);
        return new Formula(conjunctions);
    }

    public static Formula and(Term term1, Term term2) {
        List<Set<Literal>> dnf1 = term1.toDisjunctiveNormalForm();
        List<Set<Literal>> dnf2 = term2.toDisjunctiveNormalForm();
        List<Set<Literal>> conjunctions = new ArrayList<>();

        dnf1.forEach(conj1 -> dnf2.forEach(conj2 -> {
            Set<Literal> newConjunction = new HashSet<>(conj1);
            newConjunction.addAll(conj2);
            conjunctions.add(newConjunction);
        }));
        return new Formula(conjunctions);
    }

    public static Formula or(Literal... literals) {
        List<Set<Literal>> conjunctions = new ArrayList<>();
        if (literals == null) {
            return new Formula(conjunctions);
        }
        Arrays.asList(literals).forEach(literal -> {
            Set<Literal> conjunction = new HashSet<>();
            conjunction.add(literal);
            conjunctions.add(conjunction);
        });
        return new Formula(conjunctions);
    }

    public static Formula or(Term term1, Term term2) {
        List<Set<Literal>> dnf = term1.toDisjunctiveNormalForm();
        dnf.addAll(term2.toDisjunctiveNormalForm());
        return new Formula(dnf);
    }

    @Override
    public Term negate() {
        return conjunctions.stream()
            .map(conjunction -> {
                List<Set<Literal>> newConjunctions = new ArrayList<>();
                conjunction.stream().sorted().forEach(literal -> {
                    Set<Literal> newConjunction = new HashSet<>();
                    newConjunction.add((Literal) literal.negate());
                    newConjunctions.add(newConjunction);
                });
                return new Formula(newConjunctions);
            }).reduce(Formula::and).get();
    }

    @Override
    public List<Set<Literal>> toDisjunctiveNormalForm() {
        List<Set<Literal>> conjunctionsCopy = new ArrayList<>();
        conjunctions.forEach(conj -> {
            Set<Literal> newConj = new HashSet<>(conj);
            conjunctionsCopy.add(newConj);
        });
        return conjunctionsCopy;
    }


    @Override
    public String toString() {
        return conjunctions.stream()
            .map(conj -> conj.stream().sorted().map(Literal::toString).collect(Collectors.joining(".")))
            .collect(Collectors.joining(" + "));
    }
}
