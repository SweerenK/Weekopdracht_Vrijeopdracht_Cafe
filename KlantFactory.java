package weekopdracht_cafe;

import java.util.Random;

public class KlantFactory {
	String[][] namenlijst = {{"Ali", "Bernard", "Charles", "David", "Edward", "Fernando", "Gerrit", "Henk", "Iwan","James","Karel","Leo","Menno","Nick"},{"Anne","Bea","Claire","Daisy","Emma","Fenna","Gemma","Hannah", "Ilse", "Jennie", "Karin", "Maaike", "Naomi"}};
	Random random = new Random();
	
	
	public Klant KlantFactory() {
		int randomKlant = random.nextInt(10);
		int geslacht = random.nextInt(2);
		String naam = namenlijst[geslacht][random.nextInt(namenlijst[0].length-1)];
		int geduldigheid;
		
		if(randomKlant < 2) {
			geduldigheid = random.nextInt(75)+1;
		}else {
			geduldigheid = random.nextInt(100)+1;
		}
		
		return (randomKlant < 2) ? new Jeugd(random.nextInt(9)+ 8, naam, geslacht, geduldigheid) : new Volwassene(random.nextInt(70)+18, naam, geslacht, geduldigheid);
	}
}
