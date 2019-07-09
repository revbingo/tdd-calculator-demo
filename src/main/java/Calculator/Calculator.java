package Calculator;

public class Calculator {

    String display = "";
    private String operand1;
    private String operand2;
    private boolean resetDisplay = false;

    public void press(String s) {
        if(s.equals("C")) {
            this.display = "";
        } else if(s.equals("+")) {
            this.operand1 = this.display;
            resetDisplay = true;
        } else if(s.equals("=")) {
            this.operand2 = this.display;
            this.display = addOperands();
        } else {
            if(resetDisplay) {
                this.display = "";
                this.resetDisplay = false;
            }
            this.display += s;
        }
    }

    private String addOperands() {
        int op1 = Integer.valueOf(this.operand1);
        int op2 = Integer.valueOf(this.operand2);
        return String.valueOf(op1 + op2);
    }

    public String display() {
        return this.display;
    }
}