import Calculator.Calculator;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CalculatorTest {

    @Test
    public void pressingANumberDisplaysTheNumberOnScreen() {
        Calculator unit = new Calculator();

        unit.press("3");

        assertThat(unit.display(), is("3"));
    }

    @Test
    public void pressingMultipleNumbersDisplaysAllOfThemAsOneNumber() {
        Calculator unit = new Calculator();

        unit.press("3");
        unit.press("4");
        unit.press("5");

        assertThat(unit.display(), is("345"));
    }

    @Test
    public void iCanClearTheDisplayByPressingTheClearButton() {
        Calculator unit = new Calculator();

        // Note that I'll assert the display is *not* clear first, to verify that it really
        // is the "C" button that clears the display (as opposed to the display always showing empty, for example)
        unit.press("345");
        assertThat(unit.display(), is("345"));

        unit.press("C");
        assertThat(unit.display(), is(""));
    }

    /*
        I started to write the test useThePlusButtonToAddTwoNumbersTogether, but it was forcing me to implement
        both the plus and equals at the same time, which felt like too much functionality to do in one go.  This
        test is a simple one to force me to at least treat + as a special case before I implement the addition
        behaviour
     */
    @Test
    public void pressingThePlusButtonDoesNotChangeTheDisplay() {
        Calculator unit = new Calculator();

        unit.press("345");
        assertThat(unit.display(), is("345"));

        unit.press("+");
        assertThat(unit.display(), is("345"));
    }

    @Test
    public void useThePlusButtonToAddTwoNumbersTogether() {
        Calculator unit = new Calculator();

        unit.press("3");
        unit.press("+");
        unit.press("4");
        unit.press("=");

        assertThat(unit.display(), is("7"));
    }

    /*
        Because the test above worked, I expected this to work without any changes to the code. Instead, it printed
        out "37".  This was because I didn't set the resetDisplay flag back to false.  An easy mistake to make, and
        a good job I wrote this test
     */
    @Test
    public void addingTwoDigitNumbersWorksAsExpected() {
        Calculator unit = new Calculator();

        unit.press("3");
        unit.press("3");
        unit.press("+");
        unit.press("4");
        unit.press("4");
        unit.press("=");

        assertThat(unit.display(), is("77"));
    }

    /*
        This test also failed after I had implemented the functionality. This turned out to be because I had cut and
        pasted the test and forgot to change the operator.  Sometimes, failures are because the test is buggy,
        not the production code!
     */
    @Test
    public void useTheMinusButtonToSubtractNumbers() {
        Calculator unit = new Calculator();

        unit.press("4");
        unit.press("-");
        unit.press("3");
        unit.press("=");

        assertThat(unit.display(), is("1"));
    }

    /*
        This and tests below it do not force me to add any new code, they just verify that the implementation
        I've written cover these cases.  We write these tests because we cannot be sure that every implementation
        in the future will also cover these cases. Also be sure to deliberately make these tests fail e.g. by
        changing the production code, or by changing the expected values, to be sure that the test does actually
        fail if the code or the test is incorrect.
     */
    @Test
    public void nothingHappensUntilIPressEquals() {
        Calculator unit = new Calculator();

        unit.press("4");
        unit.press("+");
        unit.press("3");
        unit.press("1");

        assertThat(unit.display(), is("31"));

        unit.press("=");
        assertThat(unit.display(), is("35"));
    }

    @Test
    public void addingZeroWorksAsExpected() {
        Calculator unit = new Calculator();

        unit.press("4");
        unit.press("+");
        unit.press("0");
        unit.press("=");

        assertThat(unit.display(), is("4"));
    }

    @Test
    public void pressingPlusTwiceStillAddsTheNumbers() {
        Calculator unit = new Calculator();

        unit.press("4");
        unit.press("+");
        unit.press("+");
        unit.press("4");
        unit.press("=");

        assertThat(unit.display(), is("8"));
    }

    @Test
    public void pressingMinusTwiceStillSubtractsTheNumbers() {
        Calculator unit = new Calculator();

        unit.press("4");
        unit.press("-");
        unit.press("-");
        unit.press("4");
        unit.press("=");

        assertThat(unit.display(), is("0"));
    }

    /*
        This was *really* interesting! The original name for the the test was pressingPlusThenEqualsStillShowsEnteredValue().
        I expected the display to show "4", and expected this test to pass. It didn't, it showed "8". I debugged, and
        found that this was because when I pressed equals, it took the content of the display as being the second
        operand, and because pressing plus doesn't immediately clear the display, it was still "4",
        so it did 4 + 4. This got me thinking about what the behaviour ought to be.  I tried it on the Mac Calculator.Calculator.app,
        and it shows exactly the same behaviour.  Pressing 4, +, = gives an answer of 8
     */
    @Test
    public void pressingPlusThenEqualsImpliesSecondOperandSameAsTheFirst() {
        Calculator unit = new Calculator();

        unit.press("4");
        unit.press("+");
        unit.press("=");

        assertThat(unit.display(), is("8"));
    }

    /*
        Having discovered the above in Calculator.Calculator.app, it made me wonder "what happens if I press equals again"??.
        Totally unexpectedly, my code, without any changes, behaves the same way.  The first operator is repeatedly
        applied to the first operator
     */
    @Test
    public void pressingEqualsMultipleTimesAppliesTheLastOperatorToTheLastOperand() {
        Calculator unit = new Calculator();

        unit.press("4");
        unit.press("+");
        assertThat(unit.display(), is("4"));

        unit.press("=");
        assertThat(unit.display(), is("8"));

        unit.press("=");
        assertThat(unit.display(), is("12"));

        unit.press("=");
        assertThat(unit.display(), is("16"));
    }

    @Test
    public void pressingPlusFirstImpliesZeroForFirstOperand() {
        Calculator unit = new Calculator();

        unit.press("+");
        unit.press("4");
        assertThat(unit.display(), is("4"));
    }

    /*
        In trying to implement this, things got a little complicated with state, and it started breaking other
        tests. Rather than trying to both refactor the code and implement this functionality, I @Ignore this test
        temporarily and concentrate on applying my refactoring whilst keeping everything else green.  Once I've done
        that refactoring, implementing the code for this feature should be trivial.
     */
    @Test
    public void canAddMultipleNumbers() {
        Calculator unit = new Calculator();

        unit.press("4");
        unit.press("+");
        unit.press("5");
        unit.press("+");
        unit.press("6");
        unit.press("=");
        assertThat(unit.display(), is("15"));
    }

}
