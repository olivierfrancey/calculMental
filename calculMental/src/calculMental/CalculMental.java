package calculMental;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class CalculMental extends JFrame implements ActionListener, KeyListener {

	private JTextField result;
	
	private JLabel operation;
	private JLabel nbreOperations;
	private JLabel nbreJustes;
	private JLabel nbreAdditions;
	private JLabel nbreSoustractions;
	private JLabel nbreMultiplications;
	private JLabel nbreDivisions;
	private JLabel addNiveau;
	private JLabel sousNiveau;
	private JLabel multNiveau;
	private JLabel divNiveau;
	private JLabel message;
	private JLabel score;
	private JLabel title;
	private JLabel statTitle;
	
	private Bouton addition	      = new Bouton(this, "+");
	private Bouton soustraction   = new Bouton(this, "-");
	private Bouton multiplication = new Bouton(this, "\u00D7");
	private Bouton division	   	  = new Bouton(this, "\u00F7");
	private Bouton ok;
	
	private JMenuBar menuBar;
	private JMenuItem menuNouveau;
	private JMenuItem menuQuitter;
	
	private Operation op = new Addition(addition, soustraction, multiplication, division);
	
	private Level addLevel;
	private Level sousLevel;
	private Level multLevel;
	private Level divLevel;

	/**
	 * Cree la fenetre.
	 */
	public CalculMental() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Panneau contentPane = new Panneau(this, new BorderLayout());
		contentPane.setBorder(new EmptyBorder(5, 0, 5, 0));

//***barre de menu*************************************************************
		
		menuBar = new JMenuBar();

		JMenu menuFichier = new JMenu("Fichier");

		menuNouveau = new JMenuItem("Nouveau");
		menuQuitter = new JMenuItem("Quitter");

		menuNouveau.addActionListener(this);
		menuQuitter.addActionListener(this);

		menuFichier.add(menuNouveau);
		menuFichier.addSeparator();  					// Ajouter un séparateur
		menuFichier.add(menuQuitter);

		menuBar.add(menuFichier);
		setJMenuBar(menuBar);
		
//*******************************************************<fin> barre de menu***

//***container calcul**********************************************************
		
		Panneau calcul = new Panneau(contentPane, new GridLayout(3,1), BorderLayout.NORTH);
		
	// head 
		Panneau head = new Panneau(calcul, new GridLayout(1,2));
		
	// score
		Panneau sco = new Panneau(head, new FlowLayout(FlowLayout.LEFT));
		sco.add(title = new JLabel("Calcul mental"));
		title.setFont(new Font("Helvetica", Font.BOLD, 20));
				
	// operation
		Panneau oper = new Panneau(calcul, new FlowLayout(FlowLayout.RIGHT));
		oper.setPreferredSize(new Dimension(300,60));
		oper.setBackground(new Color(190, 230, 255));
		//oper.setBorder(new MatteBorder(1,1,1,1, Color.GRAY));
		
		oper.add(operation = new JLabel(op.getOperation()));
		operation.setFont(new Font("Helvetica", Font.PLAIN, 40));
		
		oper.add(result = new JTextField());
		result.setFont(new Font("Helvetica", Font.PLAIN, 40));
		result.setColumns(3);
		result.addKeyListener(this);
		
		oper.add(ok = new Bouton(this, "OK"));
		ok.setPreferredSize(new Dimension(100,50));	
	
	// message
		Panneau mess = new Panneau(calcul, new FlowLayout(FlowLayout.CENTER));
		mess.add(message = new JLabel(""));

//****************************************************<fin> container calcul***
		
//***container levels**********************************************************	
		
		Panneau levels = new Panneau(contentPane, new BorderLayout(), BorderLayout.CENTER);
		levels.setPreferredSize(new Dimension(240,230));
		
		Panneau boutNiv = new Panneau(levels, new GridLayout(1,4), BorderLayout.NORTH);		
		boutNiv.add(addition);
		boutNiv.add(soustraction);
		boutNiv.add(multiplication);
		boutNiv.add(division);

		Panneau barNiv = new Panneau(levels, new GridLayout(1,4), BorderLayout.CENTER);		
		barNiv.add(addLevel  = new Level(this));
		barNiv.add(sousLevel = new Level(this));
		barNiv.add(multLevel = new Level(this));
		barNiv.add(divLevel  = new Level(this));		
		
		Panneau textNiv = new Panneau(levels, new GridLayout(1,4), BorderLayout.SOUTH);
		textNiv.add(addNiveau  = new JLabel("0%", SwingConstants.CENTER));
		textNiv.add(sousNiveau = new JLabel("0%", SwingConstants.CENTER));
		textNiv.add(multNiveau = new JLabel("0%", SwingConstants.CENTER));
		textNiv.add(divNiveau  = new JLabel("0%", SwingConstants.CENTER));		

//***************************************************<fin> container niveaux***
		
//***container Stats***********************************************************
		
		Panneau stats = new Panneau(contentPane, new GridLayout(9,1), BorderLayout.EAST);
		stats.setPreferredSize(new Dimension(200,200));
		
	// Stats
		stats.add(statTitle 			= new JLabel("Statistiques"));
		stats.add(score 				= new JLabel("Score: 0%"));
		stats.add(nbreOperations 		= new JLabel("Calculs: 0"));
		stats.add(nbreJustes 			= new JLabel("Justes: 0"));
		stats.add(nbreAdditions 		= new JLabel("Additions: 0/0"));
		stats.add(nbreSoustractions 	= new JLabel("Soustractions: 0/0"));
		stats.add(nbreMultiplications 	= new JLabel("Multiplications: 0/0"));
		stats.add(nbreDivisions 		= new JLabel("Divisions: 0/0"));
		stats.add(new JLabel());
		
		statTitle.setFont(new Font("Helvetica", Font.BOLD, 15));
		
//*****************************************************<fin> container stats***		
	}
	
