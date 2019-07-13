package Calculator;

public class Display {
    private String display = "0";

    public String getDisplay() {
        return this.display;
    }

    public void setDisplay(String number) {
        this.display = number;
    }

    public void reset() {
        this.setDisplay("0");
    }
}
