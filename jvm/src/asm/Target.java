package asm;

public class Target {
    public void say(){
        System.out.println("now in say");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/*
* java -cp .;C:\Users\Sun\.m2\repository\org\ow2\asm\asm\8.0.1\asm-8.0.1.jar;C:\Users\Sun\.
m2\repository\org\ow2\asm\asm-util\8.0.1\asm-util-8.0.1.jar;out\production\jvm\asm\ org.objectweb.asm.util.ASMifier Targe
t
* */
