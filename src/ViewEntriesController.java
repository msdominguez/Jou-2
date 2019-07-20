import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ViewEntriesController implements Controller, KeyListener {
	private ViewEntriesPanel view;
	public File[] filesInDirectory;
	public Vector<File> listOfFiles;
	public File selectedFile;
	public int fileIndex;
	public int numFiles;

	public ViewEntriesController(ViewEntriesPanel v) {
		setView(v);
		view.setController(this);

	}

	// action listener classes
	private class HomeListener implements ActionListener {
		public HomeListener() {

		}

		public void actionPerformed(ActionEvent e) {
			// reset and go home
			view.entryNumber.setText("0 / 0");
			selectedFile = null;
			view.entryArea.setText("Load in an entry ☁ ");
			view.cardLayout.show(view.cardPanel, "Main");
		}
	}

	private class ChooseFileListener implements ActionListener {
		public ChooseFileListener() {

		}

		public void actionPerformed(ActionEvent e) {
			// open file chooser here and load the file into jtextarea
			String path = "C:\\Users\\MSD\\eclipse-workspace\\jou-2\\entries";
			JFileChooser jfc = new JFileChooser(path);
			int chooser = jfc.showOpenDialog(null);

			if (chooser == JFileChooser.APPROVE_OPTION) {
				try {
					File f = new File(jfc.getSelectedFile().getAbsolutePath());
					selectedFile = f;
					InputStream in = new FileInputStream(f);
					BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));

					view.entryArea.read(br, null);
					br.close();
					// view.entryArea.requestFocus();
				} catch (Exception ex) {
					System.out.println(ex);
				}

				// load in all the files
				filesInDirectory = jfc.getCurrentDirectory().listFiles();
				listOfFiles = new Vector<File>();

				for (File file : filesInDirectory) {
					listOfFiles.add(file);
				}

				// set label
				fileIndex = listOfFiles.indexOf(selectedFile) + 1;
				numFiles = listOfFiles.size();
				view.entryNumber.setText(String.valueOf(fileIndex) + " / " + String.valueOf(numFiles));

				// give cardpanel focus again for keylistener
				view.cardPanel.setFocusable(true);
				view.cardPanel.requestFocus();
			}
		}
	}

	private class ForwardListener implements ActionListener {
		public ForwardListener() {

		}

		public void actionPerformed(ActionEvent e) {
			// load in the next file from file chooser into jtextarea
			String path = "C:\\Users\\MSD\\eclipse-workspace\\jou-2\\entries";
			JFileChooser jfc = new JFileChooser(path);

			if (selectedFile != null) {
				int currentIndex = listOfFiles.indexOf(selectedFile);
				int nextIndex = currentIndex + 1;
				// if none, cycle
				if (nextIndex == -1) {
					nextIndex = listOfFiles.size() - 1; // idk if -1 is right
				}
				if (nextIndex > listOfFiles.size() - 1) {
					nextIndex = 0;
				}

				File nextFile = listOfFiles.get(nextIndex);
				try {
					selectedFile = nextFile;
					InputStream in = new FileInputStream(nextFile);
					BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));

					view.entryArea.read(br, null);
					br.close();
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
			// set label
			fileIndex = listOfFiles.indexOf(selectedFile) + 1;
			numFiles = listOfFiles.size();
			view.entryNumber.setText(String.valueOf(fileIndex) + " / " + String.valueOf(numFiles));

			// give cardpanel focus again for keylistener
			view.cardPanel.setFocusable(true);
			view.cardPanel.requestFocus();
		}
	}

	private class BackwardListener implements ActionListener {
		public BackwardListener() {

		}

		public void actionPerformed(ActionEvent e) {
			// load in the previous file from file chooser into jtextarea
			String path = "C:\\Users\\MSD\\eclipse-workspace\\jou-2\\entries";
			JFileChooser jfc = new JFileChooser(path);

			if (selectedFile != null) {
				int currentIndex = listOfFiles.indexOf(selectedFile);
				int nextIndex = currentIndex - 1;
				// if none, cycle
				if (nextIndex == -1) {
					nextIndex = listOfFiles.size() - 1; // idk if -1 is right
				}
				if (nextIndex > listOfFiles.size() - 1) {
					nextIndex = 0;
				}

				File nextFile = listOfFiles.get(nextIndex);
				try {
					selectedFile = nextFile;
					InputStream in = new FileInputStream(nextFile);
					BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));

					view.entryArea.read(br, null);
					br.close();
					// view.entryArea.requestFocus();
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
			// set label
			fileIndex = listOfFiles.indexOf(selectedFile) + 1;
			numFiles = listOfFiles.size();
			view.entryNumber.setText(String.valueOf(fileIndex) + " / " + String.valueOf(numFiles));

			// give cardpanel focus again for keylistener
			view.cardPanel.setFocusable(true);
			view.cardPanel.requestFocus();
		}
	}

	private class EditSaveListener implements ActionListener {
		public EditSaveListener() {

		}

		public void actionPerformed(ActionEvent e) {
			if (selectedFile != null) {
				if (view.btnEditSave.getText().equals("Edit")) {
					view.entryArea.setEditable(true);
					view.btnEditSave.setText("Save");
					ViewEntriesPanel.createbtnSaveCancel(view.btnEditSave, "/ok.png");
					view.btnEditCancel.setVisible(true);
				} else {
					view.btnEditCancel.setVisible(false);
					// save entry
					view.entryArea.setEditable(false);
					view.btnEditSave.setText("Edit");
					ViewEntriesPanel.createbtnSaveCancel(view.btnEditSave, "/edit.png");

					try {
						Writer writer = new BufferedWriter(
								new OutputStreamWriter(new FileOutputStream(selectedFile), "utf-8"));

						view.entryArea.write(writer);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, ".txt writing error.");
					}
				}
			}
			// give cardpanel focus again for keylistener
			view.cardPanel.setFocusable(true);
			view.cardPanel.requestFocus();
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

	private class EditCancelListener implements ActionListener {
		public EditCancelListener() {

		}

		public void actionPerformed(ActionEvent e) {
			view.btnEditCancel.setVisible(false);
			view.entryArea.setEditable(false);
			view.btnEditSave.setText("Edit");
			ViewEntriesPanel.createbtnSaveCancel(view.btnEditSave, "/edit.png");
		}
	}

	public void setView(ViewEntriesPanel v) {
		v.cardPanel.addKeyListener(this);

		// register listeners for buttons
		v.btnBack.addActionListener(new HomeListener());
		v.btnChooseEntry.addActionListener(new ChooseFileListener());
		v.btnForward.addActionListener(new ForwardListener());
		v.btnBackward.addActionListener(new BackwardListener());
		v.btnEditSave.addActionListener(new EditSaveListener());
		v.btnEditCancel.addActionListener(new EditCancelListener());

		// menu items
		v.itemNewEntry.addActionListener(new NewEntryListener());
		v.itemViewEntries.addActionListener(new ViewEntriesListener());
		v.itemEditEntry.addActionListener(new EditSaveListener());
		v.itemSettings.addActionListener(new SettingsListener());
		v.itemQuit.addActionListener(new QuitListener());
		v.itemHelp.addActionListener(new HelpListener());
		v.itemAbout.addActionListener(new AboutListener());

		this.view = v;
	}

	public View getView() {
		return view;
	}

	// key listener methods
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			this.view.btnBackward.doClick();
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			this.view.btnForward.doClick();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}
