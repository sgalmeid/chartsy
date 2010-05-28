package org.chartsy.main.intro.content;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import org.chartsy.main.utils.DesktopUtil;
import org.openide.awt.StatusDisplayer;
import org.openide.util.ImageUtilities;

/**
 *
 * @author viorel.gheba
 */
public class BulletLink extends JButton implements Constants, MouseListener, ActionListener, FocusListener {

    private ImageIcon BULLET_ICON;
    private String label;
    private String url;
    private boolean bulleted;

    public BulletLink(String label, String url) { this(label, url, true); }

    public BulletLink(String label, String url, boolean bulleted) {
        super("<html>" + label + "</html>");
        BULLET_ICON = ImageUtilities.loadImageIcon(IMAGE_BULLET, true);
        this.label = label;
        this.url = url;
        this.bulleted = bulleted;

        setBorder(new EmptyBorder(1, 1, 1, 1));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setFont(GET_STARTED_FONT);
        setForeground(COLOR_BULLET);
        setHorizontalAlignment(2);
        addMouseListener(this);
        setFocusable(true);
        if (bulleted) setIcon(BULLET_ICON);
        setMargin(new Insets(0, 0, 0, 0));
        setBorderPainted(false);
        setFocusPainted(false);
        setRolloverEnabled(true);
        setContentAreaFilled(false);
        addActionListener(this);
        addFocusListener(this);
    }

    public void setLinkFont(Font font) { setFont(font); }
    public void setLinkColor(Color color) { setForeground(color); }

    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {
        setText("<html><u>" + label + "</u></html>");
        StatusDisplayer.getDefault().setStatusText(url);
    }
    public void mouseExited(MouseEvent e) {
        setText("<html>" + label + "</html>");
        StatusDisplayer.getDefault().setStatusText("");
    }
    public void actionPerformed(ActionEvent e) {
        try { DesktopUtil.browse(url); }
        catch (Exception ex) {}
    }
    public void focusGained(FocusEvent e) {
        if (bulleted) {
            Rectangle rectangle = getBounds();
            rectangle.grow(0, 12);
            scrollRectToVisible(rectangle);
        }
    }
    public void focusLost(FocusEvent e) {}

}
