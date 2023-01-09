import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class TicTacToe {

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> compPositions = new ArrayList<Integer>();
            
    public static void main(String[] args) {
        
        //Array of empty Tic Tac Toe spots
        char[][] gameBoard = {{' ','|',' ','|', ' '},
                              {'-','+','-','+', '-'},
                              {' ','|',' ','|', ' '},
                              {'-','+','-','+', '-'},
                              {' ','|',' ','|', ' '}};
        
        //Prints empty game board for player 
        printGameBoard(gameBoard);
        
        //Continuous loop for continued plays
        while(true){
            //Reads players selected position
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter your placement (1-9): ");
            int playerPos = scan.nextInt();
            
            //Checks players position against existing positions selected by player or computer
            while(playerPositions.contains(playerPos) || compPositions.contains(playerPos)){
                System.out.println("Spot taken! Enter a different position: ");
                playerPos = scan.nextInt();
            }
            
            //Places player piece on game board
            placePiece(gameBoard, playerPos, "player");
            
            //Checks if there is a winner after placement
            String result = checkWinner();
            
            //If there is a String stored in result, it will print a result.
            if(result.length() > 0){
                printGameBoard(gameBoard);
                System.out.println(result);
                break;
            }

            //Computer creates randomized position 1-9
            Random rand = new Random();
            int compPos = rand.nextInt(9)+1;
            
            //Checks players position against existing positions selected by player or computer
            while(playerPositions.contains(compPos) || compPositions.contains(compPos)){
                compPos = rand.nextInt(9)+1;
            }
            
            //Places computer piece on game board
            placePiece(gameBoard, compPos, "computer");

            //Prints the board at the end of both turns
            printGameBoard(gameBoard);
            
            //Checks if there is a winner after placement
            result = checkWinner();
            if(result.length() > 0){
                System.out.println(result);
                break;
            }
        }  
    }
    
    public static void printGameBoard(char[][] gameBoard){
        for(char[] row : gameBoard){
            for(char c : row){
                System.out.print(c);
            }
            System.out.println();
        }
    }
    
    public static void placePiece(char[][] gameBoard, int pos, String user){
        char symbol = ' ';
        
        if(user.equals("player")){
            symbol = 'X';
            playerPositions.add(pos);
        }
        else if(user.equals("computer")){
            symbol = 'O';
            compPositions.add(pos);
        }
        
        switch(pos){
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        } 
        
        
    }
    
    public static String checkWinner(){
        
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(3, 5, 7);
        
        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);
        
        for(List l: winning){
            if(playerPositions.containsAll(l)){
                return "Congratulations you won!";
            } else if(compPositions.containsAll(l)){
                return "Computer wins! Sorry!";
            }
        }
        
        if (playerPositions.size() + compPositions.size() == 9){
                return "No winner. CAT game.";
        }
        
        return "";
    }
    
    public static void clearBoard(char[][] gameBoard){
        
        gameBoard[0][0] = ' ';
        gameBoard[0][2] = ' ';
        gameBoard[0][4] = ' ';
        gameBoard[2][0] = ' ';
        gameBoard[2][2] = ' ';
        gameBoard[2][4] = ' ';
        gameBoard[4][0] = ' ';
        gameBoard[4][2] = ' ';
        gameBoard[4][4] = ' ';
        
        playerPositions.clear();
        compPositions.clear();
    }
}
