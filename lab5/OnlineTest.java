import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class Question {
	private String question;
	// unique number given for each new Question object
	// cannot be changed
	private static int nextNumber = 1;
	private int number = 1;
	private String ansNum;

	public Question(String newQuestion) {
		// string is question with ans num appended to 
		// question mark
		question = newQuestion.substring(0,newQuestion.length()-1);
		ansNum = newQuestion.substring(newQuestion.length()-1);
		number = nextNumber;
		nextNumber++;
	}

	public String getAnswerNum() {
		return ansNum;
	}

	public String toString() {
		return number + ". " + question;
	}
} // End of Question.class


class  OnlineTest extends JFrame implements ActionListener {

	JLabel qLbl;
	JPanel radioPnl, bottomPnl;
	JButton verifyBtn, nextBtn;
	JLabel marksLbl;
	ButtonGroup group;

	Question[] questions;
	final int MAXQUESTIONS = 6;
	int qNum;

	int marks;
	int qCounter;

	final String questionsFile = "questions.txt";

	public OnlineTest() {
		marks = 0;
		setLayout(new GridLayout(3,1));

		qLbl = new JLabel();
		qLbl = setupQLbl();
		add(qLbl);

		radioPnl = new JPanel(new GridLayout(2,2));
		createRadioBtns(radioPnl);
		add(radioPnl);

		bottomPnl = new JPanel(new GridLayout(1,3));
		marksLbl = new JLabel(marks + "/0", JLabel.CENTER);
		bottomPnl.add(marksLbl);
		verifyBtn = setupBtn("Verify", bottomPnl);
		nextBtn = setupBtn("Next", bottomPnl);
		verifyBtn.setEnabled(true);
		nextBtn.setEnabled(false);
		add(bottomPnl);
	}

	JLabel setupQLbl() {
		JLabel l = new JLabel();
     	l.setFont(new Font("Sansserif",Font.PLAIN,18));
      	l.setBackground(Color.white);
      	l.setOpaque(true);
      	return l;
	}

	void createRadioBtns(Container c) {
		JRadioButton radioBtn1 = new JRadioButton("ANSWER 1");
    	radioBtn1.setActionCommand("1");
    	radioBtn1.setSelected(true);

    	JRadioButton radioBtn2 = new JRadioButton("ANSWER 2");
    	radioBtn2.setActionCommand("2");

    	JRadioButton radioBtn3 = new JRadioButton("ANSWER 3");
    	radioBtn3.setActionCommand("3");

    	JRadioButton radioBtn4 = new JRadioButton("ANSWER 4");
    	radioBtn4.setActionCommand("4");

    	//Group the radio buttons.
    	group = new ButtonGroup();
    	group.add(radioBtn1);
    	group.add(radioBtn2);
    	group.add(radioBtn3);
    	group.add(radioBtn4);
    	c.add(radioBtn1);
    	c.add(radioBtn2);
    	c.add(radioBtn3);
    	c.add(radioBtn4);

    	//Register a listener for the radio buttons.
    	radioBtn1.addActionListener(this);
    	radioBtn2.addActionListener(this);
    	radioBtn3.addActionListener(this);
    	radioBtn4.addActionListener(this);
	}

	JButton setupBtn(String s, Container c) {
		JButton b = new JButton(s);
      	b.setFont(new Font("Sansserif",Font.PLAIN,18));
      	b.setBackground(Color.white);
      	b.setOpaque(true);
      	c.add(b);
      	b.addActionListener(this);
      	return b;
	}

	void readQuestions() throws IOException {
		BufferedReader file;
		questions = new Question[MAXQUESTIONS];
		qNum = 0;
		try {
			file = new BufferedReader(new FileReader(questionsFile));
		} catch (FileNotFoundException e) {
			System.out.print(e.getMessage());
			return;
		}
		String line = file.readLine();
		while (line!=null && qNum<MAXQUESTIONS) {
			questions[qNum] = new Question(line);
			qNum++;
			line = file.readLine();
		}
		file.close();
	}

	void showQuestion() {
		qLbl.setText(questions[qCounter].toString());
	}
	
	public void actionPerformed(ActionEvent e) {  
		if (e.getSource()==verifyBtn) {
			// check if answer is correct
			// change marks label
			// enable next button and disable verify button
			if (group.getSelection().getActionCommand()
				.equals(questions[qCounter].getAnswerNum())) {
				marks++;
			}
			marksLbl.setText(marks + "/" + (qCounter+1));
			verifyBtn.setEnabled(false);
			// check if MAX Qs answered
			if (qCounter==MAXQUESTIONS-1) {
				nextBtn.setEnabled(false);
				System.out.println("You scored " + marks + " out of " + (qCounter+1));
				//System.exit(0);
			} else {
				nextBtn.setEnabled(true);
			}
		}
		if (e.getSource()==nextBtn) {
			// inc counter to go to next question
			// disable next button and enable verify button
			qCounter++;
			showQuestion();
			verifyBtn.setEnabled(true);
			nextBtn.setEnabled(false);
		}
   	}

} // End of OnlineTest.class


class TestOnlineTest {
	public static void main(String[] args) throws IOException {
		OnlineTest ot = new OnlineTest();
		ot.setSize(600,200);
		ot.setTitle("Online Test");
		ot.setVisible(true);
		ot.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		ot.readQuestions();
		ot.showQuestion();
	}
}