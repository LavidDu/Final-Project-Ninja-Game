package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * Enemy class -> incompleted as of submission
 * @author Andreas Alvear
 *
 */
public class Enemy extends GameObject {

	private Image[] state;
	private EnemyState enemyState;
	private boolean right;
	private boolean alive;
	private int velX, velY;
	private double gravity = 0.7;
	
	/**
	 * constructor
	 * @param x starting x coordinate
	 * @param y starting y coordinate
	 */
	public Enemy(int x, int y) {
		super(new ImageView());
		setX(x);
		setY(y);

		this.state = new Image[8];
		this.enemyState = EnemyState.standingR;

		state[0] = new Image("/Images/StandingR.png", 70, 70, true, false);
		state[1] = new Image("/Images/SlidingR.png", 70, 70, true, false);
		state[2] = new Image("/Images/JumpingR.png", 70, 70, true, false);
		state[3] = new Image("/Images/ThrowingR.png", 70, 70, true, false);
		state[4] = new Image("/Images/StandingL.png", 70, 70, true, false);
		state[5] = new Image("/Images/SlidingL.png", 70, 70, true, false);
		state[6] = new Image("/Images/JumpingL.png", 70, 70, true, false);
		state[7] = new Image("/Images/ThrowingL.png", 70, 70, true, false);
		if (getView().getTranslateX() < 0) {
			right = true;
		}
	}
	
	/**
	 * Updates state based on EnemyState enum
	 */
	public void switchState() {
		switch (enemyState) {
		case standingR:
			getView().setImage(state[0]);
			break;
		case slidingR:
			getView().setImage(state[1]);
			break;
		case jumpingR:
			getView().setImage(state[2]);
			break;
		case throwingR:
			getView().setImage(state[3]);
			break;
		case standingL:
			getView().setImage(state[4]);
			break;
		case slidingL:
			getView().setImage(state[5]);
			break;
		case jumpingL:
			getView().setImage(state[6]);
			break;
		case throwingL:
			getView().setImage(state[7]);
			break;
		}
	}

	/*
	 * public EnemyState setEnemyState(){ if (right){ if(getVelocity().getX() ==
	 * 0 && getVelocity().getY() == 0){ return EnemyState.standingR;
	 * 
	 * } if(getView().getTranslateX() > 270 && getView().getTranslateX() < 300
	 * && getView().getTranslateY() == 330){ return EnemyState.slidingR; } }
	 * return null; }
	 */
}
