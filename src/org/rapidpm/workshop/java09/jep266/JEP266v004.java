package org.rapidpm.workshop.java09.jep266;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
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
public class JEP266v004 {


  public static void main(String[] args) throws InterruptedException {

    ExecutorService executor = Executors.newFixedThreadPool(10);
    try (SubmissionPublisher pup = new SubmissionPublisher<Integer>(executor, 10)) {

      TestProcessor processor = new TestProcessor();
      pup.subscribe(processor);
      //pup.subscribe(processor);
      processor.subscribe(processor);

      processor.consume(System.out::println);

      IntStream.range(1, 10)
          .forEach(pup::submit);

      System.out.println("published World");
      Thread.sleep(1000 + pup.estimateMaximumLag());
    } finally {
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
