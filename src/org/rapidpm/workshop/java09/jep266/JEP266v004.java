package org.rapidpm.workshop.java09.jep266;


import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.stream.IntStream;

/**
 * Created by benjamin-bosch on 22.11.16.
 */
public class JEP266v004 {


  public static void main(String[] args) throws InterruptedException {

    ExecutorService executor = Executors.newFixedThreadPool(10);
    try (SubmissionPublisher pup = new SubmissionPublisher<Integer>(executor,10)) {

      TestProcessor processor = new TestProcessor();
      pup.subscribe(processor);
      //pup.subscribe(processor);
      processor.subscribe(processor);

      processor.consume(System.out::println);

      IntStream.range(1, 10)
              .forEach(i -> pup.submit(i));

      System.out.println("published World");
      Thread.sleep(1000 + pup.estimateMaximumLag());
    }
    finally {
      executor.shutdown();
    }
  }



  public static class TestProcessor extends SubmissionPublisher<Integer> implements Flow.Processor<Integer, Integer> {

    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
      this.subscription = subscription;
      this.subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(Integer item) {
      //this.subscription.request(1);
      submit(item);
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }
  }
}
