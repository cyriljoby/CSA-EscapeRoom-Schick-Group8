
/*
* Problem 1: Escape Room
* 
* V1.0
* 10/10/2019
* Copyright(c) 2019 PLTW to present. All rights reserved
*/
import java.util.Scanner;

/**
 * Create an escape room game where the player must navigate
 * to the other side of the screen in the fewest steps, while
 * avoiding obstacles and collecting prizes.
 */
class Main {

  // describe the game with brief welcome message
  // determine the size (length and width) a player must move to stay within the
  // grid markings
  // Allow game commands:
  // right, left, up, down: if you try to go off grid or bump into wall, score
  // decreases
  // jump over 1 space: you cannot jump over walls
  // if you land on a trap, spring a trap to increase score: you must first check
  // if there is a trap, if none exists, penalty
  // pick up prize: score increases, if there is no prize, penalty
  // help: display all possible commands
  // end: reach the far right wall, score increase, game ends, if game ended
  // without reaching far right wall, penalty
  // replay: shows number of player steps and resets the board, you or another
  // player can play the same board
  // Note that you must adjust the score with any method that returns a score
  // Optional: create a custom image for your player use the file player.png on
  // disk

  // set up the game
  // boolean play = true;
  // while (play)
  // {
  // get user input and call game methods to play
  // play = false;
  // }
  // *///

  public static void main(String[] args) {
    // welcome message
    System.out.println("Welcome to EscapeRoom!");
    System.out.println("Get to the other side of the room, avoiding walls and invisible traps,");
    System.out.println("pick up all the prizes.\n");

    // set up game

    GameGUI game = new GameGUI();
    game.createBoard();

    // starting location of sprite

    // size of move
    int m = 60;
    // individual player moves
    int px = 0;
    int py = 0;

    int score = 0;

    Scanner in = new Scanner(System.in);
    String[] validCommands = { "right", "left", "up", "down", "r", "l", "u", "d",
        "jump", "jr", "jumpleft", "jl", "jumpup", "ju", "jumpdown", "jd",
        "pickup", "p", "quit", "q", "replay", "help", "?", "find", "f", "rm", "remove"};
    String[] trapValidCommands = { "right", "left", "up", "down", "r", "l", "u", "d"};

    // set up game
    boolean play = true;
    while (play) {
      // {
      /* TODO: get all the commands working */
      /* Your code here */
      System.out.println("Enter a command: ");

      String input = UserInput.getValidInput(validCommands);
      px=0;
      py=0;
      if (input.equals("quit")|| input.equals("q")){
          game.endGame();
          play = false;
      }
      else if (input.equals("replay")){
          game.replay();
          play = true;
      }

      else if (input.equals("help")||  input.equals("?"))
      {
        System.out.println("Valid commands: To move in various Directions: right, left, up, down, r, l, u, d");
        System.out.println("To jump: jump jr, jumpleft, jl, jumpup, ju, jumpdown, jd");
        System.out.println("pickup, p, quit, q, replay, help, ?");
      }
      if (input.equals("right") || input.equals("r")) {

        px = m;
        py = 0;

      }

      else if (input.equals("jump") || input.equals("jr")) {

        px = 2 * m;
        py = 0;

      }

      else if (input.equals("left") || input.equals("l")) {

        px = -m;
        py = 0;

      }

      else if (input.equals("jumpleft") || input.equals("jl")) {

        px = -2 * m;
        py = 0;

      }

      else if (input.equals("up") || input.equals("u")) {

        px = 0;
        py = -m;

      }

      else if (input.equals("jumpup") || input.equals("ju")) {

        px = 0;
        py = -2 * m;

      }

      else if (input.equals("down") || input.equals("d")) {
        System.out.println("down");
        px = 0;
        py = m;

      }

      else if (input.equals("jumpdown") || input.equals("jd")) {

        px = 0;
        py = 2 * m;

      }

      if (input.equals("pickup") || input.equals("p")) {
        px = 0;
        py = 0;
        score += game.pickupPrize();
      }

      score += game.movePlayer(px, py);

      if (input.equals("find") || input.equals("f")) {
        if(game.isTrap(0,-m))
        {
          System.out.println("There is a trap above you");

        }
        if(game.isTrap(0,m))
        {
          System.out.println("There is a trap below you");

        }
        if(game.isTrap(m,0))
        {
          System.out.println("There is a trap to the right");

        }
        if(game.isTrap(-m,0))
        {
          System.out.println("There is a trap to the left");

        }


        }
      else if (input.equals("rm") || input.equals("remove")){
        System.out.println("What direction would you like to remove the trap");
        String springInput = UserInput.getValidInput(trapValidCommands);
        if (springInput.equals("r") ||springInput.equals("right") ){
          score+=game.springTrap(m, 0);
        }
        else if (springInput.equals("l") ||springInput.equals("left") ){
          score+=game.springTrap(-m, 0);
        }
        else if (springInput.equals("u") ||springInput.equals("up") ){
          score+=game.springTrap(0, -m);
        }
        else if (springInput.equals("d") ||springInput.equals("down") ){
          score+=game.springTrap(0, m);
        }
      }
      else if(game.isTrap(0, 0)){
        System.out.println("Hit by Trap");
        score-=5;
      }

      System.out.println("Score: "+ score);

}
      
    // end game
    score += game.endGame();

    System.out.println("score=" + score);
    System.out.println("steps=" + game.getSteps());
  }

}
