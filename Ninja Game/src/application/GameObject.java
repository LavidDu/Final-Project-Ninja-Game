package application;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

public class GameObject {
	
	private ImageView view;
	private boolean alive = true;
	private double x, y, velX, velY;
	private Point2D velocity = new Point2D(0,0);
	private Bounds bounds;
	
	public GameObject(ImageView view){
		this.view = view;
		this.bounds = view.getBoundsInParent();
	}
	
	public ImageView getView(){
		return view;
	}
	
	public Point2D getVelocity(){
		return velocity;
	}
	
	public void setVelocity(double dx, double dy){
		velocity = velocity.add(dx, dy);
	}
	
	public void update(){
		view.setTranslateX(view.getTranslateX() + velocity.getX());
		view.setTranslateY(view.getTranslateY() + velocity.getY());
	}
	
	public boolean isAlive(){
		return alive;
	}
	
	public void setAlive(boolean value){
		alive = value;
	}
	
	public boolean isColliding(GameObject other){
		return getView().getBoundsInParent().intersects(other.getView().getBoundsInParent());
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
		view.setX(x);
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
		view.setY(y);
	}

	public double getVelX() {
		return velX;
	}

	public void setVelX(double velX) {
		this.velX = velX;
	}

	public double getVelY() {
		return velY;
	}

	public void setVelY(double velY) {
		this.velY = velY;
	}
	
	public void updateBounds(){
		bounds = view.getBoundsInParent();
	}
	
	public Bounds getBounds(){
		return bounds;
	}
	
}
