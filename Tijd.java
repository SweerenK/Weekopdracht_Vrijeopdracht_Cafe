package weekopdracht_cafe;

import java.time.LocalTime;

public class Tijd {
	
	void vorderen(Cafe cafe) {
		LocalTime currentTime = cafe.getOpeningstijd();
		
		System.out.println("De tijd loopt vanaf " + currentTime + ". Even geduld a.u.b.");
		while (currentTime.minusHours(3).isBefore(cafe.getSluitingstijd().minusHours(3))) {
			//System.out.println("De tijd is: " + currentTime);
			currentTime = currentTime.plusMinutes(5);
			
			try {
				Thread.sleep(300);
				if (Main.random.nextInt(100) > 80) {
					System.out.println("Er komt een klant binnen om " + currentTime + " uur.");
					
					Klant nieuweklant = new KlantFactory().KlantFactory();
					System.out.println(nieuweklant);
					System.out.println(nieuweklant.naam + " (" + nieuweklant.leeftijd + " jaar, geduldigheid: " + nieuweklant.geduldigheid + ") komt binnen.");
					Cafe.klantenlijst.add(nieuweklant);
					String test = Main.scanner.nextLine();															//vervangen door gebruikersActie
					currentTime = currentTime.plusMinutes(5);
					System.out.println("Het is nu " + currentTime + ". Wachten op de volgende klant.");
				}
			} catch (Exception e) {

			}
		}
	}
}
