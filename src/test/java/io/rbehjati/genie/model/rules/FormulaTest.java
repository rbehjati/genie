package io.rbehjati.genie.model.rules;

import io.rbehjati.genie.model.Feature;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Objects;

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


    private static class SimpleLiteral implements Literal{
        private final String value;
        private volatile boolean negated = false;

        SimpleLiteral(String value){
            this.value = value;
        }

        @Override
        public SimpleLiteral negate() {
            negated = !negated;
            return this;
        }

        @Override
        public String toString() {
            return (negated ? "!" : "") + value;
        }

        @Override
        public int compareTo(Object o) {
            SimpleLiteral that = (SimpleLiteral) o;
            return value.equals(that.value)
                    ? this.toString().compareTo(that.toString())
                    : value.compareTo(that.value);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SimpleLiteral that = (SimpleLiteral) o;
            return negated == that.negated &&
                    Objects.equals(value, that.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, negated);
        }
    }

}