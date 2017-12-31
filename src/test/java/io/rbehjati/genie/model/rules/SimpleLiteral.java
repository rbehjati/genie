package io.rbehjati.genie.model.rules;

import java.util.Objects;

public class SimpleLiteral implements Literal{
    final String value;
    volatile boolean negated = false;

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