package mco364;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

class DrawPanel extends JPanel {

    private class MouseEventsListener extends MouseAdapter {

        private void addPoint(Point p) {
            pointList.add(p);
            repaint();
            
        }

        @Override
        public void mousePressed(MouseEvent me) {
            addPoint(me.getPoint());
            
            System.out.println(me.getPoint());
            throw new RuntimeException();

        }

        @Override
        public void mouseDragged(MouseEvent me) {
            addPoint(me.getPoint());
        }
    }

    DrawPanel() {
        MouseEventsListener mel = new MouseEventsListener();
        addMouseListener(mel);
        addMouseListener(mel);
        addMouseMotionListener(mel);
        setFocusable(true); // panels are not mouse foucsable by default
    }
    int i = 0;
    Random r = new Random();
    ArrayList<Point> pointList = new ArrayList<>();

    @Override
    public void paint(java.awt.Graphics g) { // paint method gets called auotmatically whenever the screen needs to be repainted
        // on maximize, resize, when window becomes visible again
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setStroke(new BasicStroke(10));
        g2.setColor(Color.BLACK);

//        g.drawLine(
//                r.nextInt(this.getWidth()),
//                r.nextInt(this.getHeight()),
//                r.nextInt(this.getWidth()),
//                r.nextInt(this.getHeight()));
        for (int i = 0; i < pointList.size() - 1; i++) {
            Point p1 = pointList.get(i),
                    p2 = pointList.get(i + 1);
            g2.drawLine(p1.x, p1.y, p2.x, p2.y);
        }

        System.out.println("repaint" + i++);
    }

}

class DrawApp extends JFrame {

    DrawApp() {
        add(new DrawPanel());
        setSize(400, 600);
        setDefaultCloseOperation(3);
        setVisible(true);
    }

}

public class EntryPoint {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        new DrawApp();

    }
}
