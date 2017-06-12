package application;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
/**
 * 
 * @author Andreas Alvear, David Lu, Joey Zhong, Nicholas Ting
 *Game object superclass
 */
public class GameObject {
	private ImageView view;
	private boolean alive = true;
	private double x, y, velX, velY;
	private Point2D velocity = new Point2D(0,0);
	private Bounds bounds;
	
	/**
	 * constructor
	 * @param view is image of GameObject
	 */
	public GameObject(ImageView view){
		this.view = view;
		this.bounds = view.getBoundsInParent();
	}
	
	/**
	 * Gets image associated with GameObject
	 * @return image
	 */
	public ImageView getView(){
		return view;
	}
	
	/**
	 * Gets velocity
	 * @return velocity
	 */
	public Point2D getVelocity(){
		return velocity;
	}
	
	/**
	 * Change velocity
	 * @param dx new x velocity
	 * @param dy new y velocity
	 */
	public void setVelocity(double dx, double dy){
		velocity = velocity.add(dx, dy);
	}
	
	/**
	 * Updates GameObject x and y coordinates
	 */
	public void update(){
		view.setTranslateX(view.getTranslateX() + velocity.getX());
		view.setTranslateY(view.getTranslateY() + velocity.getY());
	}
	
	/**
	 *Shows whether object is alive or not
	 * @return alive or not
	 */
	public boolean isAlive(){
		return alive;
	}
	
	/**
	 * Change whether object is alive or not
	 * @param value alive or not (true or false)
	 */
	public void setAlive(boolean value){
		alive = value;
	}
	
	/**
	 * Checks whether 2 objects are colliding
	 * @param other return object
	 * @return whether collision has occured
	 */
	public boolean isColliding(GameObject other){
		return getView().getBoundsInParent().intersects(other.getView().getBoundsInParent());
	}
	
	/**
	 * Get x coordinate
	 * @return x coordinate
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * Change x coordinate
	 * @param x new x coordinate
	 */
	public void setX(double x) {
		this.x = x;
		view.setX(x);
	}
	
	/**
	 * Get y coordinate
	 * @return y coordinate
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * Change y coordinate
	 * @param y new y coordinate
	 */
	public void setY(double y) {
		this.y = y;
		view.setY(y);
	}
	
	/**
	 * Get velocity in x direction
	 * @return velocity in x direction
	 */
	public double getVelX() {
		return velX;
	}
	
	/**
	 * Change velocity in x direction
	 * @param velX new velocity in x direction
	 */
	public void setVelX(double velX) {
		this.velX = velX;
	}
	
	/**
	 * Get velocity in y direction
	 * @return velocity in y direction
	 */
	public double getVelY() {
		return velY;
	}
	
	/**
	 * Change velocity in y direction
	 * @param velY new velocity in y direction
	 */
	public void setVelY(double velY) {
		this.velY = velY;
	}
	
	/**
	 * Updates area occupied by object
	 */
	public void updateBounds(){
		bounds = view.getBoundsInParent();
	}
	
	/**
	 * Gets area occupied by object
	 * @return area occupied by object
	 */
	public Bounds getBounds(){
		return bounds;
	}
	
}
