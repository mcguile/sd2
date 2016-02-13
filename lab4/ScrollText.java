import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.util.List;

class ScrollText extends JFrame {  
	BufferedReader keyboard =
    new BufferedReader(new InputStreamReader(System.in));

	Label[]  scrollLabels;
	JLabel prompt;
	JTextField textField;
	JPanel scrollPanel;
	JPanel lowerPanel;

	List<String> leftOverStrings = new ArrayList<String>();

	ScrollText() {
		setLayout(new GridLayout(2,1,10,10));

		scrollPanel = new JPanel(new GridLayout(1,10,10,10));
		scrollLabels = new Label[10];
		for (int i=0;i<10;i++){
            scrollLabels[i]=new Label();
            scrollLabels[i].setBackground(Color.white);
            scrollPanel.add(scrollLabels[i]);
 
        }

        add(scrollPanel);

        lowerPanel = new JPanel(new GridLayout(1,2));
        prompt = new JLabel("Enter text and press Return", JLabel.CENTER);
        lowerPanel.add(prompt);
        textField = new JTextField();
        lowerPanel.add(textField);

        add(lowerPanel);

	}

	void shiftStrings() {
		for (int i=0; i<scrollLabels.length; i++) {
			if (i<scrollLabels.length-1) {
				scrollLabels[i].setText(scrollLabels[i+1].getText());
			} else if (!leftOverStrings.isEmpty()) {
				scrollLabels[i].setText(leftOverStrings.get(0));
				leftOverStrings.remove(0);
			} else {
				scrollLabels[i].setText("");
			}
		}
	}

	void scroll() throws IOException {
			String enter = keyboard.readLine();
			String input = textField.getText();
			textField.setEditable(false);
			for (int i=0; i<input.length(); i++) {
				String s = input.substring(i,i+1);
				leftOverStrings.add(s);
				System.out.println(leftOverStrings.toString());
			}
			while (true) {
				enter = keyboard.readLine();
				shiftStrings();
			}
	}

}

class TestScrollText {
	public static void main(String[] args) throws IOException {
		ScrollText st = new ScrollText();
		st.setTitle("Scroll Text");
		st.setSize(400,200);
		st.setVisible(true);
		st.addWindowListener(new WindowAdapter()
                              {  public void windowClosing(WindowEvent e)
                                 {  System.exit(0); }
                              });
		st.scroll();
	}
}