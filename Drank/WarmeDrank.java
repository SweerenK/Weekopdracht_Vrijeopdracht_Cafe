package weekopdracht_cafe.Drank;
//V2R0

public interface WarmeDrank {
	
	void opwarmen();
	default void roeren() {
		System.out.println("Het drankje wordt geroerd.");
	}
}
