package weekopdracht_cafe.Drank;

public interface MetViltje {
	default void voegViltjeToe() {
		System.out.println("Je legt een viltje onder het drankje.");
	}
}
