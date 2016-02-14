package calculMental;

import java.util.Random;

public abstract class Operation {
	
	private String text = "";
	protected static int additionLevel = 0;
	protected static int nbreAdditions = 0;
	protected static int soustractionLevel = 0;
	protected static int nbreSoustractions = 0;
	protected static int multiplicationLevel = 0;
	protected static int nbreMultiplications = 0;
	protected static int divisionLevel = 0;
	protected static int nbreDivisions = 0;
	protected static int pts = 0;
	protected static int nbre = 0;
	protected static boolean addStatut = true;
	protected static boolean sousStatut = true;
	protected static boolean multStatut = true;
	protected static boolean divStatut = true;
	protected int a = 0;
	protected int b = 0;
	protected int c = 0;
	protected int n = 1;
	protected boolean r = false;
	protected static int levelSize = 3;
	protected int nbreLevel = 5;
	
	// Constructeurs par défaut
	public Operation () {}
	
	//abstract Operation newOperation() {}
	public String getOperation () 		  {return text;}
	public void newOperation() 			  {}
	public void setOperation() 			  {}
	
	public float getAdditionLevel() 	  {return (float)additionLevel/(float)(levelSize * nbreLevel);}
	public float getSoustractionLevel()   {return (float)soustractionLevel/(float)(levelSize * nbreLevel);}
	public float getMultiplicationLevel() {return (float)multiplicationLevel/(float)(levelSize * nbreLevel);}
	public float getDivisionLevel() 	  {return (float)divisionLevel/(float)(levelSize * nbreLevel);}
	
	public boolean getAddStatut()		  {return addStatut;}
	public boolean getSousStatut()		  {return sousStatut;}
	public boolean getMultStatut()		  {return multStatut;}
	public boolean getDivStatut()         {return divStatut;}
	public boolean getStatut()            {return true;}
	
	public String getNbreOperations() 	  {return "Calculs:" + nbre;}
	public String getNbreAdditions() 	  {return "Additions: " + additionLevel + "/" + nbreAdditions;}	
	public String getNbreSoustractions()  {return "Soustractions: " + soustractionLevel + "/" + nbreSoustractions;}	
	public String getNbreMultiplications(){return "Multiplications: " + multiplicationLevel + "/" + nbreMultiplications;}	
	public String getNbreDivisions()	  {return "Divisions: " + divisionLevel + "/" + nbreDivisions;}
	public String getNbreJustes() 		  {return "Justes: " + pts;}
	
	public int getResult() 				  {return c;}
	public String getScore() {
		String txt = new String();
		double f;
		f = (double)pts / (double)nbre * 100.;
		txt = "Score: " + (int)f + "%";
		return txt;
	}
	
	public void setAddStatut(boolean a) {addStatut = a;}
	public void setSousStatut(boolean a){sousStatut = a;}
	public void setMultStatut(boolean a){multStatut = a;}
	public void setDivStatut(boolean a) {divStatut = a;}
	
	public boolean checkResult(int _c) 	  {return r;}
	public void checkResult() 			  {nbre++;}

	public void reset() {
		pts = 0;
		nbre = 0;
		additionLevel = 0;
		nbreAdditions = 0;
		soustractionLevel = 0;
		nbreSoustractions = 0;
		multiplicationLevel = 0;
		nbreMultiplications = 0;
		divisionLevel = 0;	
		nbreDivisions = 0;
	}
}

//***Addition******************************************************************

class Addition extends Operation {
	
	private final int STEP = 10;
	
	// Constructeurs
	public Addition(Bouton b1, Bouton b2, Bouton b3, Bouton b4) {
		setOperation();
		b1.setButtonInactive();
		b2.setButtonActive(sousStatut);
		b3.setButtonActive(multStatut);
		b4.setButtonActive(divStatut);
	} 
	
	public void setOperation() {
			Random x = new Random();
			int n = additionLevel/levelSize + 1;
			a = (x.nextInt(STEP-1) +1) + (n-1) * STEP;
			b = x.nextInt(n * STEP -1 ) +1;
			c = a + b;
	}
	
	public boolean getStatut() {return addStatut;}
	
