import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class EntryController implements Controller {
	private EntryPanel view;
	private static final DateFormat tf = new SimpleDateFormat("hh:mm a");
	private static final DateFormat df = new SimpleDateFormat("EE, MMM dd, yyyy");

	public EntryController(EntryPanel v) {
		setView(v);
		view.setController(this);
	}

	// private classes for button action listeners
	private class ClearListener implements ActionListener {
		public ClearListener() {
		}

		public void actionPerformed(ActionEvent e) {
			view.resetFieldsEmpty();
		}
	}

	private class SaveEntryListener implements ActionListener {
		public SaveEntryListener() {
		}

		public void actionPerformed(ActionEvent e) {
			new Entry(view.dateField, view.timeField, view.titleField, view.songField, view.entryArea);

			// ask if another entry
			JLabel label = new JLabel("Do you want to make another entry?");
			label.setFont(EntryPanel.font);
			Object[] options = { "Yes", "No" };
			int ans = JOptionPane.showOptionDialog(null, label, "Make another entry?", JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE, null, options, options[1]);

			view.resetFieldsEmpty();
			if (ans == JOptionPane.NO_OPTION) {
				view.resetFields();
				view.cardLayout.show(view.cardPanel, "Main");
			}
		}

	}

	private class CancelListener implements ActionListener {
		public CancelListener() {
		}

		public void actionPerformed(ActionEvent e) {
			JLabel label = new JLabel("Are you sure you want to cancel this entry?");
			// label.setFont(Main.font);
			Object[] options = { "Yes", "No" };
			int ans = JOptionPane.showOptionDialog(null, label, "Go to main screen?", JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE, null, options, options[1]);

			if (ans == JOptionPane.YES_OPTION) {
				view.resetFields();
				view.cardLayout.show(view.cardPanel, "Main");
			}
		}
	}

	private class ClickListener implements MouseListener {
		public ClickListener() {
		}

		public void mouseReleased(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mouseClicked(MouseEvent e) {
			// if at default values then clickable to empty
			if (e.getSource() == view.titleField && view.titleField.getText().equals("My Entry")) {
				view.titleField.setText("");
			} else if (e.getSource() == view.songField && view.songField.getText().equals("Powerlines - Tame Impala")) {
				view.songField.setText("");
			} else if (e.getSource() == view.entryArea && view.entryArea.getText().equals("My Entry ... ☁ ")) {
				view.entryArea.setText("");
			}
		}
	}

	// menu listeners
	private class NewEntryListener implements ActionListener {
		public NewEntryListener() {
		}

		public void actionPerformed(ActionEvent e) {
			view.cardLayout.show(view.cardPanel, "Entry");
		}
	}

	private class ViewEntriesListener implements ActionListener {
		public ViewEntriesListener() {

		}

		public void actionPerformed(ActionEvent e) {
			view.cardLayout.show(view.cardPanel, "View");
		}
	}

	private class SettingsListener implements ActionListener {
		public SettingsListener() {
		}

		public void actionPerformed(ActionEvent e) {
			view.settings.setVisible(true);
		}
	}

	private class HelpListener implements ActionListener {
		public HelpListener() {
		}

		public void actionPerformed(ActionEvent e) {
			new HelpDialog();
		}
	}

	private class AboutListener implements ActionListener {
		public AboutListener() {
		}

		public void actionPerformed(ActionEvent e) {
			new AboutDialog();
		}
	}

	private class QuitListener implements ActionListener {
		public QuitListener() {
		}

		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}

	}

	public void setView(EntryPanel v) {
		// register listeners for buttons
		v.btnClear.addActionListener(new ClearListener());
		v.btnSave.addActionListener(new SaveEntryListener());
		v.btnCancel.addActionListener(new CancelListener());
		v.timeField.addMouseListener(new ClickListener());
		v.dateField.addMouseListener(new ClickListener());
		v.titleField.addMouseListener(new ClickListener());
		v.songField.addMouseListener(new ClickListener());
		v.entryArea.addMouseListener(new ClickListener());

		// menu items
		v.itemNewEntry.addActionListener(new NewEntryListener());
		v.itemViewEntries.addActionListener(new ViewEntriesListener());
		v.itemSettings.addActionListener(new SettingsListener());
		v.itemQuit.addActionListener(new QuitListener());
		v.itemHelp.addActionListener(new HelpListener());
		v.itemAbout.addActionListener(new AboutListener());

		this.view = v;
	}

	public View getView() {
		return view;
	}

}
