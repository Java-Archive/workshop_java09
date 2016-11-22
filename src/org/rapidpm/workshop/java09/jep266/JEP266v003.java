package org.rapidpm.workshop.java09.jep266;


import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.stream.IntStream;

/**
 * Created by benjamin-bosch on 22.11.16.
 */
public class JEP266v003 {


  public static void main(String[] args) throws InterruptedException {

    try (SubmissionPublisher pup = new SubmissionPublisher<Integer>()) {

      TestProcessor processor = new TestProcessor();
      pup.subscribe(processor);
      processor.subscribe(new TestSubscriber("Sub1"));
      processor.consume(System.out::println);

      IntStream.range(1, 10)
              .forEach(pup::submit);

      System.out.println("published World");
      Thread.sleep(100 + pup.estimateMaximumLag());
    }
  }

  public static class TestProcessor extends SubmissionPublisher<String> implements Flow.Processor<Integer, String> {

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
      subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(Integer item) {
      this.submit(String.valueOf(item));
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }
  }

  public static class TestSubscriber implements Flow.Subscriber<String> {

    private final String name;

    public TestSubscriber(String name) {
      this.name = name;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
      subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(String item) {
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
