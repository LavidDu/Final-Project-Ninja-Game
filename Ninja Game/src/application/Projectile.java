package application;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Projectile extends GameObject{

	private double targetx;
	private double targety;
	
	public Projectile(double x, double y) {
		super(new ImageView());
		this.targetx = x;
		this.targety = y;
		getView().setImage(new Image("/Images/shuriken.png", 25, 25, true, false));
	}
	
	public void update(){
		getView().setTranslateX(getView().getTranslateX() + getVelocity().getX());
		getView().setTranslateY(getView().getTranslateY() + getVelocity().getY());
		getView().setRotate(getView().getRotate() + 5);
		updateBounds();
	}
	
	
	public boolean collideWithSword(Sword s){
		if(isColliding(s)){
			return true;
		}
		return false;
	}
	
}
