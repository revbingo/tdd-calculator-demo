package Calculator;

public class Calculator {

    private String display = "";
    private String currentNumber = "";
    private int accumulator = 0;
    private String latestOperator;
    private boolean resetDisplay = false;
    private boolean expectingOperand = true;

    private String operators = "+-x/";

    public Calculator press(String s) {
        if(s.equals("C")) {
            reset();
        } else if(operators.contains(s)) {
            if(!"".equals(this.currentNumber) && !this.expectingOperand) {
                this.accumulator = accumulate(Integer.valueOf(this.currentNumber), latestOperator);
            }

            resetDisplay = true;
            this.latestOperator = s;
            this.expectingOperand = true;
        } else if(s.equals("=")) {
            int latestOperand = Integer.valueOf((this.currentNumber.equals("") ? "0" : this.currentNumber));
            this.accumulator = accumulate(latestOperand, latestOperator);
            this.display = String.valueOf(this.accumulator);
        } else {
            if(resetDisplay) {
                reset();
            }
            this.currentNumber += s;
            this.expectingOperand = false;
            this.display = this.currentNumber;
        }
        return this;
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