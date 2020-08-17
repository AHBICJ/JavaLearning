package classloader;

public class ClassLoaderLearning {
    public static void main(String[] args) throws ClassNotFoundException {
        String str = "Hello Class Loader";
        System.out.println("str class loader == " + str.getClass().getClassLoader());

        Class<?> driver = Class.forName("java.sql.Driver");
        System.out.println("driver class loader == " + driver.getClassLoader());
        System.out.println("driver parent class loader == " + driver.getClassLoader().getParent());
        ClassLoaderLearning cll = new ClassLoaderLearning();
        System.out.println("cll class loader == " + cll.getClass().getClassLoader());
        System.out.println("cll parent class loader == " + cll.getClass().getClassLoader().getParent());
        System.out.println("cll parent.parent class loader == "
                + cll.getClass().getClassLoader().getParent().getParent());

        Class<?> jshell = Class.forName("jdk.jshell.JShell");
        System.out.println("jshell class loader == " + jshell.getClassLoader());

        MyClassLoader myClassLoader = new MyClassLoader("myClassLoader");
        Class<?> cls = myClassLoader.loadClass("classloader.MyClass");
        System.out.println("cls1 class loader == " + cls.getClassLoader());
        System.out.println(jshell.getClassLoader() == cll.getClass().getClassLoader());
    }
}
