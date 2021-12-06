import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

/**
 * This class contains the contributers information and app description.
 * 
 * @author Matt Wong, Zach Tucker, Thomas Mandell, Alex Polivka, Jonathan Wist
 *
 */

public class AboutPage extends JPanel implements ActionListener {
  // Declaring AppDescription Text Area and components
  private JTextArea appDescriptTxtArea;

  private String appDescription;

  // Initializing profile and components
  private ImageIcon profile;
  private JLabel profileLabel;

  // Initializing profile names
  private JLabel name1;
  private JLabel name2;
  private JLabel name3;
  private JLabel name4;
  private JLabel name5;

  // Initializing JButtons and necessary variables
  private String choice;

  private JButton back;

  // Initializing Split Pane
  private JSplitPane splitPane;

  // Initializing AboutPage JPanel
  public JPanel aboutPage;

  public AboutPage() throws IOException {
    // Initializing AppDescription text area and components
    // BufferedReader reader = new BufferedReader(new FileReader(new File("dogabout.txt")));
    // while (reader.readLine() != null) {
    // appDescription += reader.readLine() + "\n";
    // }
    try {
      appDescription = new String(Files.readAllBytes(Paths.get("dogabout.txt")));
    } catch (IOException e) {
      e.printStackTrace();
    }


    appDescriptTxtArea = new JTextArea(appDescription);
    appDescriptTxtArea.setLineWrap(true);
    appDescriptTxtArea.setWrapStyleWord(true);
    appDescriptTxtArea.setEditable(false);

    // Initializing and setting up "Back" button
    back = new JButton("Back");
    back.addActionListener((ActionListener) this);
    back.setPreferredSize(new Dimension(100, 100));
    back.setVisible(true);

    //
    profileLabel = new JLabel();

    splitPane = new JSplitPane();

    // Initializing aboutPage JPanel
    aboutPage = new JPanel(new GridLayout(1, 2));

    aboutPage.add(appDescriptTxtArea);
    aboutPage.add(back);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    choice = e.getActionCommand();

    if (choice.equals("Back")) {
      Window.layout.show(Window.layoutPane, "homescreen");
      Window.frame.setTitle("Dog App");
    } else if (choice.equals("")) {

    }

  }
}
