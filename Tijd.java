package weekopdracht_cafe;

import java.time.LocalTime;

import weekopdracht_cafe.Dranken.*;
import weekopdracht_cafe.Klanten.*;

public class Tijd implements Runnable {
	
	public void run() {
	}
	
	public void run(Manager manager, Cafe cafe) {
		LocalTime currentTime = cafe.getOpeningstijd();
		int uurOffset = cafe.getSluitingstijd().getHour() + 1;
		System.out.println("De zaak opent om " + currentTime + " uur. We wachten op de eerste klant.\n");
		
		while (currentTime.minusHours(uurOffset).isBefore(cafe.getSluitingstijd().minusHours(uurOffset))) {
			try {
				currentTime = currentTime.plusMinutes(1);
				Thread.sleep(200);
				if (Main.random.nextInt(100) > 96) {
					Thread.currentThread().interrupt();
					Klant nieuweklant = new KlantFactory().KlantFactory(currentTime);
					nieuweklant.binnenkomen(nieuweklant, currentTime);
					manager.overweegActies();
				}
				if(currentTime.getMinute() == 0) {
					System.out.println("Het is nu " + currentTime + " uur.");
				}
			} catch (Exception e) {
			}
		}
		
		System.out.println("De zaak sluit om "+currentTime+" uur. Tijd om de balans op te maken.\n");
	}

	void sluitingstijd() {
		System.out.println(
				"Het is sluitingstijd. Het café is gesloten, maar de klanten in de wachtrij mogen nog geholpen worden.");
	}
}