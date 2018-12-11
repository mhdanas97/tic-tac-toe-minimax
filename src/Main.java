public class Main {

    public static void main(String[] args) {

        char board[][] =
                {
                        {'_', '_', '_'},
                        {'_', '_', '_'},
                        {'_', '_', '_'}
                };
        TicTacToe ticTacToe = new TicTacToe();

        //   Cell bestCell = ticTacToe.bestMove(board);

        System.out.println("Current Minimax =" + ticTacToe.minimax(board, 4, Integer.MIN_VALUE, Integer.MAX_VALUE, true));
        System.out.println(ticTacToe.bestMove);
    }

}
