import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ViewEntriesPanel extends JPanel implements View {

	private static final long serialVersionUID = 21305503976948004L;

	public JPanel cardPanel;
	public CardLayout cardLayout;

	public SettingsDialog settings;
	public static Color color;
	public static Color color2;
	public static Font font;
	public static Font font2;

	private JPanel mainPanel = new JPanel(new BorderLayout());

	private Controller controller;

	private JPanel topOptions = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel topBack = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel topChooseEntry = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	private JPanel entryPanel = new JPanel(new GridLayout(0, 1));
	private JPanel bottomOptions = new JPanel(new FlowLayout(FlowLayout.CENTER));

	public JTextArea entryArea = new JTextArea("Load in an entry ☁ ");

	public JLabel entryNumber = new JLabel("0 / 0");
	public JButton btnBack = new JButton();
	public JButton btnChooseEntry = new JButton("Choose Entry");
	public JButton btnForward = new JButton();
	public JButton btnBackward = new JButton();
	public JButton btnEditSave = new JButton("Edit");
	public JButton btnEditCancel = new JButton("Cancel");

	private Box vertBox = Box.createVerticalBox();

	public JMenuBar menuBar = new JMenuBar();
	public JMenu menuFile = new JMenu("File");
	public JMenu menuEdit = new JMenu("Edit");
	public JMenu menuHelp = new JMenu("Help");

	public JMenuItem itemNewEntry = new JMenuItem("New Entry");
	public JMenuItem itemViewEntries = new JMenuItem("View Entries");
	public JMenuItem itemSettings = new JMenuItem("Settings");
	public JMenuItem itemEditEntry = new JMenuItem("Edit Entry");
	public JMenuItem itemQuit = new JMenuItem("Quit");
	public JMenuItem itemHelp = new JMenuItem("Help");
	public JMenuItem itemAbout = new JMenuItem("About");

	public ViewEntriesPanel(JPanel cardP, CardLayout cardL) {
		this.cardPanel = cardP;
		this.cardLayout = cardL;

		cardPanel.setFocusable(true);
		cardPanel.requestFocus();

		this.setLayout(new BorderLayout());
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5)); // top left bottom right

		// add things to panels
		mainPanel.setOpaque(false);

		// topBack.add(btnBack);
		// topChooseEntry.add(btnChooseEntry);

		// topOptions.add(topBack);
		// topOptions.add(topChooseEntry);
		topOptions.add(btnBack);
		topOptions.add(btnChooseEntry);
		JPanel entryNumberPanel = new JPanel(new BorderLayout());
		entryNumberPanel.setOpaque(false);
		entryNumberPanel.setBorder(new EmptyBorder(0, 45, 0, 0)); // add left-margin
		entryNumberPanel.add(entryNumber, BorderLayout.EAST);

		topOptions.add(entryNumberPanel);

		entryPanel.add(entryArea);

		bottomOptions.add(btnBackward);
		bottomOptions.add(btnForward);
		bottomOptions.add(btnEditSave);
		bottomOptions.add(btnEditCancel);
		btnEditCancel.setVisible(false);

		mainPanel.add(topOptions, BorderLayout.NORTH);
		mainPanel.add(entryPanel, BorderLayout.CENTER);
		mainPanel.add(bottomOptions, BorderLayout.SOUTH);

		JScrollPane scrollPane = new JScrollPane(entryArea);
		scrollPane.setComponentZOrder(scrollPane.getVerticalScrollBar(), 0);
		scrollPane.setComponentZOrder(scrollPane.getViewport(), 1);
		scrollPane.getVerticalScrollBar().setOpaque(false);
		scrollPane.getVerticalScrollBar().setUI(new ScrollBarUI());
		entryArea.setLineWrap(true);
		entryArea.setWrapStyleWord(true);
		entryArea.setEditable(false);

		entryPanel.add(scrollPane);

		vertBox.add(mainPanel);
		this.add(vertBox, BorderLayout.CENTER);

		// get colors from settings
		color = settings.color;
		color2 = settings.color2;
		font = settings.font;
		font2 = settings.font2;

		this.add(menuBar, BorderLayout.NORTH);

		setColors(color, color2);
		setFonts(font, font2);

		this.setVisible(true);
		new ViewEntriesController(this);
	}

	public void setFonts(Font f1, Font f2) {
		font = f1;
		font2 = f2;

		createbtnSaveCancel(btnBack, "/back.png");
		createbtnSaveCancel(btnChooseEntry, "/choosefile.png");
		createbtnSaveCancel(btnForward, "/fwd.png");
		createbtnSaveCancel(btnBackward, "/bkwd.png");
		createbtnSaveCancel(btnEditSave, "/edit.png");
		createbtnSaveCancel(btnEditCancel, "/cancel.png");

		// entryNumber.setFont(font2);
		Font entryNumberFont = new Font("Yu Gothic UI Bold", Font.PLAIN, 20);
		entryNumber.setFont(entryNumberFont);
		entryArea.setFont(font);
		createAreaPanel(entryPanel);

		// menubar
		menuFile.setFont(font2);
		menuEdit.setFont(font2);
		menuHelp.setFont(font2);
		itemNewEntry.setFont(font2);
		itemViewEntries.setFont(font2);
		itemEditEntry.setFont(font2);
		itemSettings.setFont(font2);
		itemQuit.setFont(font2);
		itemHelp.setFont(font2);
		itemAbout.setFont(font2);
	}

	public void setColors(Color c1, Color c2) {
		color = c1;
		color2 = c2;

		this.setBackground(color);
		mainPanel.setBackground(color);
		topBack.setBackground(color);
		topChooseEntry.setBackground(color);
		topOptions.setBackground(color);
		bottomOptions.setBackground(color);

		vertBox.setBackground(color);

		createbtnSaveCancel(btnBack, "/back.png");
		createbtnSaveCancel(btnChooseEntry, "/choosefile.png");
		createbtnSaveCancel(btnForward, "/fwd.png");
		createbtnSaveCancel(btnBackward, "/bkwd.png");
		createbtnSaveCancel(btnEditSave, "/edit.png");
		createbtnSaveCancel(btnEditCancel, "/cancel.png");

		createAreaPanel(entryPanel);

		MainPanel.addToMenu(menuFile, itemNewEntry);
		MainPanel.addToMenu(menuFile, itemViewEntries);
		MainPanel.addToMenu(menuEdit, itemEditEntry);
		MainPanel.addToMenu(menuFile, itemSettings);
		MainPanel.addToMenu(menuFile, itemQuit);
		MainPanel.addToMenu(menuHelp, itemHelp);
		MainPanel.addToMenu(menuHelp, itemAbout);

		// MainPanel.createTopBarMenu(menuBar, menuFile, menuHelp);
		menuBar.setBackground(settings.color2);
		menuBar.setBorder(null);

		menuBar.add(menuFile);
		menuBar.add(menuEdit);
		menuBar.add(menuHelp);
	}

	public static void createbtnSaveCancel(JButton btn, String imgname) {
		btn.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn.setHorizontalAlignment(SwingConstants.LEFT);
		btn.setAlignmentY(Component.TOP_ALIGNMENT);
		ImageIcon imgNewEntry = new ImageIcon(new ImageIcon(MainScreen.class.getResource(imgname)).getImage()
				.getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		btn.setIcon(imgNewEntry);

		btn.setFont(font2);
		btn.setForeground(Color.black);
		btn.setBackground(color2);
		btn.setBorder(new RoundedBorder(Color.white, 3, 20));
	}

	public void createAreaPanel(JPanel areaPanel) {
		areaPanel.setFont(font);
		areaPanel.setForeground(Color.black);
		areaPanel.setBackground(color2);
		entryArea.setBackground(color2);
		areaPanel.setBorder(new RoundedBorder(Color.white, 2, 20));
	}

	public void setController(Controller c) {
		this.controller = c;
	}

}
