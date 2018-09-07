package io.rbehjati.genie.model.rules;

import io.rbehjati.genie.model.Factor;
import org.junit.Ignore;
import org.junit.Test;

import static io.rbehjati.genie.model.rules.Formula.*;
import static org.assertj.core.api.Assertions.assertThat;


public class FormulaTest {

    @Test
    public void simpleOr() {
        Formula or = or(new SimpleLiteral("a"), new SimpleLiteral("b"));
        assertThat(or.toString()).isEqualTo("a + b");
    }


    @Test
    public void simpleAnd() {
        Formula and = and(new SimpleLiteral("a"), new SimpleLiteral("b"));
        assertThat(and.toString()).isEqualTo("a.b");
    }

    @Test
    public void simpleNegate() {
        Formula and = and(new SimpleLiteral("a"), new SimpleLiteral("b"));
        assertThat(and.negate().toString()).isEqualTo("!a + !b");
    }

    @Test
    public void abOracd() {
        Formula formula = or(
            and(new SimpleLiteral("a"), new SimpleLiteral("b")),
            and(new SimpleLiteral("a"), new SimpleLiteral("c"), new SimpleLiteral("d")));
        assertThat(formula.toString()).isEqualTo("a.b + a.c.d");
    }

    @Test
    public void abOracd_negated() {
        Formula formula = or(
            and(new SimpleLiteral("a"), new SimpleLiteral("b")),
            and(new SimpleLiteral("a"), new SimpleLiteral("c"), new SimpleLiteral("d")));
        assertThat(formula.negate().toString()).isEqualTo("!a + !a.!c + !a.!d + !a.!b + !b.!c + !b.!d");
    }

    @Test
    public void abOrnotAcd_negated() {
        Formula formula = or(
            and(new SimpleLiteral("a"), new SimpleLiteral("b")),
            and(new SimpleLiteral("a").negate(), new SimpleLiteral("c"), new SimpleLiteral("d")));
        assertThat(formula.negate().toString()).isEqualTo("!a.a + !a.!c + !a.!d + a.!b + !b.!c + !b.!d");
    }

    @Test
    @Ignore
    public void negateOfABigFormula(){

        Factor[] dokumentgrunnlag = new Factor[]{
            new Factor("dokumentgrunnlag1", "WithCopy", "MissingCopy", "NotPresent"),
            new Factor("dokumentgrunnlag2", "WithCopy", "MissingCopy", "NotPresent")
        };

        Factor[] personIdentifikasjon = new Factor[]{
            new Factor("personIdentifikasjon1", "ValidDuf", "InvalidDuf", "DufInUSe", "ValidNonDuf",  "NotPresent"),
            new Factor("personIdentifikasjon2", "ValidDuf", "InvalidDuf", "DufInUSe", "ValidNonDuf",  "NotPresent"),
        };

        Factor[] identitetsbekreftelse = new Factor[]{
            new Factor("identitetsbekreftelse1", "Approved", "NotApproved",  "NotPresent"),
            new Factor("identitetsbekreftelse2", "Approved", "NotApproved",  "NotPresent"),
        };

        Formula minstEnDokgrunnlagForSkatt = Formula.or(
            dokumentgrunnlag[0].in("WithCopy", "MissingCopy"),
            dokumentgrunnlag[1].in("WithCopy", "MissingCopy"));

        Formula kopiEllerBekreftetIdForSkatt =
            Formula.and(
                Formula.or(personIdentifikasjon[0].in("NotPresent"),
                    dokumentgrunnlag[0].in("WithCopy"), identitetsbekreftelse[0].in("Approved")),
                Formula.or(personIdentifikasjon[1].in("NotPresent"),
                    dokumentgrunnlag[1].in("WithCopy"), identitetsbekreftelse[1].in("Approved")));

        Formula dokumentgrunnlagSkatt =
            Formula.and(minstEnDokgrunnlagForSkatt, kopiEllerBekreftetIdForSkatt);
        System.out.println(dokumentgrunnlagSkatt.negate());
    }

}