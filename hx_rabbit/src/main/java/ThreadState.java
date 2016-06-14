/**
 * Created by xing on 2016/6/9.
 */
public class ThreadState {
    public static volatile int i=0;
    public static void main(String[] args){
        Thread t1=new Thread(new Runnable(){
            @Override
            public void run() {
                int j=0;
                while(j<1000) {
                    Thread.yield();
                    i++;
                    j++;
                }
            }
        });
        t1.start();
        Thread t2=new Thread(new Runnable(){
            @Override
            public void run() {
                int j=0;
                while(j<1000) {
                    i++;
                    j++;
                    Thread.yield();
                }
            }
        });
        t2.start();
        Thread t3=new Thread(new Runnable(){
            @Override
            public void run() {
                int j=0;
                while(j<1000) {
                    i++;
                    Thread.yield();
                    j++;
                }
            }
        });
        t3.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(i);
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
