import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DisplayTest {

    @Test
    public void displayShowsZeroByDefault() {
        Display unit = new Display();

        assertThat(unit.getDisplay(), is("0"));
    }

    @Test
    public void canSetDisplay() {
        Display unit = new Display();

        unit.setDisplay("123");
        assertThat(unit.getDisplay(), is("123"));
    }

    @Test
    public void canResetDisplayToZero() {
        Display unit = new Display();

        unit.setDisplay("123");
        assertThat(unit.getDisplay(), is("123"));

        unit.reset();
        assertThat(unit.getDisplay(), is("0"));
    }
}
