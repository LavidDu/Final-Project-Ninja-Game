package application;

import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Sword extends GameObject {

	private Image[] state;
	private BlockDir blockDirection;

	public Sword() {
		super(new ImageView());
		setX(263);
		setY(347);

		this.state = new Image[2];
		state[0] = new Image("Sword.png", 40, 5, true, false);
		state[1] = new Image("Sword2.png", 40, 5, true, false);
	}

	public void setLeft() {
		getView().setImage(state[0]);
		getView().setRotate(0);
		setX(263);
		setY(347);
		blockDirection = BlockDir.left;
	}

	public void setRight() {
		getView().setImage(state[1]);
		getView().setRotate(0);
		setX(370);
		setY(347);
		blockDirection = BlockDir.right;
	}

	public void setLeftandUp() {
		getView().setImage(state[0]);
		getView().setRotate(25);
		setX(263);
		setY(322);
		blockDirection = BlockDir.upLeft;
	}

	public void setRightandUp() {
		getView().setImage(state[1]);
		getView().setRotate(-25);
		setX(369);
		setY(322);
		blockDirection = BlockDir.upRight;
	}

	public void setDownandLeft() {
		getView().setImage(state[0]);
		getView().setRotate(-30);
		setX(266);
		setY(355);
		blockDirection = BlockDir.downLeft;
	}

	public void setDownandRight() {
		getView().setImage(state[1]);
		getView().setRotate(30);
		setX(369);
		setY(355);
		blockDirection = BlockDir.downRight;
	}

	public void test(GraphicsContext gc) {
		Bounds n = getView().getBoundsInParent();
		double x1 = n.getMinX();
		double y1 = n.getMinY();
		double x2 = n.getMaxX();
		double y2 = n.getMaxY();
		
		if(blockDirection == BlockDir.upRight || blockDirection == BlockDir.downLeft){
			double tmp = y1;
			y1 = y2;
			y2 = tmp;
		}
		
		double m =  (y2 - y1) /  (x2 - x1);
		double b = y1 - m * x1;

		gc.setFill(Color.RED);
		for (double x = x1; x <= x2; x++) {
			double y = m * x + b;
			gc.fillOval(x, y, 3, 3);
		}
	}
}
