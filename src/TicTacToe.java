public class TicTacToe {

    public Cell bestMove = null;

    public boolean checkComplete(char board[][]) {

        //write code here
        if (evaluateBoard(board) != 0) return true;
        int filledSpots = 0;
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == 'x' || cell == 'o') filledSpots++;
            }
        }
        return filledSpots == 9;
    }

    public int evaluateBoard(char board[][]) {

        //write code here
        int diagonalResult = checkDiagonals(board);
        if (diagonalResult != 0) return diagonalResult;
        for (int i = 0; i < 3; i++) {
            int columnResult = checkColumn(board, i);
            if (columnResult != 0) return columnResult;
        }
        for (int i = 0; i < 3; i++) {
            int rowResult = checkRow(board, i);
            if (rowResult != 0) return rowResult;
        }
        return 0;
    }

    public int minimax(char board[][], int depth, boolean isMaxTurn) {

        //write code here

        int evaluateBoardResult = evaluateBoard(board);
        if (evaluateBoardResult != 0) return evaluateBoardResult;
        if (checkComplete(board)) return 0;
        if (depth == 0) return 0;
        char[][] boardClone = board.clone();
        int maxResult = Integer.MIN_VALUE, minResult = Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (isMaxTurn) {
                    if (boardClone[i][j] == '_') {
                        boardClone[i][j] = 'x';
                        int result = minimax(board, depth - 1, !isMaxTurn);
                        if (result > maxResult) {
                            maxResult = result;
                            bestMove = new Cell(i, j);
                        }
                        boardClone[i][j] = '_';
                    }
                } else {
                    if (boardClone[i][j] == '_') {
                        boardClone[i][j] = 'o';
                        int result = minimax(board, depth - 1, !isMaxTurn);
                        if (result < minResult) {
                            minResult = result;
                            bestMove = new Cell(i, j);
                        }
                        boardClone[i][j] = '_';
                    }
                }
            }
        }

        return isMaxTurn ? maxResult : minResult;
    }


    public int checkDiagonals(char board[][]) {
        int mainDiagonalXCount = 0;
        int mainDiagonalOCount = 0;
        for (int i = 0; i < 3; i++) {
            if (board[i][i] == 'x') mainDiagonalXCount++;
            if (board[i][i] == 'o') mainDiagonalOCount++;
        }
        if (mainDiagonalXCount == 3) return 10;
        if (mainDiagonalOCount == 3) return -10;

        if (board[0][2] == 'x' && board[1][1] == 'x' && board[2][0] == 'x') return 10;
        if (board[0][2] == 'o' && board[1][1] == 'o' && board[2][0] == 'o') return -10;

        return 0;
    }

    public int checkRow(char[][] board, int rowNum) {
        if (board[0][rowNum] == 'x' && board[1][rowNum] == 'x' && board[2][rowNum] == 'x') return 10;
        if (board[0][rowNum] == 'o' && board[1][rowNum] == 'o' && board[2][rowNum] == 'o') return -10;
        return 0;
    }

    public int checkColumn(char[][] board, int colNum) {
        if (board[colNum][0] == 'x' && board[colNum][1] == 'x' && board[colNum][2] == 'x') return 10;
        if (board[colNum][0] == 'o' && board[colNum][1] == 'o' && board[colNum][2] == 'o') return -10;
        return 0;
    }

    public int heuristic(char[][] board, int x, int y) {
        return 0;
    }
}
