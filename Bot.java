public class Bot extends Player
{    
  public Bot(String name, String token)
  {
    super(name, token);
  }
  
  public int selectMove(TicTacToe b) 
  {
      // win or block the opponent
      String[][] board = b.getBoard();
      for (int i = 1; i <= 9; i++) 
      {
          if (canWin(board, i, getToken())) 
          {
              return i;
          }
          if (canWin(board, i, getOpponentToken(b))) 
          {
              return i;
          }
      }

      // Take the center if available
      if (board[1][1].equals(" ")) 
      {
          return 5;
      }

      // Take a corner if available
      int[] corners = {1, 3, 7, 9};
      for (int c : corners) 
      {
          int row = (c - 1) / 3;
          int col = (c - 1) % 3;
          if (board[row][col].equals(" ")) 
          {
              return c;
          }
      }

      // Take any available space
      for (int i = 1; i <= 9; i++) 
      {
          int row = (i - 1) / 3;
          int col = (i - 1) % 3;
          if (board[row][col].equals(" ")) 
          {
              return i;
          }
      }

      return -1; // If no move is possible
  }

  private boolean canWin(String[][] board, int move, String token) 
  {
      int row = (move - 1) / 3;
      int col = (move - 1) % 3;
      if (!board[row][col].equals(" ")) 
      {
          return false;
      }
      board[row][col] = token;
      boolean isWin = checkWin(board, token);
      board[row][col] = " "; // Reset the move
      return isWin;
  }

  private boolean checkWin(String[][] board, String token) 
  {
      for (int i = 0; i < 3; i++) 
      {
          if (board[i][0].equals(token) && board[i][1].equals(token) && board[i][2].equals(token)) 
          {
              return true;
          }
          if (board[0][i].equals(token) && board[1][i].equals(token) && board[2][i].equals(token)) 
          {
              return true;
          }
      }

      if (board[0][0].equals(token) && board[1][1].equals(token) && board[2][2].equals(token)) 
      {
          return true;
      }
      if (board[0][2].equals(token) && board[1][1].equals(token) && board[2][0].equals(token)) 
      {
          return true;
      }

      return false;
  }

public String getOpponentToken(TicTacToe b) 
{
    String botToken = getToken();
    String[][] board = b.getBoard();
    for (int row = 0; row < 3; row++) 
    {
        for (int col = 0; col < 3; col++) 
        {
        String token = board[row][col];
        if (!token.equals(" ") && !token.equals(botToken)) 
            {
                return token;
            }
        }
    }
        return null;
}
}
