public class View {

    public void refresh(Symbol[][] marks) {
        StringBuilder resultString;
        for (int i = 0; i < 3; i++) {
            resultString = new StringBuilder("|");
            for (int j = 0; j < 3; j++) {
                Symbol mark = marks[i][j];
                switch (mark) {
                    case SPACE -> resultString.append("-|");
                    case CROSS -> resultString.append("X|");
                    case ZERO -> resultString.append("0|");
                }
            }
            System.out.println(resultString);
        }
    }
}
