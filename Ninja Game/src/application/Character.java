package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * 
 * @author Andreas Alvear, David Lu, Joey Zhong, Nicholas Ting
 *
 */
public  class Character extends GameObject{
	
	
	private int score;
	private Text score_text;
	private Image[] state;
	private boolean alive;
	private BlockDir blockDirection;
	private final double targetX = 328;
	private final double targetY = 346;
	
	/**
	 * Constructor class for the character, places it in the right spot at rest
	 */
	public Character(){
		super(new ImageView());
		setX(301);
		setY(318);
			
		this.score = 0;
		this.state = new Image[8];
		this.blockDirection = BlockDir.rest;
		this.score_text = new Text(20, 450, "Score: 0");
		score_text.setFill(Color.GOLD);
		score_text.setFont(new Font(40));
		
		state[0] = new Image("/Images/Left.png", 70, 70, true, false);
		state[1] = new Image("/Images/Right.png", 70, 70, true, false);
		state[2] = new Image("/Images/UpLeft.png", 70, 70, true, false);
		state[3] = new Image("/Images/UpRight.png", 70, 70, true, false);
		state[4] = new Image("/Images/DownLeft.png", 70, 70, true, false);
		state[5] = new Image("/Images/DownRight.png", 70, 70, true, false);
		state[6] = new Image("/Images/Up.png", 70, 70, true, false);
		state[7] = new Image("/Images/Rest.png", 70, 70, true, false);
	}
	
	public double getTargetX(){
		return targetX;
	}
	
	public double getTargetY(){
		return targetY;
	}
	
	/**
	 * Block direction
	 * @return block direction
	 */
	public BlockDir getBlockDir(){
		return blockDirection;
	}
	
	/**
	 * Change block direction enum, not actual 
	 * @param b new block direction enum
	 */
	public void setBlockDir(BlockDir b){
		blockDirection = b;
	}
	
	/**
	 * Change score
	 */
	public void setText(String s){
		score_text.setText(s);
	}
	
	/**
	 * Get score
	 * @return score
	 */
	public Text getText(){
		return score_text;
	}
	
	/**
	 * Actually changes the state of the character based on BlockDir enum
	 */
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
	
	/**
	 * Update score text
	 * @param s score increase
	 */
	public void updateScore(int s){
		score += s;
		setText("Score: " + score);
	}
	/**
	 * Get score
	 * @return the score of the character
	 */
	public int getScore(){
		return score;
	}
	
	/**
	 * Returns if the character has been hit with projectiles or not
	 * @return whether character is alive or not
	 */
	public boolean isAlive() {
		return alive;
	}
	
	/**
	 * Changes whether character is alive or not
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
}
