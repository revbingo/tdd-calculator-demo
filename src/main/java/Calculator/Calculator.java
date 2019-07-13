package Calculator;

public class Calculator {

    private Display display = new Display();
    private String currentNumber = "";
    private int accumulator = 0;
    private String latestOperator;
    private boolean expectingOperand = false;

    private enum KeyType {
        CLEAR,
        OPERATOR,
        EQUALS,
        NUMBER;

        public static KeyType forInput(String input) {
            switch(input) {
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
    }

    public Calculator press(String key) {
        switch(KeyType.forInput(key)) {
            case CLEAR:
                reset();
                break;

            case OPERATOR:
                if(!"".equals(this.currentNumber) && !this.expectingOperand) {
                    this.accumulator = accumulate(Integer.valueOf(this.currentNumber), latestOperator);
                }

                this.expectingOperand = true;
                this.latestOperator = key;
                break;

            case EQUALS:
                int latestOperand = Integer.valueOf((this.currentNumber.equals("") ? "0" : this.currentNumber));
                this.accumulator = accumulate(latestOperand, latestOperator);
                this.display.setDisplay(String.valueOf(this.accumulator));
                break;

            case NUMBER:
                if(this.expectingOperand) {
                    reset();
                }
                this.currentNumber += key;
                this.display.setDisplay(this.currentNumber);
        }

        return this;
    }

    private void reset() {
        this.display.reset();
        this.expectingOperand = false;
        this.currentNumber = "";
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
        return this.display.getDisplay();
    }

}