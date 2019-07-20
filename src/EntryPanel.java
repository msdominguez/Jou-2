import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

public class EntryPanel extends JPanel implements View {

	private static final long serialVersionUID = 21305503976948004L;

	public JPanel cardPanel;
	public CardLayout cardLayout;

	public SettingsDialog settings;
	public static Color color;
	public static Color color2;
	public static Font font;
	public static Font font2;

	private JPanel mainPanel = new JPanel(new GridBagLayout());
	private JPanel btnSaveCancelPanel = new JPanel();
	private Controller controller;

	private JPanel dateTimePanel = new JPanel(new GridLayout(0, 2, 10, 0));
	private JPanel timePanel = new JPanel(new GridLayout(0, 1));
	private JPanel datePanel = new JPanel(new GridLayout(0, 1));
	private JPanel titlePanel = new JPanel(new GridLayout(0, 1));
	private JPanel songPanel = new JPanel(new GridLayout(0, 1));
	private JPanel entryPanel = new JPanel(new GridLayout(0, 1));

	private JLabel timeLabel = new JLabel("Time");
	private JLabel dateLabel = new JLabel("Date");
	private JLabel titleLabel = new JLabel("Title");
	private JLabel songLabel = new JLabel("Song");

	private static final DateFormat tf = new SimpleDateFormat("hh:mm a");
	private static final DateFormat df = new SimpleDateFormat("EE, MMM dd, yyyy");

	public JFormattedTextField timeField = new JFormattedTextField(tf);
	public JFormattedTextField dateField = new JFormattedTextField(df);
	public JFormattedTextField titleField = new JFormattedTextField("My Entry");
	public JFormattedTextField songField = new JFormattedTextField("Powerlines - Tame Impala");

	public JTextArea entryArea = new JTextArea("My Entry ... ☁ ");

	public JButton btnClear = new JButton("Clear");
	public JButton btnSave = new JButton("Save");
	public JButton btnCancel = new JButton("Cancel");

	private Box vertBox = Box.createVerticalBox();

	public JMenuBar menuBar = new JMenuBar();
	public JMenu menuFile = new JMenu("File");
	public JMenu menuHelp = new JMenu("Help");

	public JMenuItem itemNewEntry = new JMenuItem("New Entry");
	public JMenuItem itemViewEntries = new JMenuItem("View Entries");
	public JMenuItem itemSettings = new JMenuItem("Settings");
	public JMenuItem itemQuit = new JMenuItem("Quit");
	public JMenuItem itemHelp = new JMenuItem("Help");
	public JMenuItem itemAbout = new JMenuItem("About");

	public EntryPanel(JPanel cardP, CardLayout cardL) {
		this.cardPanel = cardP;
		this.cardLayout = cardL;

		cardPanel.setFocusable(true);
		cardPanel.requestFocus();

		this.setLayout(new BorderLayout());

		// formatted text fields
		timeField.setColumns(5);
		try {
			MaskFormatter dateMask = new MaskFormatter("##:## ??");
			dateMask.install(timeField);
			Date now = new Date();
			timeField.setText(tf.format(now));
		} catch (Exception e) {
			Date now = new Date();
			timeField.setText(tf.format(now));
		}

		dateField.setColumns(10);
		try {
			MaskFormatter dateMask = new MaskFormatter("???, ??? ##, ####");
			dateMask.install(dateField);
			Date now = new Date();
			dateField.setText(df.format(now));
		} catch (Exception e) {
			Date now = new Date();
			dateField.setText(df.format(now));
		}

		// add things to panels
		mainPanel.setOpaque(false);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;

		timePanel.add(timeLabel);
		timePanel.add(timeField);

		datePanel.add(dateLabel);
		datePanel.add(dateField);

		dateTimePanel.add(timePanel);
		dateTimePanel.add(datePanel);

		titlePanel.add(titleLabel);
		titlePanel.add(titleField);

		songPanel.add(songLabel);
		songPanel.add(songField);

		entryPanel.add(entryArea);

		// add all to gridbag
		c.gridx = 0;
		c.gridy = 0;
		mainPanel.add(dateTimePanel, c);

		c.gridx = 0;
		c.gridy = 1;
		mainPanel.add(titlePanel, c);

		c.gridx = 0;
		c.gridy = 2;
		mainPanel.add(songPanel, c);

		c.gridx = 0;
		c.gridy = 3;
		c.weighty = 1.0; // request any extra vertical space
		c.ipady = 200;
		JScrollPane scrollPane = new JScrollPane(entryArea);
		scrollPane.setComponentZOrder(scrollPane.getVerticalScrollBar(), 0);
		scrollPane.setComponentZOrder(scrollPane.getViewport(), 1);
		scrollPane.getVerticalScrollBar().setOpaque(false);
		scrollPane.getVerticalScrollBar().setUI(new ScrollBarUI());
		entryArea.setLineWrap(true);
		entryArea.setWrapStyleWord(true);

		entryPanel.add(scrollPane);
		mainPanel.add(entryPanel, c);

		vertBox.add(mainPanel);
		this.add(vertBox, BorderLayout.CENTER);

		// get colors from settings
		color = settings.color;
		color2 = settings.color2;
		font = settings.font;
		font2 = settings.font2;

		// ok cancel button panel
		this.add(btnSaveCancelPanel, BorderLayout.SOUTH);
		btnSaveCancelPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		btnSaveCancelPanel.add(btnClear);
		btnSaveCancelPanel.add(btnSave);
		btnSaveCancelPanel.add(btnCancel);

		this.add(menuBar, BorderLayout.NORTH);

		setColors(color, color2);
		setFonts(font, font2);

		this.setVisible(true);
		new EntryController(this);
	}

