import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class KeyTypeTest {

    @Test
    public void theCKeyRepresentsAClear() {
        assertThat(KeyType.forInput("C"), is(KeyType.CLEAR));
    }

    @Test
    public void operatorsMapToTheOperatorKeyType() {
        assertThat(KeyType.forInput("+"), is(KeyType.OPERATOR));
        assertThat(KeyType.forInput("-"), is(KeyType.OPERATOR));
        assertThat(KeyType.forInput("/"), is(KeyType.OPERATOR));
        assertThat(KeyType.forInput("x"), is(KeyType.OPERATOR));
    }

    @Test
    public void equalsMapsToEqualsOperator() {
        assertThat(KeyType.forInput("="), is(KeyType.EQUALS));
    }

    @Test
    public void numbersMapToNumberKeyType() {
        assertThat(KeyType.forInput("0"), is(KeyType.NUMBER));
        assertThat(KeyType.forInput("1"), is(KeyType.NUMBER));
        assertThat(KeyType.forInput("2"), is(KeyType.NUMBER));
        assertThat(KeyType.forInput("3"), is(KeyType.NUMBER));
        assertThat(KeyType.forInput("4"), is(KeyType.NUMBER));
        assertThat(KeyType.forInput("5"), is(KeyType.NUMBER));
        assertThat(KeyType.forInput("6"), is(KeyType.NUMBER));
        assertThat(KeyType.forInput("7"), is(KeyType.NUMBER));
        assertThat(KeyType.forInput("8"), is(KeyType.NUMBER));
        assertThat(KeyType.forInput("9"), is(KeyType.NUMBER));
    }

    @Test
    public void otherKeysGivesInvalidKeyType() {
        assertThat(KeyType.forInput("a"), is(KeyType.INVALID));
        assertThat(KeyType.forInput(""), is(KeyType.INVALID));
        assertThat(KeyType.forInput("!"), is(KeyType.INVALID));
        assertThat(KeyType.forInput("8888"), is(KeyType.INVALID));
    }
}
