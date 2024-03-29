import java.awt.Container;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneLayout;
import javax.swing.WindowConstants;

public class CustomScrollbarDemo {
	public static void main(String[] args) {
		JTextArea cmp = new JTextArea();
		String str = "a";
		for (int i = 0; i < 20; i++) {
			cmp.append(str + str + "\n");
		}
		JScrollPane scrollPane = new JScrollPane(cmp);
		scrollPane.setComponentZOrder(scrollPane.getVerticalScrollBar(), 0);
		scrollPane.setComponentZOrder(scrollPane.getViewport(), 1);
		scrollPane.getVerticalScrollBar().setOpaque(false);

		scrollPane.setLayout(new ScrollPaneLayout() {
			@Override
			public void layoutContainer(Container parent) {
				JScrollPane scrollPane = (JScrollPane) parent;

				Rectangle availR = scrollPane.getBounds();
				availR.x = availR.y = 0;

				Insets parentInsets = parent.getInsets();
				availR.x = parentInsets.left;
				availR.y = parentInsets.top;
				availR.width -= parentInsets.left + parentInsets.right;
				availR.height -= parentInsets.top + parentInsets.bottom;

				Rectangle vsbR = new Rectangle();
				vsbR.width = 12;
				vsbR.height = availR.height;
				vsbR.x = availR.x + availR.width - vsbR.width;
				vsbR.y = availR.y;

				if (viewport != null) {
					viewport.setBounds(availR);
				}
				if (vsb != null) {
					vsb.setVisible(true);
					vsb.setBounds(vsbR);
				}
			}
		});
		scrollPane.getVerticalScrollBar().setUI(new ScrollBarUI());

		JFrame f = new JFrame();
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.getContentPane().add(scrollPane);
		f.setSize(320, 240);
		f.setVisible(true);
	}
}
