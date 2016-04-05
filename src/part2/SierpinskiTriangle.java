package part2;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import draw.UltraDraw;
import java.awt.geom.Point2D;

public class SierpinskiTriangle implements MouseListener, MouseMotionListener {

	Point2D.Double top, right, left;
	public int n = 0;
	public Color color = Color.BLUE;

	public SierpinskiTriangle() {
		UltraDraw.addMouseListener(this);
		UltraDraw.addMouseMotionListener(this);
		UltraDraw.setXscale(0, 150);
		UltraDraw.setYscale(75, 150);

		top = new Point2D.Double(75, 150);
		right = new Point2D.Double(150, 75);
		left = new Point2D.Double(0, 75);

		UltraDraw.clear(UltraDraw.GRAY);
	}

	public void sierpinski(Point2D.Double top, Point2D.Double right, Point2D.Double left, int n) {
		if (n <= 0)
			return;

		Point2D.Double lr = new Point2D.Double((right.x + left.x) / 2.0, right.y);
		Point2D.Double tr = new Point2D.Double((top.x + right.x) / 2.0, (top.y + right.y) / 2.0);
		Point2D.Double tl = new Point2D.Double((top.x + left.x) / 2.0, (top.y + left.y) / 2.0);
		drawTriangle(tl, tr, lr, color);
		sierpinski(top, tr, tl, n - 1);
		sierpinski(tr, right, lr, n - 1);
		sierpinski(tl, lr, left, n - 1);

	}

	public void drawTriangle(Point2D.Double a, Point2D.Double b, Point2D.Double c, Color color) {
		double[] x = { a.x, b.x, c.x };
		double[] y = { a.y, b.y, c.y };
		UltraDraw.setPenColor(color);
		UltraDraw.filledPolygon(x, y);
	}

	public void draw(int n) {
		drawTriangle(top, right, left, Color.YELLOW);
		sierpinski(this.top, this.right, this.left, n-1);
	}
	
	    public void mouseClicked(MouseEvent e) {
	    	if(n >= 8) n = 0;
	    	n++; this.draw(n);
	    	}

	    public void mouseEntered(MouseEvent e) { }
	    
	    public void mouseExited(MouseEvent e) { }

	    public void mousePressed(MouseEvent e) { }
	    
	    public void mouseReleased(MouseEvent e) {
	    	color = Color.BLUE;
	    }
	    
	    public void mouseMoved(MouseEvent e ) { }
	     
	    public void mouseDragged(MouseEvent e) {
	    	color = Color.BLACK;
	    	this.draw(n);
	    }

	public static void main(String[] args) {
		SierpinskiTriangle triangle = new SierpinskiTriangle();
		triangle.draw(5);
	}
}