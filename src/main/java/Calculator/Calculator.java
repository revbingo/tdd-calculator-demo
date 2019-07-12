package Calculator;

public class Calculator {

    private String display = "";
    private String currentNumber = "";
    private int accumulator = 0;
//    private int latestOperand = 0;
    private String latestOperator;
    private boolean resetDisplay = false;
    private boolean expectingOperand = true;

    public void press(String s) {
        if(s.equals("C")) {
            reset();
        } else if(s.equals("+") || s.equals("-")) {
            if(!"".equals(this.currentNumber) && !this.expectingOperand) {
                this.accumulator = accumulate(latestOperator);
            }

            resetDisplay = true;
            this.latestOperator = s;
            this.expectingOperand = true;
        } else if(s.equals("=")) {
            this.accumulator = accumulate(latestOperator);
            this.display = String.valueOf(this.accumulator);
        } else {
            if(resetDisplay) {
                reset();
            }
            this.currentNumber += s;
            this.expectingOperand = false;
            this.display = this.currentNumber;
        }
    }

    private void reset() {
        this.display = "";
        this.currentNumber = "";
        this.resetDisplay = false;
    }

    private int accumulate(String operator) {
        int latestOperand = Integer.valueOf((this.currentNumber.equals("") ? "0" : this.currentNumber));
        if(operator == null) return latestOperand;
        return (operator.equals("+")) ? this.accumulator + latestOperand : this.accumulator - latestOperand;
    }

    public String display() {
        return this.display;
    }
}