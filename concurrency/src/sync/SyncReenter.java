package sync;

class superClass{
    public synchronized void doSomething(){
        System.out.println("do something in super");
        System.out.println(this);
    }
}

public class SyncReenter extends superClass{
    @Override
    public synchronized void doSomething() {
        System.out.println("do something in child");
        System.out.println(this);
        super.doSomething();
    }

    public static void main(String[] args) {
        new SyncReenter().doSomething();
    }
}
