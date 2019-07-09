import Calculator.Calculator;
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
}
