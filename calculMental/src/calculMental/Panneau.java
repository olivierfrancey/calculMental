package calculMental;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;

public class Panneau extends JPanel {
	
	public Panneau () {
		super();
		setBackground(Color.WHITE);
	}
	
	public Panneau (JPanel host, GridLayout lay) {
		super(lay);
		setBackground(Color.WHITE);
		host.add(this);
	}
	
	public Panneau (JPanel host, FlowLayout lay) {
		super(lay);
		setBackground(Color.WHITE);
		host.add(this);
	}

	public Panneau(CalculMental host, BorderLayout lay) {
		super(lay);
		setBackground(Color.WHITE);
		host.add(this);
	}
	
	public Panneau (JPanel host, BorderLayout lay, String loc) {
		super(lay);
		setBackground(Color.WHITE);
		host.add(this, loc);
	}
	
	public Panneau (JPanel host, GridLayout lay, String loc) {
		super(lay);
		setBackground(Color.WHITE);
		host.add(this, loc);
	}	
}
