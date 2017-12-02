# iDontCare
a java agent that turns a java.lang.NullPointerException in what it really means.

Test with:
> java -javaagent:iDontCare-0.1.jar -jar iDontCare-0.1.jar

And you will see:

Exception in thread "main" rocks.nullpointer.IDontCareException: Meh. I don't care about breaking your code by null.
        at Main.main(Main.java:3)
