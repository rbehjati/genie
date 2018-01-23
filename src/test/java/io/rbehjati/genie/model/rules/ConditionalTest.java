package io.rbehjati.genie.model.rules;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ConditionalTest {

    @Test
    public void a_then_b() {
        assertThat(
            new Formula(
                new Conditional()
                    .given(new SimpleLiteral("a"))
                    .then(new SimpleLiteral("b"))
                    .toDisjunctiveNormalFormOfNegate()).toString()).isEqualTo("a.!b");
    }

    @Test
    public void andClause_then_andClause() {
        assertThat(
            new Formula(
                new Conditional()
                    .given(Formula.and(new SimpleLiteral("a"), new SimpleLiteral("b"), new SimpleLiteral("c")))
                    .then(Formula.and(new SimpleLiteral("d"), new SimpleLiteral("e").negate()))
                    .toDisjunctiveNormalFormOfNegate()).toString()).isEqualTo("a.b.c.!d + a.b.c.e");
    }

    @Test
    public void orClause_then_andClause() {
        assertThat(
            new Formula(
                new Conditional()
                    .given(Formula.or(new SimpleLiteral("a"), new SimpleLiteral("b")))
                    .then(Formula.and(new SimpleLiteral("c"), new SimpleLiteral("d"), new SimpleLiteral("e")))
                    .toDisjunctiveNormalFormOfNegate()).toString()).isEqualTo("a.!c + a.!d + a.!e + b.!c + b.!d + b.!e");
    }

    @Test
    public void orClause_then_orClause() {
        assertThat(
            new Formula(
                new Conditional()
                    .given(Formula.or(new SimpleLiteral("a"), new SimpleLiteral("b")))
                    .then(Formula.or(new SimpleLiteral("c"), new SimpleLiteral("d"), new SimpleLiteral("e")))
                    .toDisjunctiveNormalFormOfNegate()).toString()).isEqualTo("a.!c.!d.!e + b.!c.!d.!e");
    }

    @Test
    public void andClause_then_orClause() {
        assertThat(
            new Formula(
                new Conditional()
                    .given(Formula.and(new SimpleLiteral("a"), new SimpleLiteral("b")))
                    .then(Formula.or(new SimpleLiteral("c"), new SimpleLiteral("d"), new SimpleLiteral("e")))
                    .toDisjunctiveNormalFormOfNegate()).toString()).isEqualTo("a.b.!c.!d.!e");
    }

    @Test
    public void converse_of_givenDE_thenABC() {
        assertThat(
            new Formula(
                new Conditional()
                    .given(Formula.and(new SimpleLiteral("d"), new SimpleLiteral("e")))
                    .then(Formula.and(new SimpleLiteral("a"), new SimpleLiteral("b"), new SimpleLiteral("c")))
                    .converse()
                    .toDisjunctiveNormalFormOfNegate()).toString()).isEqualTo("a.b.c.!d + a.b.c.!e");
    }

}