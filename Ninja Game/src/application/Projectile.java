package application;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Projectile extends GameObject{

	private Image sprite;
	private int cx;
	private int cy;
	private int targetx;
	private int targety;
	
	public Projectile(int x, int y) {
		super(new ImageView());
		this.targetx = x;
		this.targety = y;
		
		
		getView().setImage(new Image("shuriken.png", 25, 20, true, false));
	}
	
	public void update(){
		getView().setTranslateX(getView().getTranslateX() + getVelocity().getX());
		getView().setTranslateY(getView().getTranslateY() + getVelocity().getY());
		getView().setRotate(getView().getRotate() + 5);
		cx = getX() + (int)getView().getTranslateX();
		cy = getY() + (int)getView().getTranslateY();
	}
	
	public void setCX(int x){
		cx = x; 
	}
	
	public int getCX(){
		return cx;
	}
	
	public void setCY(int y){
		cy = y;
	}
	
	public int getCY(){
		return cy;
	}
	
	public boolean collideWithSword(Sword s){
		if(isColliding(s)){
			return true;
		}
		return false;
	}
	
}
