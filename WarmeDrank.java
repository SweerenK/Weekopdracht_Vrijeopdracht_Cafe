package weekopdracht_cafe;

import java.time.LocalTime;

public interface WarmeDrank {
	LocalTime opwarmtijd = LocalTime.of(0,0,0);
	
	void opwarmen();
	default void roeren() {
		System.out.println("Het drankje wordt geroerd.");
	}
}
