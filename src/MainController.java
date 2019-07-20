import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController implements Controller {
	private MainPanel view;

	public MainController(MainPanel v) {
		setView(v);
		view.setController(this);
	}

	// private classes for button action listeners
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

	public void setView(MainPanel v) {
		// register listeners for buttons
		v.btnNewEntry.addActionListener(new NewEntryListener());
		v.btnViewEntries.addActionListener(new ViewEntriesListener());
		v.btnSettings.addActionListener(new SettingsListener());
		v.btnHelp.addActionListener(new HelpListener());
		v.btnAbout.addActionListener(new AboutListener());
		v.btnQuit.addActionListener(new QuitListener());

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
