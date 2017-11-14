package rocks.nullpointer;

public class IDontCareException extends NullPointerException {

    public static final String MSG_I_DONT_CARE = "Meh. I don't care about breaking your code by null.";

    /**
     * Constructs a {@code IDontCareException} with no detail message.
     */
    public IDontCareException() {
        super(MSG_I_DONT_CARE);
    }

    /**
     * Constructs a {@code IDontCareException} with the specified
     * detail message.
     *
     * @param   s   the detail message.
     */
    public IDontCareException(String s) {
        super(MSG_I_DONT_CARE + ' ' + s);
    }
}
