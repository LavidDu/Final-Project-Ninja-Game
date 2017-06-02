package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public  class Character extends GameObject{
	
	private int score;
	
	private Image[] state;
	private boolean alive;
	private BlockDir blockDirection;
	
	public Character(){
		super(new ImageView());
		setX(301);
		setY(318);
		
		this.score = 0;
		this.state = new Image[6];
		this.blockDirection = BlockDir.left;
		
		state[0] = new Image("Left.png", 70, 70, true, false);
		state[1] = new Image("Right.png", 70, 70, true, false);
		state[2] = new Image("UpLeft.png", 70, 70, true, false);
		state[3] = new Image("UpRight.png", 70, 70, true, false);
		state[4] = new Image("DownLeft.png", 70, 70, true, false);
		state[5] = new Image("DownRight.png", 70, 70, true, false);
	}
	
	public BlockDir getBlockDir(){
		return blockDirection;
	}
	
	public void setBlockDir(BlockDir b){
		blockDirection = b;
	}
	
	public void switchState(){
		switch(blockDirection){
		case left:
			getView().setImage(state[0]);
			break;
		case right:
			getView().setImage(state[1]);
			break;
		case upLeft:
			getView().setImage(state[2]);
			break;
		case upRight:
			getView().setImage(state[3]);
			break;
		case downLeft:
			getView().setImage(state[4]);
			break;
		case downRight:
			getView().setImage(state[5]);
			break;
		}
		
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
}
