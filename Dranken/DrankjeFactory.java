package weekopdracht_cafe.Dranken;

import weekopdracht_cafe.Cafe;

public class DrankjeFactory {
	Cafe cafe = new Cafe();
	private String[][] drankjeslijst = cafe.getDranken();
	
	public Drankje getDrankje(String naamDrankje){
		Drankje x;
		
		for(int i = 0; i < drankjeslijst.length; i++) {
			for(int j = 0; j < drankjeslijst[i].length; j++) {
				if(naamDrankje.equals(drankjeslijst[i][j])) {
					switch(i) {
					case 0:
						x = new Bier(naamDrankje);
						return x;
					case 1:
						x = new Wijn(naamDrankje);
						return x;
					case 2:
						x = new Puur(naamDrankje);
						return x;
					case 3:
						x = new Overig(naamDrankje);
						return x;
					case 4:
						x = new Frisdrank(naamDrankje);
						return x;
					case 5:
						x = new GemixtDrankje(naamDrankje);
						return x;
					default:
						x = new Water("water");
						return x;
					}
				}
			}
		}
		return new Water("water");
	}
}
