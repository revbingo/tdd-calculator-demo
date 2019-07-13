package Calculator;

public enum KeyType {
    CLEAR,
    OPERATOR,
    EQUALS,
    NUMBER,
    INVALID;

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

            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                return KeyType.NUMBER;

            default:
                return KeyType.INVALID;
        }
    }
}
