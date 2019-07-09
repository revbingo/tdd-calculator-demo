package Calculator;

public class Calculator {

    String display = "";
    private String operand1;
    private String operand2;
    private String latestOperator;
    private boolean resetDisplay = false;

    public void press(String s) {
        if(s.equals("C")) {
            reset();
        } else if(s.equals("+") || s.equals("-")) {
            processOperator(s);
        } else if(s.equals("=")) {
            this.operand2 = this.display;
            this.display = processEquals(latestOperator);
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
        this.operand1 = this.display;
        resetDisplay = true;
        this.latestOperator = s;
    }

    /* I renamed this from processOperands() to something more direct */
    private String processEquals(String operator) {
        int op1 = Integer.valueOf(this.operand1);
        int op2 = Integer.valueOf(this.operand2);
        int result = (operator.equals("+")) ? op1 + op2 : op1 - op2;
        return String.valueOf(result);
    }

    public String display() {
        return this.display;
    }
}