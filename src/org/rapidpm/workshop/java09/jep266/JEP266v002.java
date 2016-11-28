package org.rapidpm.workshop.java09.jep266;


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
public class JEP266v002 {


  public static void main(String[] args) throws InterruptedException {

    try (SubmissionPublisher pup = new SubmissionPublisher<String>()) {

      pup.subscribe(new GreetingSubscriber("Sub1"));
      pup.subscribe(new GreetingSubscriber("Sub2"));

      IntStream.range(1, 10)
              .boxed()
              .map(String::valueOf)
              .forEach(pup::submit);

      System.out.println("published all the numbers");
      Thread.sleep(100 + pup.estimateMaximumLag());
    }
  }

  public static class GreetingSubscriber implements Flow.Subscriber<String> {

    private final String name;
    private Flow.Subscription subscription;

    public GreetingSubscriber(String name) {
      this.name = name;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
      this.subscription = subscription;
      subscription.request(1);
    }


    @Override
    public void onNext(String item) {
      subscription.request(1);
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
