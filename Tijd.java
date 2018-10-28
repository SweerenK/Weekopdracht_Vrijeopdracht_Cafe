package weekopdracht_cafe;

import java.time.LocalTime;
import java.util.ArrayList;

import weekopdracht_cafe.Drank.Drankje;
import weekopdracht_cafe.Drank.DrankjeFactory;
import weekopdracht_cafe.Klant.*;

public class Tijd extends Thread {
	int aantalVerwachteGasten = 2;
	int aantalGenereerPogingen = 0;
	private int snelheid = 50;
	boolean klantAanwezig = false;
	
	
	public int getSnelheid() {
		return snelheid;
	}

	public void setSnelheid(int snelheid) {
		this.snelheid = snelheid;
	}

	

	public void run(Cafe cafe, Manager manager) {
		while (aantalGenereerPogingen < 480) {
			try {
				genereerKlanten(cafe);
				Thread.sleep(snelheid);

				if (klantAanwezig) {
					Klant klant = Cafe.klantenlijst.get(cafe.klantenlijst.size() - 1);
					while (klant.aanwezig && klant.drankenWens.size() != 0) {
						Drankje drankje = new DrankjeFactory().getDrankje(klant.drankenWens.get(0));
						manager.overweegActies(klant, cafe);
						manager.actieNaOverweging(cafe, klant, drankje, getIngameTijd(cafe));
					}
				}

			} catch (Exception e) {	
			}
			klantAanwezig = false;
		}
	}

	public void genereerKlanten(Cafe cafe) {
		aantalGenereerPogingen++;
		if (Main.random.nextInt(1000) > 959) {
			Klant nieuweklant = new KlantFactory().KlantFactory(cafe, getIngameTijd(cafe));
			nieuweklant.gedronkenDrankjes = new ArrayList<Drankje>();
			nieuweklant.binnenkomen(cafe, nieuweklant, getIngameTijd(cafe));
			nieuweklant.genereerWens(cafe);
			klantAanwezig = true;
		}
	}

	public LocalTime getIngameTijd(Cafe cafe) {
		LocalTime ingameTijd = cafe.getOpeningstijd().plusMinutes(aantalGenereerPogingen);
		return ingameTijd;
	}
}