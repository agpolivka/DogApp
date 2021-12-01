import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RandomDogPane extends JPanel implements ActionListener
{
  // Declaring all button components and variables
  private String choice;

  private JPanel buttonPad;

  private JButton back;
  private JButton random;
  private JButton makeMeme;

  // Declaring all things for image displaying
  URL dogImage;

  public JPanel randImgPane;
  private JLabel randDogIcon;

  private JSplitPane splitPane;

  // Declaring and initializing all default dimensions
  private final int windowWidth = 1024;
  private final int windowHeight = 768;

  // Meme Editor stuff
  private ImageIcon icon;
  public static boolean openAlready = false;
  public boolean clicked = false;

  public RandomDogPane()
  {
    // Initializing and setting up "New Dog Picture" button
    random = new JButton("New Dog Picture");
    random.setVisible(true);
    random.setPreferredSize(new Dimension(100, 100));
    random.addActionListener((ActionListener) this);

    // Initializing and setting up "Back" button
    back = new JButton("Back");
    back.addActionListener((ActionListener) this);
    back.setPreferredSize(new Dimension(100, 100));
    back.setVisible(true);

    // Initializing and setting up "Make a meme!" button
    makeMeme = new JButton("Make a meme!");
    makeMeme.addActionListener((ActionListener) this);
    makeMeme.setPreferredSize(new Dimension(100, 100));
    makeMeme.setVisible(true);

    // Initializing and setting up
    randDogIcon = new JLabel(" ", JLabel.CENTER);
    randDogIcon.setVisible(true);
    randDogIcon.setPreferredSize(new Dimension(windowWidth, windowHeight));

    // Initializing and setting up buttonPad
    buttonPad = new JPanel();
    buttonPad.setPreferredSize(new Dimension(windowWidth, 100));
    buttonPad = new JPanel(new GridLayout(1, 3));
    buttonPad.add(random);
    buttonPad.add(back);
    buttonPad.add(makeMeme);

    // Initializing SplitPane that houses the image buttons and images
    splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, buttonPad, randDogIcon);

    // Initializing JPanel that hold everything else
    randImgPane = new JPanel();
    randImgPane.add(splitPane);

  }

  public void getDogImage() throws IOException
  {
    URL url = new URL("https://dog.ceo/api/breeds/image/random");

    ObjectMapper mapper = new ObjectMapper();
    JsonNode tree = mapper.readTree(url);

    String urlString = tree.get("message").asText();
    URL urlDogPics = new URL(urlString);

    Image currImg;
    currImg = ImageIO.read(urlDogPics);
    icon = new ImageIcon(currImg);
    randDogIcon.setIcon(icon);
  }

  /*
   * Holds the functionality for all buttons in the Random Image Pane.
   */
  public void actionPerformed(ActionEvent buttonEvent)
  {
    choice = buttonEvent.getActionCommand();

    if (choice.equals("New Dog Picture"))
    {
      clicked = true;
      try
      {
        this.getDogImage();
      }
      catch (IOException e1)
      {
        e1.printStackTrace();
      }
    }
    else if (choice.equals("Back"))
    {
      if (!openAlready)
      {
        Window.layout.show(Window.layoutPane, "homescreen");
        Window.frame.setTitle("Dog App");
      }
      else
      {
        JOptionPane.showMessageDialog(Window.frame,
            "You have a meme editor open. \nPlease close your Meme editor window to go back, thank you.\n",
            "Open Meme Editor", JOptionPane.WARNING_MESSAGE);
      }
    }
    else if (choice.equals("Make a meme!"))
    {
      if (clicked)
      {
        if (!openAlready)
        {
          new MemeMaker(icon);
          openAlready = true;
        }
        else
        {
          JOptionPane.showMessageDialog(Window.frame,
              "You already have a meme editor open. \nPlease close your current Meme editor window to open a new one, thank you.\n",
              "Open Meme Editor", JOptionPane.WARNING_MESSAGE);
        }
      }
    }
    else
    {
      System.out.println("Invalid Command");
    }
  }
  // j = new JTextArea(factList.get((int) Math.floor(Math.random() * (100 - 0 + 1) + 0)));
  // sp.setBottomComponent(j);

}
