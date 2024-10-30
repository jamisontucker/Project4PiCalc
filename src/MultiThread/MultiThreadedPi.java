package MultiThread;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MultiThreadedPi {
    private static final long totalPoints = 1_000_000;

    private final static int THREADS = 4;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Future<Long>> futureList = new ArrayList<>();
        ExecutorService es = Executors.newFixedThreadPool(THREADS);
        Instant start = Instant.now();
        long i = totalPoints;
        for(int j=0; j<THREADS; i=totalPoints/THREADS*j,j++){
            Callable<Long> task = new MultiThreadTask(i, totalPoints/THREADS*j);
            Future<Long> result = es.submit(task);
            futureList.add(result);
        }

        Callable<Long> lastTask = new MultiThreadTask(i, totalPoints);
        Future<Long> lastResult = es.submit(lastTask);
        futureList.add(lastResult);
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        es.shutdown();

        long sum=0;
        for(Future<Long> f : futureList){
            sum = sum + f.get();
        }
        System.out.println("pi=" + sum);
        System.out.println("runtime=" + timeElapsed);
    }
}