	public void setFonts(Font f1, Font f2) {
		font = f1;
		font2 = f2;

		createbtnSaveCancel(btnClear, "/clear.png");
		createbtnSaveCancel(btnSave, "/ok.png");
		createbtnSaveCancel(btnCancel, "/cancel.png");

		// create Jtextfields here
		createJTextBox(timeField);
		createJTextBox(dateField);
		createJTextBox(songField);
		createJTextBox(titleField);

		entryArea.setFont(font);
		createAreaPanel(entryPanel);

		// style labels
		timeLabel.setFont(font2);
		dateLabel.setFont(font2);
		titleLabel.setFont(font2);
		songLabel.setFont(font2);

		menuFile.setFont(font2);
		menuHelp.setFont(font2);
		itemSettings.setFont(font2);
		itemQuit.setFont(font2);
		itemHelp.setFont(font2);
		itemAbout.setFont(font2);

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
		mainPanel.setBackground(color);
		dateTimePanel.setBackground(color);
		timePanel.setBackground(color);
		datePanel.setBackground(color);
		titlePanel.setBackground(color);
		songPanel.setBackground(color);

		vertBox.setBackground(color);

		btnSaveCancelPanel.setBackground(color);

		createbtnSaveCancel(btnClear, "/clear.png");
		createbtnSaveCancel(btnSave, "/ok.png");
		createbtnSaveCancel(btnCancel, "/cancel.png");

		// create Jtextfields here
		createJTextBox(timeField);
		createJTextBox(dateField);
		createJTextBox(songField);
		createJTextBox(titleField);

		createAreaPanel(entryPanel);

		MainPanel.addToMenu(menuFile, itemNewEntry);
		MainPanel.addToMenu(menuFile, itemViewEntries);
		MainPanel.addToMenu(menuFile, itemSettings);
		MainPanel.addToMenu(menuFile, itemQuit);
		MainPanel.addToMenu(menuHelp, itemHelp);
		MainPanel.addToMenu(menuHelp, itemAbout);

		MainPanel.createTopBarMenu(menuBar, menuFile, menuHelp);
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

	public void createJTextBox(JTextField jtfield) {
		jtfield.setFont(font);
		jtfield.setForeground(Color.black);
		jtfield.setBackground(color2);
		jtfield.setBorder(new RoundedBorder(Color.white, 2, 20));
	}

	public void createAreaPanel(JPanel areaPanel) {
		areaPanel.setFont(font);
		areaPanel.setForeground(Color.black);
		areaPanel.setBackground(color2);
		entryArea.setBackground(color2);
		areaPanel.setBorder(new RoundedBorder(Color.white, 2, 20));
	}

	/* reset fields methods */
	public void resetFields() {
		// reset date/time to now
		Date now = new Date();
		timeField.setText(tf.format(now));
		dateField.setText(df.format(now));

		titleField.setText("My Entry");
		songField.setText("Powerlines - Tame Impala");
		entryArea.setText("My Entry ... ☁ ");
	}

	public void resetFieldsEmpty() {
		// i dont want to mess with date/time here
		// this is called on clear btn / save to make a new entry
		// so i want to pick up where i left off there
		titleField.setText("");
		songField.setText("");
		entryArea.setText("");
	}

	public void setController(Controller c) {
		this.controller = c;
	}
}
