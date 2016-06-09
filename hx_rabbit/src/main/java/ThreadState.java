/**
 * Created by xing on 2016/6/9.
 */
public class ThreadState {
    public static void main(String[] args){
        Thread t1=new Thread(()->getString());
        t1.start();
        Thread t2=new Thread(()->getString());
        t2.start();
    }
    public static synchronized String getString(){
        System.out.println(Thread.currentThread().getName()+" get in");
        try {
            Thread.currentThread().sleep(2000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" get out");
        return "hello";
    }
}
