import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-1-12
 * Time: 下午9:59
 * To change this template use File | Settings | File Templates.
 */
public class FutureTaskDemo {
    public static void main(String[] args) {
        Callable privateAccount = new PrivateAccount();
        FutureTask futureTask = new FutureTask(privateAccount);

        Thread thread = new Thread(futureTask);
        System.out.println("future task started here...");
        thread.start();
        System.out.println("main thread do sth else here...");
        System.out.println("main thread got money 1000");
        int privateMoney = 1000;
        System.out.println("main thread wait for future task");
        while (!futureTask.isDone()){
            try{
                Thread.sleep(1000);
            } catch (Exception e) {

            }

        }
        System.out.println("future task end here " + System.nanoTime());
        Integer totalMoney = null;
        try {
            totalMoney = (Integer) futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ExecutionException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        System.out.println("Get total money " + totalMoney + ", " + System.nanoTime());

    }

    static class PrivateAccount implements Callable {
        Integer totalMoney;
        @Override
        public Object call() throws Exception {
            Thread.sleep(1000);
            totalMoney = 2000;
            System.out.println("future task run and end at " + System.nanoTime());
            return totalMoney;  //To change body of implemented methods use File | Settings | File Templates.
        }


    }
}
