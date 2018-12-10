public class Main {

    public static void main(String[] args) {

        char board[][] =
                {
                        {'o', 'o', 'x'},
                        {'x', 'x', 'o'},
                        {'x', 'o', 'x'}
                };
        TicTacToe ticTacToe = new TicTacToe();

        //   Cell bestCell = ticTacToe.bestMove(board);

        System.out.println("Current Minimax =" + ticTacToe.minimax(board, 4, true));
        System.out.println(ticTacToe.bestMove);
    }

}
