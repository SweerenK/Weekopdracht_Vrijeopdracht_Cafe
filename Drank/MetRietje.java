package weekopdracht_cafe.Drank;

public interface MetRietje {
	default void voegRietjeToe() {
		System.out.println("Je stopt een rietje in het drankje.");
	}
}
