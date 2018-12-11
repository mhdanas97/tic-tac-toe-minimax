import java.util.Comparator;
import java.util.PriorityQueue;

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
        PriorityQueue<Cell> pq = findValidMoves(board, isMaxTurn);
        if (depth == 0) {
            bestMove = pq.peek();
            return pq.peek().heuristic;
        }
        char[][] boardClone = board.clone();
        int maxResult = Integer.MIN_VALUE, minResult = Integer.MAX_VALUE;

        while (!pq.isEmpty()) {
            Cell move = pq.poll();
            if (isMaxTurn) {
                boardClone[move.x][move.y] = 'x';
                int result = minimax(boardClone, depth - 1, !isMaxTurn);
                if (result > maxResult) {
                    maxResult = result;
                    bestMove = move;
                }
                boardClone[move.x][move.y] = '_';
            } else {
                boardClone[move.x][move.y] = 'o';
                int result = minimax(boardClone, depth - 1, !isMaxTurn);
                if (result < minResult) {
                    minResult = result;
                    bestMove = move;
                }
                boardClone[move.x][move.y] = '_';
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
        int xRows = 0, oRows = 0, xCols = 0, oCols = 0, xDiagonals = 0, oDiagonals = 0;
        if (x == y) {
            int mainDiagonalXCount = 0, mainDiagonalOCount = 0;
            for (int i = 0; i < 3; i++) {
                if (board[i][i] == 'x' || board[i][i] == '_') mainDiagonalXCount++;
                if (board[i][i] == 'o' || board[i][i] == '_') mainDiagonalOCount++;
            }
            if (mainDiagonalXCount == 3) xDiagonals++;
            if (mainDiagonalOCount == 3) oDiagonals++;
        }
        if ((x + y) == 2) {
            int secondaryDiagonalXCount = 0, secondaryDiagonalOCount = 0;
            for (int i = 0; i < 3; i++) {
                if (board[i][2 - i] == 'x' || board[i][2 - i] == '_') secondaryDiagonalXCount++;
                if (board[i][2 - i] == 'o' || board[i][2 - i] == '_') secondaryDiagonalOCount++;
            }
            if (secondaryDiagonalXCount == 3) xDiagonals++;
            if (secondaryDiagonalOCount == 3) oDiagonals++;
        }

        for (int i = 0; i < 3; i++) {
            if (board[x][i] == 'x' || board[x][i] == '_') xRows++;
            if (board[x][i] == 'o' || board[x][i] == '_') oRows++;
        }

        for (int i = 0; i < 3; i++) {
            if (board[i][y] == 'x' || board[i][y] == '_') xCols++;
            if (board[i][y] == 'o' || board[i][y] == '_') oCols++;
        }

        return (xRows + xCols + xDiagonals) - (oRows + oCols + oDiagonals);
    }

    public PriorityQueue<Cell> findValidMoves(char[][] board, boolean isMaxTurn) {
        PriorityQueue<Cell> result;
        Comparator<Cell> c;
        if (!isMaxTurn) {
            c = Comparator.comparingInt(o -> o.heuristic);
            result = new PriorityQueue<>(c);
        } else {
            c = (o1, o2) -> o2.heuristic - o1.heuristic;
            result = new PriorityQueue<>(c);
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '_') result.add(new Cell(i, j, heuristic(board, i, j)));
            }
        }

        return result;
    }

}
