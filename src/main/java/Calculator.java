public class Calculator {

    private Display display = new Display();
    private String currentNumber = "";
    private int accumulator = 0;
    private String latestOperator;
    private boolean expectingOperand = false;

    public Calculator press(String key) {
        switch(KeyType.forInput(key)) {
            case CLEAR:
                reset();
                break;

            case OPERATOR:
                if(!this.expectingOperand) {
                    this.accumulator = accumulate(getOperand(), latestOperator);
                }

                this.expectingOperand = true;
                this.latestOperator = key;
                break;

            case EQUALS:
                this.accumulator = accumulate(getOperand(), latestOperator);
                this.display.setDisplay(String.valueOf(this.accumulator));
                break;

            case NUMBER:
                if(this.expectingOperand) {
                    reset();
                }
                this.currentNumber += key;
                this.display.setDisplay(this.currentNumber);
                break;

            case INVALID:
                break;
        }

        return this;
    }

    private int getOperand() {
        return Integer.valueOf((this.currentNumber.equals("") ? "0" : this.currentNumber));
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