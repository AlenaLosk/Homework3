public class View {

    public void refresh(String[][] symbols) {
        StringBuilder resultString;
        for (int i = 0; i < 3; i++) {
            resultString = new StringBuilder("|");
            for (int j = 0; j < 3; j++) {
                String symbol = symbols[i][j];
                switch (symbol) {
                    case "-" -> resultString.append("-|");
                    case "X" -> resultString.append("X|");
                    case "O" -> resultString.append("O|");
                }
            }
            System.out.println(resultString);
        }
    }
}
