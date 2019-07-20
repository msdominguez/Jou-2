import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class SettingsDialog extends JDialog implements ItemListener {
	public JPanel contentPanel = new JPanel();
	public JPanel buttonPane = new JPanel();

	public JLabel labelThemes = new JLabel("Color Theme");
	public JLabel labelFonts = new JLabel("Fonts");
	public JButton okButton = new JButton("OK");
	public JButton cancelButton = new JButton("Cancel");

	private MainPanel mainPanel;
	private EntryPanel entryPanel;
	private ViewEntriesPanel viewEntriesPanel;

	private static final long serialVersionUID = 21305503976948004L;
	public String[] themes = { "purple", "pink", "blue", "green", "yellow" };
	public String defaultTheme = "purple";
	public String currentTheme;
	public JComboBox<String> themeChoice = new JComboBox<String>(themes);

	public String[] fonts = { "one", "two" };
	public String defaultFont = "one";
	public String currentFont;
	public JComboBox<String> fontChoice = new JComboBox<String>(fonts);

	public static Color color = new Color(170, 129, 209);
	public static Color color2 = new Color(212, 166, 255);

	public static Font font = new Font("Yu Gothic UI Semilight", Font.PLAIN, 15);
	public static Font font2 = new Font("Yu Gothic UI Bold", Font.PLAIN, 13);

	SettingsDialog(MainPanel mp, EntryPanel ep, ViewEntriesPanel vep) {
		this.mainPanel = mp;
		this.entryPanel = ep;
		this.viewEntriesPanel = vep;

		setIconImage(new ImageIcon(MainScreen.class.getResource("/jou-2-logo-small.png")).getImage());
		setTitle("Settings - Jou-2 ☆");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		contentPanel.setLayout(new GridLayout(10, 0));

		contentPanel.add(labelThemes);
		contentPanel.add(themeChoice);
		contentPanel.add(labelFonts);
		contentPanel.add(fontChoice);

		themeChoice.addItemListener(this);
		fontChoice.addItemListener(this);
		currentTheme = themeChoice.getSelectedItem().toString();
		currentFont = fontChoice.getSelectedItem().toString();

		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentTheme = themeChoice.getSelectedItem().toString();
				mainPanel.setColors(color, color2);
				entryPanel.setColors(color, color2);
				viewEntriesPanel.setColors(color, color2);

				currentFont = fontChoice.getSelectedItem().toString();
				mainPanel.setFonts(font, font2);
				entryPanel.setFonts(font, font2);
				viewEntriesPanel.setFonts(font, font2);

				dispose();
			}
		});

		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				themeChoice.setSelectedItem(currentTheme);
				fontChoice.setSelectedItem(currentFont);
				dispose();
			}
		});

		setColors(color, color2);
		setFonts(font, font2);

		pack();
		setLocationRelativeTo(null);
	}

	public void setFonts(Font f1, Font f2) {
		this.font = f1;
		this.font2 = f2;

		labelThemes.setFont(font2);
		labelFonts.setFont(font2);

		themeChoice.setFont(font);
		fontChoice.setFont(font);

		okButton.setFont(font2);
		cancelButton.setFont(font2);
	}

	public static void createBtnOKCancel(JButton btn, String imgname) {
		btn.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn.setHorizontalAlignment(SwingConstants.LEFT);
		btn.setAlignmentY(Component.TOP_ALIGNMENT);
		ImageIcon imgNewEntry = new ImageIcon(new ImageIcon(MainScreen.class.getResource(imgname)).getImage()
				.getScaledInstance(15, 15, Image.SCALE_DEFAULT));
		btn.setIcon(imgNewEntry);

		btn.setFont(MainPanel.font2);
		btn.setForeground(Color.black);
		btn.setBackground(color2);
		btn.setBorder(new RoundedBorder(Color.white, 3, 20));
	}

	public void setColors(Color c1, Color c2) {
		this.color = c1;
		this.color2 = c2;

		createBtnOKCancel(okButton, "/ok.png");
		createBtnOKCancel(cancelButton, "/cancel.png");

		themeChoice.setBackground(color2);
		fontChoice.setBackground(color2);

		contentPanel.setBackground(color);
		buttonPane.setBackground(color);
	}

	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == themeChoice) {
			if (themeChoice.getSelectedItem().equals("pink")) {
				color = new Color(255, 124, 124);
				color2 = new Color(255, 208, 208);
			} else if (themeChoice.getSelectedItem().equals("purple")) {
				color = new Color(170, 129, 209);
				color2 = new Color(212, 166, 255);
			} else if (themeChoice.getSelectedItem().equals("blue")) {
				color = new Color(114, 202, 255);
				color2 = new Color(178, 226, 255);
			} else if (themeChoice.getSelectedItem().equals("green")) {
				color = new Color(123, 255, 103);
				color2 = new Color(187, 253, 176);
			} else if (themeChoice.getSelectedItem().equals("yellow")) {
				color = new Color(255, 212, 105);
				color2 = new Color(254, 255, 134);
			}
			mainPanel.setColors(color, color2); // preview
			entryPanel.setColors(color, color2);
			viewEntriesPanel.setColors(color, color2);
			this.setColors(color, color2);
		} else if (e.getSource() == fontChoice) {
			if (fontChoice.getSelectedItem().equals("one")) {
				font = new Font("Yu Gothic UI Semilight", Font.PLAIN, 15);
				font2 = new Font("Yu Gothic UI Bold", Font.PLAIN, 13);
			} else if (fontChoice.getSelectedItem().equals("two")) {
				font = new Font("Tw Cen MT", Font.PLAIN, 15);
				font2 = new Font("Myanmar Text Bold", Font.PLAIN, 13);
			}
			mainPanel.setFonts(font, font2);
			entryPanel.setFonts(font, font2);
			viewEntriesPanel.setFonts(font, font2);
			this.setFonts(font, font2);
		}
	}
}
