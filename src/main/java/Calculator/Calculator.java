package Calculator;

public class Calculator {

    private String display = "";
    private String currentNumber = "";
    private int accumulator = 0;
    private String latestOperator;
    private boolean resetDisplay = false;
    private boolean expectingOperand = true;

    private static final String OPERATORS = "+-x/";

    private enum KeyType {
        CLEAR,
        OPERATOR,
        EQUALS,
        NUMBER
    }

    public Calculator press(String key) {
        switch(getKeyType(key)) {
            case CLEAR:
                reset();
                break;

            case OPERATOR:
                if(!"".equals(this.currentNumber) && !this.expectingOperand) {
                    this.accumulator = accumulate(Integer.valueOf(this.currentNumber), latestOperator);
                }

                resetDisplay = true;
                this.latestOperator = key;
                this.expectingOperand = true;
                break;

            case EQUALS:
                int latestOperand = Integer.valueOf((this.currentNumber.equals("") ? "0" : this.currentNumber));
                this.accumulator = accumulate(latestOperand, latestOperator);
                this.display = String.valueOf(this.accumulator);
                break;

            case NUMBER:
                if(resetDisplay) {
                    reset();
                }
                this.currentNumber += key;
                this.expectingOperand = false;
                this.display = this.currentNumber;
        }

        return this;
    }

    private KeyType getKeyType(String s) {
        switch(s) {
            case "C":
                return KeyType.CLEAR;
            case "+":
            case "-":
            case "x":
            case "/":
                return KeyType.OPERATOR;
            case "=":
                return KeyType.EQUALS;
            default:
                return KeyType.NUMBER;
        }
    }

    private boolean isOperator(String s) {
        return OPERATORS.contains(s);
    }

    private void reset() {
        this.display = "";
        this.currentNumber = "";
        this.resetDisplay = false;
    }

    private int accumulate(int latestOperand, String operator) {
        if(operator == null) return latestOperand;
        switch(operator) {
            case "+":
                return this.accumulator + latestOperand;
            case "-":
                return this.accumulator - latestOperand;
            case "x":
                return this.accumulator * latestOperand;
            case "/":
                return this.accumulator / latestOperand;
            default:
                return this.accumulator;
        }
    }

    public String display() {
        return this.display;
    }
}