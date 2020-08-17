package asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

public class MyClassVisitor extends ClassVisitor {
    public MyClassVisitor(ClassVisitor classVisitor){
        super(Opcodes.ASM8, classVisitor);

    }
}
