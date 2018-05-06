package com.zipper.snake;
import java.util.ArrayList;

//Controls all the game logic .. most important class in this project.
public class ThreadsController extends Thread
{
	ArrayList<ArrayList<DataOfSquare>> Squares = new ArrayList<ArrayList<DataOfSquare>>();
	Tuple headSnakePos;
	int sizeSnake = 3; // 3;
	private static long speed = 50;
	public static int directionSnake;
	private int scoreCounter = 0;
	boolean stopGame = false;
	
	ArrayList<Tuple> positions = new ArrayList<Tuple>();
	Tuple foodPosition;

	// Constructor of ThreadsController
	ThreadsController(Tuple positionDepart)
	{
		setupGameThread(positionDepart);

	} //end of constructor
	
	public void setupGameThread(Tuple positionDepart)
	{
		// Get all the threads
		Squares = GamePlayPanel.Grid;

		headSnakePos = new Tuple(positionDepart.x, positionDepart.y);
		directionSnake = 1;

		// !!! Pointer !!!!
		Tuple headPos = new Tuple(headSnakePos.getX(), headSnakePos.getY());
		positions.clear();
		positions.add(headPos);

		foodPosition = new Tuple(GamePlayPanel.height - 1, GamePlayPanel.width - 1);
		spawnFood(foodPosition);
		
		sizeSnake = 3;
		scoreCounter = 0;
	} //end of setupGameThread method
	
	public static void setSpeed(long s)
	{
		speed = s;
	}
	
	public static long getSpeed()
	{
		return speed;
	}
	
	
	
	public int getScoreCounter()
	{
		return scoreCounter;
	}
	
	public void setstopGame(boolean gameStatus)
	{
		stopGame = gameStatus;
	} //end of setStopGame method
	
	// Important part :
	public void run() {
		while (true) {
			moveInterne(directionSnake);
			checkCollision();
			moveExterne();
			deleteTail();
			pauser();
		}
	}

	// delay between each move of the snake
	private void pauser() {
		try {
			sleep(speed);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Checking if the snake bites itself or is eating
	private void checkCollision() {
		Tuple posCritique = positions.get(positions.size() - 1);
		for (int i = 0; i <= positions.size() - 2; i++) {
			boolean biteItself = posCritique.getX() == positions.get(i).getX()
					&& posCritique.getY() == positions.get(i).getY();
			if (biteItself) {
				stopTheGame();
			}
		}

		boolean eatingFood = posCritique.getX() == foodPosition.y && posCritique.getY() == foodPosition.x;
		if (eatingFood) {
			System.out.println("Yummy!");
			GamePlayPanel.scoreTxtField.setText( Integer.toString(++scoreCounter) );
			sizeSnake = sizeSnake + 1;
			foodPosition = getValAleaNotInSnake();

			spawnFood(foodPosition);
		}
	}

	// Stops The Game
	private void stopTheGame() {
		System.out.println("COLISION! \n");
		stopGame = true;
		while (stopGame) {
			pauser();
		}
	}

	// Put food in a position and displays it
	private void spawnFood(Tuple foodPositionIn) {
		Squares.get(foodPositionIn.x).get(foodPositionIn.y).lightMeUp(1);
	}

	// return a position not occupied by the snake
	private Tuple getValAleaNotInSnake() {
		Tuple p;
		int ranX = 0 + (int) (Math.random() * 19);
		int ranY = 0 + (int) (Math.random() * 19);
		p = new Tuple(ranX, ranY);
		for (int i = 0; i <= positions.size() - 1; i++) {
			if (p.getY() == positions.get(i).getX() && p.getX() == positions.get(i).getY()) {
				ranX = 0 + (int) (Math.random() * 19);
				ranY = 0 + (int) (Math.random() * 19);
				p = new Tuple(ranX, ranY);
				i = 0;
			}
		}
		return p;
	}

	// Moves the head of the snake and refreshes the positions in the arraylist
	// 1:right 2:left 3:top 4:bottom 0:nothing
	private void moveInterne(int dir) {
		switch (dir) {
		case 4:
			headSnakePos.ChangeData(headSnakePos.x, (headSnakePos.y + 1) % 20);
			positions.add(new Tuple(headSnakePos.x, headSnakePos.y));
			break;
		case 3:
			if (headSnakePos.y - 1 < 0) {
				headSnakePos.ChangeData(headSnakePos.x, 19);
			} else {
				headSnakePos.ChangeData(headSnakePos.x, Math.abs(headSnakePos.y - 1) % 20);
			}
			positions.add(new Tuple(headSnakePos.x, headSnakePos.y));
			break;
		case 2:
			if (headSnakePos.x - 1 < 0) {
				headSnakePos.ChangeData(19, headSnakePos.y);
			} else {
				headSnakePos.ChangeData(Math.abs(headSnakePos.x - 1) % 20, headSnakePos.y);
			}
			positions.add(new Tuple(headSnakePos.x, headSnakePos.y));

			break;
		case 1:
			headSnakePos.ChangeData(Math.abs(headSnakePos.x + 1) % 20, headSnakePos.y);
			positions.add(new Tuple(headSnakePos.x, headSnakePos.y));
			break;
		}
	}

	// Refresh the squares that needs to be
	private void moveExterne() {
		for (Tuple t : positions) {
			int y = t.getX();
			int x = t.getY();
			Squares.get(x).get(y).lightMeUp(0);

		}
	}

	// Refreshes the tail of the snake, by removing the superfluous data in
	// positions arraylist
	// and refreshing the display of the things that is removed
	private void deleteTail() {
		int cmpt = sizeSnake;
		for (int i = positions.size() - 1; i >= 0; i--) {
			if (cmpt == 0) {
				Tuple t = positions.get(i);
				Squares.get(t.y).get(t.x).lightMeUp(2);
			} else {
				cmpt--;
			}
		}
		cmpt = sizeSnake;
		for (int i = positions.size() - 1; i >= 0; i--) {
			if (cmpt == 0) {
				positions.remove(i);
			} else {
				cmpt--;
			}
		}
	} //end of deleteTail method
} // end of ThreadsController class
