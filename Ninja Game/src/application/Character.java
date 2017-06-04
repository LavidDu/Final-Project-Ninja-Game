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
		
		state[0] = new Image("/Images/Left.png", 70, 70, true, false);
		state[1] = new Image("/Images/Right.png", 70, 70, true, false);
		state[2] = new Image("/Images/UpLeft.png", 70, 70, true, false);
		state[3] = new Image("/Images/UpRight.png", 70, 70, true, false);
		state[4] = new Image("/Images/DownLeft.png", 70, 70, true, false);
		state[5] = new Image("/Images/DownRight.png", 70, 70, true, false);
		state[6] = new Image("/Images/Up.png", 70, 70, true, false);
		state[7] = new Image("/Images/Rest.png", 70, 70, true, false);
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
			setY(318);
			getView().setImage(state[0]);
			break;
		case right:
			setY(318);
			getView().setImage(state[1]);
			break;
		case upLeft:
			setY(318);
			getView().setImage(state[2]);
			break;
		case upRight:
			setY(318);
			getView().setImage(state[3]);
			break;
		case downLeft:
			setY(318);
			getView().setImage(state[4]);
			break;
		case downRight:
			setY(318);
			getView().setImage(state[5]);
			break;
		case up:
			setY(339);
			getView().setImage(state[6]);
			break;
		case rest:
			setY(318);
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
