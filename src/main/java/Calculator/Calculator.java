package Calculator;

public class Calculator {

    private String display = "";
    private int accumulator = 0;
    private int operand1 = 0;
    private int operand2 = 0;
    private String latestOperator;
    private boolean resetDisplay = false;

    public void press(String s) {
        if(s.equals("C")) {
            reset();
        } else if(s.equals("+") || s.equals("-")) {
            processOperator(s);
        } else if(s.equals("=")) {
            this.operand2 = Integer.valueOf(this.display);
            this.accumulator = accumulate(latestOperator);
            this.display = String.valueOf(this.accumulator);
        } else {
            if(resetDisplay) {
                reset();
            }
            this.display += s;
        }
    }

    private void reset() {
        this.display = "";
        this.resetDisplay = false;
    }

    private void processOperator(String s) {
        if(!"".equals(this.display)) {
            this.operand1 = Integer.valueOf(this.display);
        }

        resetDisplay = true;
        this.latestOperator = s;
    }

    private int accumulate(String operator) {
        return (operator.equals("+")) ? this.operand1 + this.operand2 : this.operand1 - this.operand2;
    }

    public String display() {
        return this.display;
    }
}