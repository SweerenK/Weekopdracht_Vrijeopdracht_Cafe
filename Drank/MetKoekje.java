package weekopdracht_cafe.Drank;

public interface MetKoekje {
	default void voegKoekjeToe() {
		System.out.println("Je legt een koekje bij het drankje.");
	}
}
