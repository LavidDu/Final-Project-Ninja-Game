package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Enemy extends GameObject{
	
	private Image[] state;
	private EnemyState enemyState;
	private boolean alive;
	private int velX, velY;

	public Enemy(ImageView view) {
		super(view);
		this.state = new Image[8];
		state[0] = new Image("/Images/StandingR.png", 70, 70, true, false);
		state[1] = new Image("/Images/SlidingR.png", 70, 70, true, false);
		state[2] = new Image("/Images/JumpingR.png", 70, 70, true, false);
		state[3] = new Image("/Images/ThrowingR.png", 70, 70, true, false);
		state[4] = new Image("/Images/StandingL.png", 70, 70, true, false);
		state[5] = new Image("/Images/SlidingL.png", 70, 70, true, false);
		state[6] = new Image("/Images/JumpingL.png", 70, 70, true, false);
		state[7] = new Image("/Images/ThrowingL.png", 70, 70, true, false);
	}

}
