import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MainPanel extends JPanel implements View {

	private static final long serialVersionUID = 21305503976948004L;

	public static Color color;
	public static Color color2;
	public static Font font;
	public static Font font2;

	public static JFrame frame;

	public static SettingsDialog settings;
	private JPanel mainPanel = new JPanel();
	public EntryPanel entryPanel;
	public ViewEntriesPanel vePanel;
	public CardLayout cardLayout = new CardLayout();
	public JPanel cardPanel = new JPanel(cardLayout);
	private Controller controller;

	public JPanel contentPanel = new JPanel(new BorderLayout());
	public Box vertBox = Box.createVerticalBox();
	public JPanel btnPanel = new JPanel();
	public ImageIcon imageIcon = new ImageIcon(new ImageIcon(MainScreen.class.getResource("/jou-2-logo-big.png"))
			.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
	public JLabel logo = new JLabel();
	public JButton btnNewEntry = new JButton("New Entry");
	public JButton btnViewEntries = new JButton("View Entries");
	public JButton btnSettings = new JButton("Settings");
	public JButton btnHelp = new JButton("Help");
	public JButton btnAbout = new JButton("About");
	public JButton btnQuit = new JButton("Quit");

	public JMenuBar menuBar = new JMenuBar();
	public JMenu menuFile = new JMenu("File");
	public JMenu menuHelp = new JMenu("Help");

	public JMenuItem itemNewEntry = new JMenuItem("New Entry");
	public JMenuItem itemViewEntries = new JMenuItem("View Entries");
	public JMenuItem itemSettings = new JMenuItem("Settings");
	public JMenuItem itemQuit = new JMenuItem("Quit");
	public JMenuItem itemHelp = new JMenuItem("Help");
	public JMenuItem itemAbout = new JMenuItem("About");

	public MainPanel() {
		cardPanel.add(this, "Main");
		entryPanel = new EntryPanel(cardPanel, cardLayout);
		vePanel = new ViewEntriesPanel(cardPanel, cardLayout);
		cardPanel.add(entryPanel, "Entry");
		cardPanel.add(vePanel, "View");

		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(cardPanel, BorderLayout.CENTER);

		settings = new SettingsDialog(this, entryPanel, vePanel);
		entryPanel.settings = settings;
		vePanel.settings = settings;
		color = settings.color;
		color2 = settings.color2;
		font = settings.font;
		font2 = settings.font2;

		setLayout(new BorderLayout());
		setSize(400, 500);

		add(contentPanel, BorderLayout.CENTER);

		logo.setAlignmentX(Component.CENTER_ALIGNMENT);
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setIcon(imageIcon);

		// add things to panels
		btnPanel = new JPanel(new GridLayout(-100, 1, 0, 10));
		btnPanel.setBorder(new EmptyBorder(0, 100, 30, 100)); // top left bottom right
		btnPanel.add(btnNewEntry);
		btnPanel.add(btnViewEntries);
		btnPanel.add(btnSettings);
		btnPanel.add(btnHelp);
		btnPanel.add(btnAbout);
		btnPanel.add(btnQuit);

		vertBox.add(logo);
		vertBox.add(btnPanel);
		contentPanel.add(vertBox, BorderLayout.CENTER);

		contentPanel.add(menuBar, BorderLayout.NORTH);

		// set colors
		setColors(color, color2);
		setFonts(font, font2);

		frame = new JFrame();
		frame.setTitle("Jou-2  ☁");
		frame.setIconImage(new ImageIcon(MainScreen.class.getResource("/jou-2-logo-small.png")).getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(mainPanel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
		setVisible(true);
	}

	public void setController(Controller c) {
		this.controller = c;
	}

	public void setFonts(Font f1, Font f2) {
		font = f1;
		font2 = f2;

		// add icons and align
		createBtn(btnNewEntry, "/newentry.png");
		createBtn(btnViewEntries, "/viewentries.png");
		createBtn(btnSettings, "/settings.png");
		createBtn(btnHelp, "/help.png");
		createBtn(btnAbout, "/about.png");
		createBtn(btnQuit, "/quit.png");

		// menubar
		menuFile.setFont(font2);
		menuHelp.setFont(font2);
		itemNewEntry.setFont(font2);
		itemViewEntries.setFont(font2);
		itemSettings.setFont(font2);
		itemQuit.setFont(font2);
		itemHelp.setFont(font2);
		itemAbout.setFont(font2);
	}

	public void setColors(Color c1, Color c2) {
		color = c1;
		color2 = c2;

		this.setBackground(color);
		contentPanel.setBackground(color);

		// add icons and align
		createBtn(btnNewEntry, "/newentry.png");
		createBtn(btnViewEntries, "/viewentries.png");
		createBtn(btnSettings, "/settings.png");
		createBtn(btnHelp, "/help.png");
		createBtn(btnAbout, "/about.png");
		createBtn(btnQuit, "/quit.png");

		addToMenu(menuFile, itemNewEntry);
		addToMenu(menuFile, itemViewEntries);
		addToMenu(menuFile, itemSettings);
		addToMenu(menuFile, itemQuit);
		addToMenu(menuHelp, itemHelp);
		addToMenu(menuHelp, itemAbout);

		createTopBarMenu(menuBar, menuFile, menuHelp);

		btnPanel.setBackground(color);
		vertBox.setBackground(color);
	}

	public static void createBtn(JButton btn, String imgname) {
		btn.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn.setHorizontalAlignment(SwingConstants.LEFT);
		btn.setAlignmentY(Component.TOP_ALIGNMENT);
		ImageIcon imgNewEntry = new ImageIcon(new ImageIcon(MainScreen.class.getResource(imgname)).getImage()
				.getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		btn.setIcon(imgNewEntry);

		btn.setFont(font2);
		btn.setForeground(Color.black);
		btn.setBackground(color2);
		btn.setBorder(new RoundedBorder(Color.white, 3, 20));
	}

	// use settings.color to use in EntryPanel
	public static void addToMenu(JMenu menu, JMenuItem item) {
		menu.setBackground(settings.color2);
		menu.setBorder(null);
		item.setBackground(settings.color2);
		item.setBorder(null);
		menu.add(item);
	}

	public static void createTopBarMenu(JMenuBar menuBar, JMenu menuFile, JMenu menuHelp) {
		menuBar.setBackground(settings.color2);
		menuBar.setBorder(null);

		menuBar.add(menuFile);
		menuBar.add(menuHelp);
	}
}
