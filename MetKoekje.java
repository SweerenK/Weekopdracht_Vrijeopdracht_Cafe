package weekopdracht_cafe;

public interface MetKoekje {
	default void voegKoekjeToe() {
		System.out.println("Er komt een koekje bij.");
	}
}
