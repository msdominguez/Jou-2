import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class HelpDialog extends JDialog {

	public final JPanel contentPanel = new JPanel();
	private static final long serialVersionUID = 21305503976948004L;

	public HelpDialog() {
		setIconImage(new ImageIcon(MainScreen.class.getResource("/jou-2-logo-small.png")).getImage());
		setTitle("Help - jou-2");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		getContentPane().add(contentPanel, BorderLayout.WEST);

		contentPanel.setLayout(new GridLayout(1, 0));

		JTextArea textArea = new JTextArea("");
		textArea.setFont(MainScreen.font);
		JScrollPane scrollPane = new JScrollPane(textArea);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		textArea.setOpaque(false);
		textArea.setBackground(new Color(91, 173, 106, 0));

		// set text from file
		setText(textArea);

		scrollPane.setOpaque(false);
		scrollPane.setPreferredSize(new Dimension(450, 225));
		getContentPane().add(scrollPane, BorderLayout.NORTH);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");
		okButton.setFont(MainScreen.font);
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		setVisible(true);
	}

	public void setText(JTextArea textArea) {
		try {
			InputStream in = MainScreen.class.getResourceAsStream("/help.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));

			textArea.read(br, null);
			br.close();
			textArea.requestFocus();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
