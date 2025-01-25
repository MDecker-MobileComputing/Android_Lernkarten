package de.eldecker.room.lernkarten.helferlein;


/**
 * Sammlung von globalen Konstanten für die ganze App.
 */
public interface IGlobaleKonstanten {

    /** Tag für Log-Nachrichte, die auf den Logger geschrieben werden. */
    public static final String TAG4LOGGING = "Lernkarten";

    /**
     * Schlüsselwert für Extra in Intent, der Lernmodus festlegt, also einen Wert aus
     * {@link LernModusEnum}. Zum Transport vom Enum-Werten als Extra-Wert siehe
     * <a href="https://stackoverflow.com/a/9753178">diese StackOverflow-Antwort.</a>
     */
    public static final String EXTRA_KEY_LERN_MODUS = "extra-key-lernmodus";

}