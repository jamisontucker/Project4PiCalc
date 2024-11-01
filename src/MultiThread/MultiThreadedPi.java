package MultiThread;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MultiThreadedPi {
    private static final long totalPoints = 1_000_000;

    private final static int THREADS = 4;

    public static void main(String[] args) throws Exception {
        Instant start = Instant.now();
        List<Future<Long>> futureList = new ArrayList<>();
        ExecutorService es = Executors.newFixedThreadPool(THREADS);
        long i = totalPoints;
        for(int j=0; j<THREADS; j++){
            Callable<Long> task = new MultiThreadTask(totalPoints/THREADS);
            Future<Long> result = es.submit(task);
            futureList.add(result);
        }


        es.shutdown();
        long sum=0;
        for(Future<Long> f : futureList){
            sum = sum + f.get();
        }
        double pi = sum / (double) totalPoints * 4;
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        System.out.println("pi=" + pi);
        System.out.println("runtime=" + timeElapsed);
    }
}
