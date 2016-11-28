package org.rapidpm.workshop.java09.jep266;


import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Copyright (C) 2010 RapidPM
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Created by RapidPM - Team on 23.11.16.
 */
public class JEP266v006 {


  public static void main(String[] args) throws InterruptedException {


    try (SubmissionPublisher pup = new SubmissionPublisher<Integer>()) {

      TestProcessor processor = new TestProcessor(0);
      TestProcessor processor2 = new TestProcessor(1);
      pup.subscribe(processor);
      pup.subscribe(processor2);

      TestSubscriber testAll = new TestSubscriber("testAll");
      TestSubscriber testEven = new TestSubscriber("testEven");
      TestSubscriber testOdd = new TestSubscriber("testOdd");

      processor.subscribe(testAll);
      processor2.subscribe(testAll);

      processor.subscribe(testEven);

      processor2.consume(integer -> {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

      });


      IntStream.range(1, 10000)
              .forEach(i -> pup.offer(i, 10, TimeUnit.MILLISECONDS, (o, o2) -> {
                System.out.println(String.format("Subscirber %s rejected %s", o, o2));
                return false;
              }));

      System.out.println("published World");
      Thread.sleep(1000 + pup.estimateMaximumLag());
    }
  }


  public static class TestProcessor extends SubmissionPublisher<Integer> implements Flow.Processor<Integer, Integer> {

    private Flow.Subscription subscription;
    private final int mod;

    public TestProcessor(int mod) {
      this.mod = mod;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
      this.subscription = subscription;
      this.subscription.request(10);
    }

    @Override
    public void onNext(Integer item) {
      subscription.request(10);
      if (item % 2 == mod) submit(item);
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }
  }

  public static class TestSubscriber implements Flow.Subscriber<Integer> {


    private final String name;

    public TestSubscriber(String name) {
      this.name = name;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
      subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(Integer item) {

      System.out.println(String.format("%s says: Hello %s", name, item));
      //System.out.flush();
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
