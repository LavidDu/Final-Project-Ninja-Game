package application;

import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Projectile extends GameObject{

	private double cx;
	private double cy;
	private double targetx;
	private double targety;
	
	public Projectile(double x, double y) {
		super(new ImageView());
		this.targetx = x;
		this.targety = y;
		
		getView().setImage(new Image("shuriken.png", 25, 20, true, false));
	}
	
	public void update(){
		getView().setTranslateX(getView().getTranslateX() + getVelocity().getX());
		getView().setTranslateY(getView().getTranslateY() + getVelocity().getY());
		getView().setRotate(getView().getRotate() + 5);
		cx = getX() + getView().getTranslateX();
		cy = getY() + getView().getTranslateY();
	}
	
	public void setCX(double x){
		cx = x; 
	}
	
	public double getCX(){
		return cx;
	}
	
	public void setCY(double y){
		cy = y;
	}
	
	public double getCY(){
		return cy;
	}
	
	public void test(GraphicsContext gc){
		gc.setFill(Color.WHITE);
		Bounds b = getView().getBoundsInParent();
		for(double i = 0; i < 700; i++){
			for(int j = 0; j < 700; j++){
				if(b.contains(i, j)){
					gc.fillOval(i, j, 1, 1);
				}
			}
		}
	}
	
	public boolean collideWithSword(Sword s){
		if(isColliding(s)){
			return true;
		}
		return false;
	}
	
}
