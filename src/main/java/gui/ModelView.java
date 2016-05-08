package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

import lw2.projet.controller.StbController;
import lw2.projet.model.Stb;

public class ModelView {
	private StbController controller;
	private JFrame frame;
	private JTextPane textPane;
	private JButton home;
	private JButton resume;
	private JButton resumeN;
	private JSpinner spin;
	private JButton depot;
	private JFileChooser fileChooser;
	

	public ModelView() {
		controller = new StbController();
		createView();
		placeComponents();
		createControllers();
	}
	
	private void createView() {
		frame = new JFrame("Client REST");
		frame.setPreferredSize(new Dimension(500, 400));
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		
		home = new JButton("Accueil");
		resume = new JButton("/resume");
		resumeN = new JButton("/resume/n");
		spin = new JSpinner(new SpinnerNumberModel(0, 0, 25, 1));
		depot = new JButton("/depot");
		
		fileChooser = new JFileChooser();
		
	}

	private void placeComponents() {
		JPanel p = new JPanel(new BorderLayout()); {
			p.add(new JScrollPane(textPane));
			p.setMinimumSize(frame.getSize());
		}
		frame.add(p, BorderLayout.CENTER);
		
		p = new JPanel(); {
			p.add(home);
			p.add(resume);
			JPanel q = new JPanel(); {
				q.add(resumeN);
				q.add(spin);
			}
			p.add(q);
			p.add(depot);
		}
		frame.add(p, BorderLayout.NORTH);
	}

	private void createControllers() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textPane.setContentType("text/html");
				textPane.setText(controller.accueil());
			}
		});
		
		resume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textPane.setContentType("text/plain");
				String text = "";
				List<Stb> list = controller.getModel().getShortStbList();
				for (Stb stb : list) {
					text += stb.toShortString();
				}
				textPane.setText(text);
			}
		});
		
		resumeN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textPane.setContentType("text/plain");
				int n = (Integer) spin.getValue();
				List<Stb> list = controller.getModel().getStbList();
				if (n < list.size()) {
					textPane.setText(list.get(n).toString());
				} else {
					textPane.setText("");
				}
			}
		});
		
		depot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnVal = fileChooser.showOpenDialog(frame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					controller.depot(fileChooser.getSelectedFile().getAbsolutePath());
			    }
			}
		});
	}

	public void display() {
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ModelView().display();
            }
        });
	}
}
