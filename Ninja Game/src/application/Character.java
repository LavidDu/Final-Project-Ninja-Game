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
		this.state = new Image[8];
		this.blockDirection = BlockDir.rest;
		
		state[0] = new Image("Left.png", 70, 70, true, false);
		state[1] = new Image("Right.png", 70, 70, true, false);
		state[2] = new Image("UpLeft.png", 70, 70, true, false);
		state[3] = new Image("UpRight.png", 70, 70, true, false);
		state[4] = new Image("DownLeft.png", 70, 70, true, false);
		state[5] = new Image("DownRight.png", 70, 70, true, false);
		state[6] = new Image("Up.png", 70, 70, true, false);
		state[7] = new Image("Rest.png", 70, 70, true, false);
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
			getView().setY(318);
			getView().setImage(state[0]);
			break;
		case right:
			getView().setY(318);
			getView().setImage(state[1]);
			break;
		case upLeft:
			getView().setY(318);
			getView().setImage(state[2]);
			break;
		case upRight:
			getView().setY(318);
			getView().setImage(state[3]);
			break;
		case downLeft:
			getView().setY(318);
			getView().setImage(state[4]);
			break;
		case downRight:
			getView().setY(318);
			getView().setImage(state[5]);
			break;
		case up:
			getView().setY(339);
			getView().setImage(state[6]);
			break;
		case rest:
			getView().setY(318);
			getView().setImage(state[7]);
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