// Implementation des methodes ActionListener
	public void actionPerformed(ActionEvent event)
	{
		Object source = event.getSource();
		if (source == menuNouveau)		   {restart();}
		else if (source == menuQuitter)    {System.exit(0);}
		else if (source == addition) 	   {op = new Addition(addition, soustraction, multiplication, division); this.okAction(false);}
		else if (source == soustraction)   {op = new Soustraction(addition, soustraction, multiplication, division); this.okAction(false);}
		else if (source == multiplication) {op = new Multiplication(addition, soustraction, multiplication, division); this.okAction(false);}
		else if (source == division) 	   {op = new Division(addition, soustraction, multiplication, division); this.okAction(false);}
		else if (source == ok)			   {this.okAction(true);}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		if((e.getKeyCode() == KeyEvent.VK_ENTER)){
			this.okAction(true);
		}	
	}

	@Override
	public void keyReleased(KeyEvent e) {}
	
	public void okAction(boolean t) {  // Opérations par défaut lorsqu'un bouton est pressé
		if (t){
			try {
				int res = Integer.parseInt(result.getText());
				String txt = new String();
				if (op.checkResult(res)){
					txt = "Bravo! ta réponse est juste.";
				} else {
					txt = "Désolé, mais ta réponse est fausse, recommence.";
				}
				message.setText(txt);
			} catch (Exception e) { 
				message.setText("");
			}
		}
		
	// Propose une nouvelle équation
		if (op.getStatut()) op.setOperation();
		else 				swapOperations();
		
	// Mise à jour des zones de textes
		addLevel.setLevel(op.getAdditionLevel());			// met à jour les niveaux
		sousLevel.setLevel(op.getSoustractionLevel());
		multLevel.setLevel(op.getMultiplicationLevel());
		divLevel.setLevel(op.getDivisionLevel());
		
		addNiveau.setText(String.valueOf((int)(op.getAdditionLevel()*100)) + "%");
		sousNiveau.setText(String.valueOf((int)(op.getSoustractionLevel()*100)) + "%");
		multNiveau.setText(String.valueOf((int)(op.getMultiplicationLevel()*100)) + "%");
		divNiveau.setText(String.valueOf((int)(op.getDivisionLevel()*100)) + "%");
		
		operation.setText(op.getOperation());				// affiche la nouvelle opération
		result.setText(null);								// vide la zone de résultat
		result.grabFocus();									// place le curseur dans la zone de résultat
		score.setText(op.getScore());						// met à jour le score
		
		nbreOperations.setText(String.valueOf(op.getNbreOperations()));	// met à jour les statistiques
		nbreJustes.setText(String.valueOf(op.getNbreJustes()));
		nbreAdditions.setText(op.getNbreAdditions());
		nbreSoustractions.setText(op.getNbreSoustractions());
		nbreMultiplications.setText(op.getNbreMultiplications());
		nbreDivisions.setText(op.getNbreDivisions());
	}
	
	public void swapOperations(){
		if (op.getAddStatut())      {op = new Addition(addition, soustraction, multiplication, division); this.okAction(false);}
		else if (op.getSousStatut()){op = new Soustraction(addition, soustraction, multiplication, division); this.okAction(false);}
		else if (op.getMultStatut()){op = new Multiplication(addition, soustraction, multiplication, division); this.okAction(false);}
		else if (op.getDivStatut()) {op = new Division(addition, soustraction, multiplication, division); this.okAction(false);}
		else {
			addition.setButtonInactive();
			soustraction.setButtonInactive();
			multiplication.setButtonInactive();
			division.setButtonInactive();
			operation.setText("");
			message.setText("Partie terminée, bravo");
		}
	}
	
	public void restart() {
		op.reset();
		okAction(false);
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalculMental frame = new CalculMental();
					frame.pack();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
