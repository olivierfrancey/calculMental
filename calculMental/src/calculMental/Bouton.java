package calculMental;

import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Font;

public class Bouton extends JButton {
	
	private Dimension dim = new Dimension(40,40);
	private CalculMental host;
	
	public Bouton(CalculMental _host, String text){
		super();
		host = _host;
		addActionListener(host);
		setText(text);
		setPreferredSize(dim);
		setFont(new Font("Helvetica", Font.PLAIN, 20));
		setBorderPainted(false);
	}
	
	public void setButtonActive(boolean stat){
		if (stat){
			this.setEnabled(true);
		}else{
			// bouton ne pouvant pas être actif
			this.setEnabled(false);
		}
	}
	
	public void setButtonInactive(){
		this.setEnabled(false);
	}
}
