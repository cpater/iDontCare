import java.lang.instrument.Instrumentation;

public class Agent {
    public static void premain(String args, Instrumentation instrumentation){
        NPETransformer transformer = new NPETransformer();
        instrumentation.addTransformer(transformer);
    }
}