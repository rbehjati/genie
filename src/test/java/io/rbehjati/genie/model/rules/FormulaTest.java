package io.rbehjati.genie.model.rules;

import org.junit.Test;

import static io.rbehjati.genie.model.rules.Formula.*;
import static org.assertj.core.api.Assertions.assertThat;


public class FormulaTest {

    @Test
    public void simpleOr(){
        Formula or = or(new SimpleLiteral("a"), new SimpleLiteral("b"));
        assertThat(or.toString()).isEqualTo("a + b");
    }


    @Test
    public void simpleAnd(){
        Formula and = and(new SimpleLiteral("a"), new SimpleLiteral("b"));
        assertThat(and.toString()).isEqualTo("a.b");
    }

    @Test
    public void simpleNegate(){
        Formula and = and(new SimpleLiteral("a"), new SimpleLiteral("b"));
        assertThat(and.negate().toString()).isEqualTo("!a + !b");
    }

    @Test
    public void abOracd(){
        Formula formula = or(
                and(new SimpleLiteral("a"), new SimpleLiteral("b")),
                and(new SimpleLiteral("a"), new SimpleLiteral("c"), new SimpleLiteral("d")));
        assertThat(formula.toString()).isEqualTo("a.b + a.c.d");
    }

    @Test
    public void abOracd_negated(){
        Formula formula = or(
                and(new SimpleLiteral("a"), new SimpleLiteral("b")),
                and(new SimpleLiteral("a"), new SimpleLiteral("c"), new SimpleLiteral("d")));
        assertThat(formula.negate().toString()).isEqualTo("!a + !a.!c + !a.!d + !a.!b + !b.!c + !b.!d");
    }

    @Test
    public void abOrnotAcd_negated(){
        Formula formula = or(
                and(new SimpleLiteral("a"), new SimpleLiteral("b")),
                and(new SimpleLiteral("a").negate(), new SimpleLiteral("c"), new SimpleLiteral("d")));
        assertThat(formula.negate().toString()).isEqualTo("!a.a + !a.!c + !a.!d + a.!b + !b.!c + !b.!d");
    }

}