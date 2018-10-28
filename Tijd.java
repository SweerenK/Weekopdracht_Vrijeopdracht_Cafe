package weekopdracht_cafe;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import weekopdracht_cafe.Drank.Drankje;
import weekopdracht_cafe.Drank.DrankjeFactory;
import weekopdracht_cafe.Klant.*;

public class Tijd extends Thread {
	private int aantalGenereerPogingen = 0;
	private int snelheid = 30, klantDrukte = 959;
	private boolean klantAanwezig = false;
	
	public int getKlantDrukte() {
		return klantDrukte;
	}

	public void setKlantDrukte(int klantDrukte) {
		this.klantDrukte = klantDrukte;
	}
	
	public int getAantalGenereerPogingen() {
		return aantalGenereerPogingen;
	}

	public void setAantalGenereerPogingen(int aantalGenereerPogingen) {
		this.aantalGenereerPogingen = aantalGenereerPogingen;
	}
	
	public int getSnelheid() {
		return snelheid;
	}

	public void setSnelheid(int snelheid) {
		this.snelheid = snelheid;
	}

	public void resetDag(Tijd tijd, Cafe cafe) {
		tijd.setAantalGenereerPogingen(0);
		List<Klant> templist = cafe.getKlantenlijst();
		templist.clear();
		cafe.setKlantenlijst(templist);
		cafe.setOpeningstijd(cafe.getOpeningstijd().plusHours(16));
		cafe.setDagenOpen(cafe.getDagenOpen() + 1);
	}
	
	public void run(Cafe cafe, Manager manager) {
		while (aantalGenereerPogingen < 480) {
			try {
				genereerKlanten(cafe);
				Thread.sleep(snelheid);

				if (klantAanwezig) {
					Klant klant = cafe.getKlantenlijst().get(cafe.getKlantenlijst().size() - 1);
					while (klant.isAanwezig() && klant.getDrankenWens().size() != 0) {
						Drankje drankje = new DrankjeFactory().getDrankje(klant.getDrankenWens().get(0));
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
		if (Main.random.nextInt(1000) > klantDrukte) {
			Klant nieuweklant = new KlantFactory().KlantFactory(cafe, getIngameTijd(cafe));
			nieuweklant.setGedronkenDrankjes(new ArrayList<Drankje>());
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