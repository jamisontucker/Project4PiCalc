package MultiThread;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

public class MultiThreadTask implements Callable<Long> {
    private long totalPoints;

    public MultiThreadTask(long totalPoints){
        this.totalPoints = totalPoints;

    }

    public Long call(){
        long pointsInCircle = 0;
        for (long i = 0; i < totalPoints; i++) {
            double x = ThreadLocalRandom.current().nextDouble(0, 2);
            double y = ThreadLocalRandom.current().nextDouble(0, 2);
            double distance = Math.sqrt((x - 1) * (x - 1) + (y - 1) * (y - 1));
            if (distance <= 1) {
                pointsInCircle++;
            }
        }return pointsInCircle;

    }

}
