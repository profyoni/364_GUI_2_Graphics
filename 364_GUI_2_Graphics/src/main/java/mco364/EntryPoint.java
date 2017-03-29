package mco364;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


// add menuitmne blue, greenn, black
// add File | Save 
// new lines draw using previous points color
// v 2. later, Open ,  Exit method using serizlization
// 
class ColorPoint extends Point {

    ColorPoint(Point p, Color c) {
        super(p);
        color = c;
    }
    Color color;
}

class DrawPanel extends JPanel {

    private Color currentColor = Color.BLUE;

    void setColor(Color color) {
        this.currentColor = color;
    }

    private class MouseEventsListener extends MouseAdapter {

        private void addPoint(Point p) {
            if (!pointList.isEmpty()) {
                ColorPoint lastPoint = pointList.get(pointList.size() - 1);
                Graphics2D g2 = (Graphics2D) DrawPanel.this.getGraphics(); // same as getGraphics

                setGraphics(g2);
                
                g2.setColor(currentColor);
                g2.drawLine(lastPoint.x, lastPoint.y, p.x, p.y);
            }
            ColorPoint cp = new ColorPoint(p, currentColor);

            pointList.add(cp);
            //repaint(); // don't force an entire repaint for every draw point!

        }

        @Override
        public void mousePressed(MouseEvent me) {
            addPoint(me.getPoint());

            System.out.println(me.getPoint());
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
    ArrayList<ColorPoint> pointList = new ArrayList<>();

    private void setGraphics(Graphics2D g2) {
        g2.setStroke(new BasicStroke(10));
    }

    @Override
    public void paint(java.awt.Graphics g) { // paint method gets called auotmatically whenever the screen needs to be repainted
        // on maximize, resize, when window becomes visible again
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        setGraphics(g2);

        for (int i = 0; i < pointList.size() - 1; i++) {
            ColorPoint p1 = pointList.get(i),
                    p2 = pointList.get(i + 1);

            g2.setColor(p1.color);
            g2.drawLine(p1.x, p1.y, p2.x, p2.y);
        }

        System.out.println("repaint" + i++);

    }

}

class DrawApp extends JFrame {

    DrawApp() {
        DrawPanel drawPanel = new DrawPanel();

        JMenuBar menuBar = new JMenuBar();
        JMenu optionsMenu = new JMenu("Options");
        menuBar.add(optionsMenu);

        JMenuItem optionsMenuItem = new JMenuItem("Red");
        optionsMenu.add(optionsMenuItem);
        optionsMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                drawPanel.setColor(Color.RED);
            }
        });

        add(menuBar, BorderLayout.NORTH);
        add(drawPanel);

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
        System.out.println(System.getProperty("os.name"));
        new DrawApp();

    }
}
