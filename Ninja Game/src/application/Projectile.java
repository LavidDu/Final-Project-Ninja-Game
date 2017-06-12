package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Projectile extends GameObject {

	private final double targetX = 328;
	private final double targetY = 346;

	public Projectile(double x, double y) {
		super(new ImageView());
		setX(x);
		setY(y);
		getView().setImage(new Image("/Images/shuriken.png", 25, 25, true, false));
	}

	public void update() {
		getView().setTranslateX(getView().getTranslateX() + getVelocity().getX());
		getView().setTranslateY(getView().getTranslateY() + getVelocity().getY());
		getView().setRotate(getView().getRotate() + 5);
		updateBounds();
	}

	public void setVelocity(boolean neg) {
		double x1 = getX();
		double y1 = getY();
		double x2 = targetX;
		double y2 = targetY;

		double m = (y2 - y1) / (x2 - x1);
		//double b = y1 - m * x1;

		if (neg) {
			setVelocity(1 / m * 2, 2);
		} else {
			setVelocity(2, 2 * m);
		}

		/*
		 * gc.setFill(Color.RED); if(neg){ for (double x = x2; x <= x1; x++) {
		 * double y = m * x + b; gc.fillOval(x, y, 2, 2); } } for (double x =
		 * x1; x <= x2; x++) { double y = m * x + b; gc.fillOval(x, y, 2, 2); }
		 */
	}

	public boolean collidewithPlayer() {
		return getBounds().contains(targetX, targetY);
	}

}
