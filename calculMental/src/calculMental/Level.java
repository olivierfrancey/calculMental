package calculMental;

import javax.swing.*;
import java.awt.Color;

public class Level extends JPanel {
	
	private JProgressBar bar;
	private JLabel lab = new JLabel("");
	
// constructeurs d'objet Level
	public Level() {}								// constructeur par défaut
	
	public Level(CalculMental host){				// constructeur principal
		super();
		setBackground(Color.WHITE);
		bar = new JProgressBar();
		bar.setOrientation(SwingConstants.VERTICAL);
		add(bar);	
	}
	
	public void setText(float score) {
		lab.setText(String.valueOf(score * 100.));	// met à jour le niveau (texte)
	}
	
	public void setLevel(float level){
		bar.setValue((int)(level * 100.));			// met à jour le niveau (progress bar)
	}
}
