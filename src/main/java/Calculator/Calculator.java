package Calculator;

public class Calculator {

    String display = "";
    private String operand1;
    private String operand2;
    private String latestOperator;
    private boolean resetDisplay = false;

    public void press(String s) {
        if(s.equals("C")) {
            this.display = "";
        } else if(s.equals("+")) {
            this.operand1 = this.display;
            resetDisplay = true;
            this.latestOperator = s;
        } else if(s.equals("-")) {
            this.operand1 = this.display;
            resetDisplay = true;
            this.latestOperator = s;
        } else if(s.equals("=")) {
            this.operand2 = this.display;
            this.display = processOperands(latestOperator);
        } else {
            if(resetDisplay) {
                this.display = "";
                this.resetDisplay = false;
            }
            this.display += s;
        }
    }

    private String processOperands(String operator) {
        int op1 = Integer.valueOf(this.operand1);
        int op2 = Integer.valueOf(this.operand2);
        int result = (operator.equals("+")) ? op1 + op2 : op1 - op2;
        return String.valueOf(result);
    }

    public String display() {
        return this.display;
    }
}