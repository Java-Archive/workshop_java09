package org.rapidpm.workshop.java09.jep266;


import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.stream.IntStream;

/**
 * Created by benjamin-bosch on 22.11.16.
 */
public class JEP266v002 {


  public static void main(String[] args) throws InterruptedException {

    try (SubmissionPublisher pup = new SubmissionPublisher<String>()) {

      pup.subscribe(new TestSubscriber("Sub1"));
      pup.subscribe(new TestSubscriber("Sub2"));

      IntStream.range(1, 1000)
              .forEach(i -> pup.submit(i + ""));

      System.out.println("published World");
      Thread.sleep(100 + pup.estimateMaximumLag());
    }
  }

  public static class TestSubscriber implements Flow.Subscriber<String> {

    private final String name;
    private Flow.Subscription subscription;

    public TestSubscriber(String name) {
      this.name = name;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
      this.subscription = subscription;
      subscription.request(2);
    }


    @Override
    public void onNext(String item) {
      subscription.request(2);
      System.out.println(String.format("%s says: Hello %s", name, item));
      System.out.flush();
    }

    @Override
    public void onError(Throwable throwable) {
      System.err.print(throwable);
    }

    @Override
    public void onComplete() {
      // nothing to do here
    }
  }

}
