import javassist.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class NPETransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer)
            throws IllegalClassFormatException {

        return catchNPE(classfileBuffer);
    }

    private byte[] catchNPE(byte[] bytecode) {
        try {
            ClassPool cPool = ClassPool.getDefault();
            CtClass ctClass = cPool.makeClass(new ByteArrayInputStream(bytecode));
            CtMethod[] ctClassMethods = ctClass.getDeclaredMethods();
            for (CtMethod ctClassMethod : ctClassMethods) {
                CtClass etype = ClassPool.getDefault().get("java.lang.NullPointerException");
                ctClassMethod.addCatch("{ throw new rocks.nullpointer.IDontCareException((null==$e.getMessage())?\"\":$e.getMessage()); }", etype);
            }
            bytecode = ctClass.toBytecode();
        } catch (IOException e) {
            // SILENT DEATH
        } catch (CannotCompileException e) {
            //SILENT DEATH
        } catch (NotFoundException e) {
            // SILENT DEATH
        } finally {
            return bytecode;
        }
    }
}