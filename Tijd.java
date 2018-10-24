package weekopdracht_cafe;

import java.time.LocalTime;

public class Tijd {

	public void vorderen(Cafe cafe) {
		LocalTime currentTime = cafe.getOpeningstijd();
		System.out.println("De zaak opent om " + currentTime + ". We wachten op de eerste klant.");
		int uurOffset = cafe.getSluitingstijd().getHour() + 1;
		while (currentTime.minusHours(uurOffset).isBefore(cafe.getSluitingstijd().minusHours(uurOffset))) {
			// System.out.println("De tijd is: " + currentTime);

			try {
				Thread.sleep(50);
				currentTime = currentTime.plusMinutes(1);
				if (Main.random.nextInt(100) > 95) {
					Klant nieuweklant = new KlantFactory().KlantFactory(currentTime);
					nieuweklant.binnenkomen(nieuweklant, currentTime);
					
					String test = Main.scanner.nextLine(); // vervangen door gebruikersActie
				}
			} catch (Exception e) {
			}
		}
		System.out.println("De zaak sluit om " + currentTime + " uur. Tijd om de balans op te maken.\n");
	}

	void sluitingstijd() {
		System.out.println("Het is sluitingstijd. Het café is gesloten, maar de klanten in de wachtrij mogen nog geholpen worden.");
	}
}