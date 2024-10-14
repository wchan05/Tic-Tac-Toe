// Main class

public class Main 
{
public static void main(String[] args) 
  {
    Player player1 = new Player("Willa", "X");
    Player player2 = new Player("Oliver", "O");
    TicTacToe game = new TicTacToe(player1, player2);
    game.run();
  }
}

// TicTacToe class

import java.util.Scanner;

public class TicTacToe 
{

    private Player[] players;
    private String[][] board;
    private int turnIndex;

    public TicTacToe(Player player1, Player player2) 
    {
        players = new Player[2];
        players[0] = player1;
        players[1] = player2;
        board = new String[3][3];
        for (int i = 0; i < 3; i++) 
        {
            for (int j = 0; j < 3; j++) 
            {
                board[i][j] = " ";
            }
        }
        turnIndex = (int)(Math.random() * 2);
    }

    public void printBoard() 
    {
     for (int i = 0; i < 3; i++) 
       {
            for (int j = 0; j < 3; j++) 
            {
                System.out.print(board[i][j]);
                if (j < 2) 
                {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < 2) 
            {
                System.out.println("-+-+-");
            }
       }
    }
        public boolean isOver() 
        {
            for (int i = 0; i < 3; i++)
                // Column check
                // Row Check
            {
                if (board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2]) && !board[i][0].equals(" ")) 
                {
                    return true;
                }
                if (board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i]) && !board[0][i].equals(" ")) 
                {
                    return true;
                }
            }
                // Diagonal check from the left
            if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) && !board[0][0].equals(" ")) 
            {
                return true;
            }
                // Diagonal check from the right
            if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) && !board[0][2].equals(" ")) 
            {
                return true;
            }

            // Check for full board
            for (int i = 0; i < 3; i++) 
            {
                for (int j = 0; j < 3; j++) 
                {
                    if (board[i][j].equals(" ")) 
                    {
                        return false;
                    }
                }
            }
            return true;
        }

        // Method to make a move
        public void move() 
        {
            Scanner scanner = new Scanner(System.in);
            Player currentPlayer = players[turnIndex];
            int place;

            while (true) 
            {
                place = currentPlayer.selectMove();
                if (place == 1 && board[0][0].equals(" ")) 
                {
                    board[0][0] = currentPlayer.getToken();
                    break;
                } 
                else if(place == 2 && board[0][1].equals(" "))
                {
                    board[0][1] = currentPlayer.getToken();
                    break;
                }
                else if(place == 3 && board[0][2].equals(" "))
                {
                    board[0][2] = currentPlayer.getToken();
                    break;
                }
                else if(place == 4 && board[1][0].equals(" "))
                {
                    board[1][0] = currentPlayer.getToken();
                    break;
                }
                else if(place == 5 && board[1][1].equals(" "))
                {
                    board[1][1] = currentPlayer.getToken();
                    break;
                }
                else if(place == 6 && board[1][2].equals(" "))
                {
                    board[1][2] = currentPlayer.getToken();
                    break;
                }
                else if(place == 7 && board[2][0].equals(" "))
                {
                    board[2][0] = currentPlayer.getToken();
                    break;
                }
                else if(place == 8 && board[2][1].equals(" "))
                {
                    board[2][1] = currentPlayer.getToken();
                    break;
                }
                else if(place == 9 && board[2][2].equals(" "))
                {
                    board[2][2] = currentPlayer.getToken();
                    break;
                }
                else 
                {
                    System.out.println("This move is not valid. Try again.");
                }
            }
            // Changing the turn
            turnIndex = (turnIndex + 1) % 2;
        }

        // Method to run the game
        public void run() 
        {
            System.out.println("This is Tic Tac Toe! Can you get Three in a Row?");
            System.out.println(players[0].getName() + " vs " + players[1].getName());
            System.out.println(players[turnIndex].getName() + " is up first!");
            while (!isOver()) 
            {
                printBoard();
                move();
            }

            printBoard();
            // The winnerIndex is the last person who went
            int winnerIndex = (turnIndex + 1) % 2;
            if (isOverBeforeEmpty(players[winnerIndex].getToken())) 
            {
                System.out.println(players[winnerIndex].getName() + " is VICTORIOUS!");
            } else 
            {
                System.out.println("OH NO THERE IS A DRAW! Rematch?");
            }
        }
        // Checks specific token (X or O)
        private boolean isOverBeforeEmpty(String token) 
        {
            // Check rows and columns for a win
            // Same as isOver, except only checking for a winner not if the board is full or not
            for (int i = 0; i < 3; i++) {
                if (board[i][0].equals(token) && board[i][1].equals(token) && board[i][2].equals(token)) 
                {
                    return true;
                }
                if (board[0][i].equals(token) && board[1][i].equals(token) && board[2][i].equals(token)) 
                {
                    return true;
                }
            }

            // Check diagonals for a win
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

        // Getters
        public Player[] getPlayers() 
        {
            return players;
        }
        public String[][] getBoard() 
        {
            return board;
        }
        public int getTurnIndex() 
        {
            return turnIndex;
        }
}

// Player class

import java.util.Scanner;

public class Player 
{
    private String name;
    private String token;

    public Player(String n, String t) 
    {
        this.name = n;
        this.token = t;
    }
    public String toString()
    {
        return "Name: " + name + " Token: " + token;
    }
    public String getName() 
    {
        return name;
    }

    public String getToken() 
    {
        return token;
    }

    public int selectMove() 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println(name + ", enter your move (# 1-9)");
        int place = scanner.nextInt();
        return place;
    }
}
