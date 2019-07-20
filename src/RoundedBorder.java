import javax.swing.border.*;
import javax.swing.*;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.awt.*;

public class RoundedBorder extends AbstractBorder {

    private static final long serialVersionUID = -6244878594068693694L;

    private Color color;

    private int thickness = 4;

    private int arcSize = 8;

    private BasicStroke stroke;

    private RenderingHints hints;

    /**
     * Constructor for round border
     *
     * @param color
     *            The color
     * @param thickness
     *            The thickness
     * @param arcSize
     *            the arc size
     */
    public RoundedBorder(Color color, int thickness, int arcSize) {
        this.thickness = thickness;
        this.arcSize = arcSize;
        this.color = color;
        this.stroke = new BasicStroke(thickness);
        this.hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    /**
     * Set the current color of the border
     *
     * @param color
     *            The color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Get the current color of the border
     *
     * @return
     */
    public Color getColor() {
        return color;
    }

    public void setArcSize(int arcSize) {
        this.arcSize = arcSize;
    }

    public int getArcSize() {
        return arcSize;
    }

    /* (non-Javadoc)
     * @see javax.swing.border.AbstractBorder#getBorderInsets(java.awt.Component, java.awt.Insets)
     */
    @Override
    public Insets getBorderInsets(Component c, Insets i) {
        i.set(thickness + 10, thickness + 10, thickness + 10, thickness + 10);
        return i;
    }

    /* (non-Javadoc)
     * @see javax.swing.border.AbstractBorder#paintBorder(java.awt.Component, java.awt.Graphics, int, int, int, int)
     */
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g;

        Shape roundRect = new RoundRectangle2D.Double(x + thickness / 2,
                                                      y + thickness / 2,
                                                      width - thickness,
                                                      height - thickness,
                                                      arcSize,
                                                      arcSize);
        Area area = new Area(roundRect);

        // Paint the BG color of the parent, everywhere outside the clip of the text bubble.
        Component parent = c.getParent();
        if (parent != null) {
            Color bg = parent.getBackground();
            Rectangle rect = new Rectangle(x, y, width, height);
            Area borderRegion = new Area(rect);
            borderRegion.subtract(area);
            g2.setClip(borderRegion);
            g2.setColor(bg);
            g2.fillRect(0, 0, width, height);
            g2.setClip(null);
        }

        g2.setColor(color);
        g2.setRenderingHints(hints);
        g2.setStroke(stroke);

        g2.draw(area);
    }
}
