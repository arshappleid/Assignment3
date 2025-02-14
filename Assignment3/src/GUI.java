import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import wiki.*;

public class GUI {

	// this class creates the Graphical User Interface and uses the bot object to
	// facilitate conversation
	private JFrame frame;
	private JTextField input_field;
	JTextPane chatHistory;
	JScrollPane scrollPane;
	static Bot bot = new Bot();
	String input;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 550, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		input_field = new JTextField();
		chatHistory = new JTextPane();
		chatHistory.setEditable(false);

		chatHistory.setBackground(SystemColor.menu);
		scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(chatHistory);

		events();
		frame.getContentPane().add(input_field, BorderLayout.SOUTH);
		input_field.setColumns(10);
		bot.greet();
		output(bot.getOutput());

	}

	private void events() {

		input_field.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = input_field.getText();
				bot.setOutput(bot.name + ": " + input); // show the user input on the gui screen
				// bot.removePunc();
				output(bot.getOutput());
				bot.setInput(input); // set the input , string to bots current input.
				input_field.setText(""); // clear the input field after.

				bot.exchange_count++;
				if (bot.exchange_count <= 1) {
					// in the first exchange count, check if we were able to get the name right.
					// bot.optimizeInput();
					bot.name = PoSTagger.getProperNoun(input);
					if (bot.name == "") // just in case the getProperNoun library fails
						bot.name = input;
					if (input == "") {
						output("Serenity: I'm going to need your name before we continue!");
						bot.name = "User";
					} else {
						bot.setOutput("Serenity: Nice to meet you " + bot.name + " :) How's it going?");
						output(bot.getOutput());
					}
				} else if (input == "") {
					bot.name = "User";
				} else {
					bot.optimizeInput();
					if (bot.check_goodbye()) {
						bot.goodbye();
						output("Serenity: " + bot.getOutput());
					} else {
						bot.updateMoodScore();
						bot.getResponse();
						output("Serenity: " + bot.getOutput());
					}
					if (bot.exchange_count > 15 && bot.exchange_count % 8 == 0) {

						switch ((int) (3 * Math.random())) {
							case 0: {
								bot.inform();
								output("Serenity: " + bot.getOutput());
							}
								break;
							case 1: {
								bot.affirm();
								output("Serenity: " + bot.getOutput());
							}
								break;
							case 2: {
								bot.suggestHelp();
								output("Serenity: " + bot.getOutput());
							}
						}
					}
				}
			}
		});
	}

	private void output(String s) {
		chatHistory.setText(chatHistory.getText() + s + "\n");
	}

}
