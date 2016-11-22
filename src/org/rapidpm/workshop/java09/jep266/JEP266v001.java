package org.rapidpm.workshop.java09.jep266;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.SubmissionPublisher;
import java.util.stream.IntStream;

/**
 * Created by benjamin-bosch on 22.11.16.
 */
public class JEP266v001 {


  public static void main(String[] args) throws InterruptedException, ExecutionException {
    CompletableFuture consume = null;
    try (SubmissionPublisher pup = new SubmissionPublisher<String>()) {
      consume = pup.consume(System.out::println);
      IntStream.range(1, 10).forEach(pup::submit);
    } finally {
      consume.get();
    }

  }


}
