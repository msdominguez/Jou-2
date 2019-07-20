import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class MainScreen extends JFrame {
	private static final long serialVersionUID = 21305503976948004L;

	public static Font font = new Font("Yu Gothic UI Semilight", Font.PLAIN, 15);
	public static Color color = new Color(170, 129, 209);
	public static Color color2 = new Color(212, 166, 255);

	public JPanel contentPanel = new JPanel(new BorderLayout());
	public ImageIcon imageIcon = new ImageIcon(new ImageIcon(MainScreen.class.getResource("/jou-2-logo-big-2.png"))
			.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
	public JLabel logo = new JLabel();
	public JButton btnNewEntry = new JButton("New Entry");
	public JButton btnHelp = new JButton("Help");
	public JButton btnAbout = new JButton("About");
	public JButton btnQuit = new JButton("Quit");

	public MainScreen() {
		setTitle("Jou-2 ☆");
		setLayout(new BorderLayout());
		setSize(400, 500);
		// setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(new ImageIcon(MainScreen.class.getResource("/jou-2-logo-small.png")).getImage());

		setBackground(color);
		contentPanel.setBackground(color);
		add(contentPanel, BorderLayout.CENTER);

		createTopBarMenu();

		logo.setAlignmentX(Component.CENTER_ALIGNMENT);
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setIcon(imageIcon);

		// style buttons

		// add icons and align
		btnNewEntry.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnNewEntry.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewEntry.setAlignmentY(Component.TOP_ALIGNMENT);
		ImageIcon imgNewEntry = new ImageIcon(new ImageIcon(MainScreen.class.getResource("/newentry.png")).getImage()
				.getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		btnNewEntry.setIcon(imgNewEntry);

		btnHelp.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnHelp.setHorizontalAlignment(SwingConstants.LEFT);
		btnHelp.setAlignmentY(Component.TOP_ALIGNMENT);
		ImageIcon imgHelp = new ImageIcon(new ImageIcon(MainScreen.class.getResource("/help.png")).getImage()
				.getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		btnHelp.setIcon(imgHelp);

		btnAbout.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnAbout.setHorizontalAlignment(SwingConstants.LEFT);
		btnAbout.setAlignmentY(Component.TOP_ALIGNMENT);
		ImageIcon imgAbout = new ImageIcon(new ImageIcon(MainScreen.class.getResource("/about.png")).getImage()
				.getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		btnAbout.setIcon(imgAbout);

		btnQuit.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnQuit.setHorizontalAlignment(SwingConstants.LEFT);
		btnQuit.setAlignmentY(Component.TOP_ALIGNMENT);
		ImageIcon imgQuit = new ImageIcon(new ImageIcon(MainScreen.class.getResource("/quit.png")).getImage()
				.getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		btnQuit.setIcon(imgQuit);

		btnNewEntry.setFont(font);
		btnHelp.setFont(font);
		btnAbout.setFont(font);
		btnQuit.setFont(font);

		btnNewEntry.setBackground(color2);
		btnHelp.setBackground(color2);
		btnAbout.setBackground(color2);
		btnQuit.setBackground(color2);

		btnNewEntry.setForeground(Color.black);
		btnHelp.setForeground(Color.black);
		btnAbout.setForeground(Color.black);
		btnQuit.setForeground(Color.black);

		btnNewEntry.setBorder(new RoundedBorder(Color.white, 3, 20));
		btnHelp.setBorder(new RoundedBorder(Color.white, 3, 20));
		btnAbout.setBorder(new RoundedBorder(Color.white, 3, 20));
		btnQuit.setBorder(new RoundedBorder(Color.white, 3, 20));

		// add things to panels
		JPanel btnPanel = new JPanel(new GridLayout(-100, 1, 0, 10));
		btnPanel.setBackground(color);
		btnPanel.setBorder(new EmptyBorder(0, 100, 100, 100)); // top left bottom right
		btnPanel.add(btnNewEntry);
		btnPanel.add(btnHelp);
		btnPanel.add(btnAbout);
		btnPanel.add(btnQuit);

		Box vertBox = Box.createVerticalBox();
		vertBox.setBackground(color);

		vertBox.add(logo);
		vertBox.add(btnPanel);
		contentPanel.add(vertBox, BorderLayout.CENTER);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static JMenu createMenuFile() {
		JMenu m = new JMenu("File");

		JMenuItem i2 = new JMenuItem("Quit");
		m.add(i2);
		i2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		return m;
	}

	public JMenu createMenuHelp() {
		JMenu m = new JMenu("Help");
		JMenuItem i1 = new JMenuItem("Help");
		m.add(i1);
		i1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// new Help();
			}
		});
		JMenuItem i2 = new JMenuItem("About");
		m.add(i2);
		i1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// new About();
			}
		});

		return m;
	}

	public void createTopBarMenu() {
		JButton btnHelp = new JButton("Help");
		btnHelp.setFont(font);
		btnHelp.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnHelp.setAlignmentY(Component.TOP_ALIGNMENT);
		btnHelp.setHorizontalAlignment(SwingConstants.LEFT);
		// ImageIcon imgHelp = new ImageIcon(
		// new ImageIcon(MainScreen.class.getResource("/help.png")
		// ).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		// btnHelp.setIcon(imgHelp);
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// display help (rules and controls)
				// new Help();
			}
		});

		JButton btnAbout = new JButton("About");
		btnAbout.setFont(font);
		btnAbout.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnAbout.setAlignmentY(Component.TOP_ALIGNMENT);
		btnAbout.setHorizontalAlignment(SwingConstants.LEFT);
		// ImageIcon imgAbout = new ImageIcon(
		// new ImageIcon(MainScreen.class.getResource("/about.png")
		// ).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		// btnAbout.setIcon(imgAbout);
		btnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// display about
				// new About();
			}
		});

		JButton btnQuit = new JButton("Quit");
		btnQuit.setFont(font);
		btnQuit.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnQuit.setAlignmentY(Component.TOP_ALIGNMENT);
		btnQuit.setHorizontalAlignment(SwingConstants.LEFT);
		// ImageIcon imgQuit = new ImageIcon(
		// new ImageIcon(MainScreen.class.getResource("/quit.png")
		// ).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		// btnQuit.setIcon(imgQuit);
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		JMenuBar menuBar = new JMenuBar();
		contentPanel.add(menuBar, BorderLayout.NORTH);

		menuBar.add(createMenuFile());
		menuBar.add(createMenuHelp());
		menuBar.add(Box.createHorizontalGlue());
	}

}