	public String getOperation() {
		String txt = new String();
		txt = a + " + " + b + " = ";
		return txt;
	}
	
	public boolean checkResult(int _c) {
		if (additionLevel == levelSize * nbreLevel -1){setAddStatut(false);}
		nbre++;
		nbreAdditions++;
		if (c == _c) {additionLevel++; r = true; pts++;}
		else r = false;
		return r;
	}
}

//************************************************************<fin> addition***

//***Soustraction**************************************************************

class Soustraction extends Operation {
	
	private final int STEP = 10;
	
	// Constructeur
	public Soustraction(Bouton b1, Bouton b2, Bouton b3, Bouton b4) {
		setOperation();
		b1.setButtonActive(addStatut);
		b2.setButtonInactive();
		b3.setButtonActive(multStatut);
		b4.setButtonActive(divStatut);
	} 
	
	public void setOperation() {
		Random x = new Random();
		int n = soustractionLevel/levelSize + 1;
		a = (x.nextInt(STEP-1) +1) + (n-1) * STEP;
		b = x.nextInt(n * STEP -1 ) +1;
		if (a < b){ int d = a; a = b; b = d; } 
		c = a - b;
	}
	
	public boolean getStatut() {return sousStatut;}
	
	public String getOperation() {
		String txt = new String();
		txt = a + " - " + b + " = ";
		return txt;
	}
	
	public boolean checkResult(int _c) {
		if (soustractionLevel == levelSize * nbreLevel -1){setSousStatut(false);}
		nbre++;
		nbreSoustractions++;
		if (c == _c) {soustractionLevel++; r = true; pts++;}
		else r = false;
		return r;
	}
}

//********************************************************<fin> soustraction***

//***Multiplication************************************************************

class Multiplication extends Operation {
	
	private final int STEP = 5;
	
	// Constructeur
	public Multiplication(Bouton b1, Bouton b2, Bouton b3, Bouton b4) {
		setOperation();
		b1.setButtonActive(addStatut);
		b2.setButtonActive(sousStatut);
		b3.setButtonInactive();
		b4.setButtonActive(divStatut);
	} 
	
	public void setOperation() {
		Random x = new Random();
		int n = multiplicationLevel/levelSize + 1;
		a = x.nextInt(n * STEP -1 ) +1;
		b = (x.nextInt(STEP-1) +1) + (n-1) * STEP;
		c = a * b;
	}
	
	public boolean getStatut() {return multStatut;}
	
	public String getOperation() {
		String txt = new String();
		txt = a + " \u00D7 " + b + " = ";
		return txt;
	}
	
	public boolean checkResult(int _c) {
		if (multiplicationLevel == levelSize * nbreLevel -1){setMultStatut(false);}
		nbre++;
		nbreMultiplications++;
		if (c == _c){ multiplicationLevel++; r = true; pts++;} 
		else r = false;
		return r;
	}
}

//******************************************************<fin> multiplication***

//***Division******************************************************************

class Division extends Operation {
	
	private final int STEP = 3;
	
	// Constructeur
	public Division(Bouton b1, Bouton b2, Bouton b3, Bouton b4) {
		setOperation();
		b1.setButtonActive(addStatut);
		b2.setButtonActive(sousStatut);
		b3.setButtonActive(multStatut);
		b4.setButtonInactive();
	}	
		
	public void setOperation(){
		Random x = new Random();
		int n = divisionLevel/levelSize + 1;
		a = (x.nextInt(STEP-1) +1) + (n-1) * STEP;
		c = x.nextInt(n * STEP -1 ) +1;
		b = a * c;
	} 
	
	public boolean getStatut() {return divStatut;}
	
	public String getOperation() {
		String txt = new String();
		txt = b + " \u00F7 " + a + " = ";
		return txt;
	}
	
	public boolean checkResult(int _c) {
		if (divisionLevel == levelSize * nbreLevel -1){setDivStatut(false);}
		nbre++;
		nbreDivisions++;
		if (c == _c){ divisionLevel++; r = true; pts++;} 
		else r = false;
		return r;
	}
}

//************************************************************<fin> division***
