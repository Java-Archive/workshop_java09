package org.rapidpm.workshop.java09.jep264.v001.app;


import java.time.LocalDateTime;
import java.util.function.Supplier;

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
public class JEP264V001 {

  public static void main(String[] args) {


    final System.Logger logger = System.getLogger("LoggerName");
    System.out.println(logger.getName());
    System.out.println("logger = " + logger);

    final boolean loggable = logger.isLoggable(System.Logger.Level.DEBUG);
    System.out.println("loggable = " + loggable);

    logger.log(System.Logger.Level.DEBUG, () -> "logmessage");



    //log something
    if(logger.isLoggable(System.Logger.Level.DEBUG))
      logger.log(System.Logger.Level.DEBUG, "here " + "are " + "expensive " + "ops " + methodToShowThePrice());

    // Logger not working on this level
    if(logger.isLoggable(System.Logger.Level.TRACE))
      logger.log(System.Logger.Level.TRACE, "here " + "are " + "expensive " + "ops " + methodToShowThePrice());




    logger.log(System.Logger.Level.TRACE, ()-> "here " + "are " + "expensive " + "ops " + methodToShowThePrice());
    logger.log(System.Logger.Level.TRACE, logMessage);

  }


  private static Supplier<String> logMessage = ()-> "here " + "are " + "expensive " + "ops " + methodToShowThePrice();

  private static String methodToShowThePrice() {
    System.out.println(" will create instance now.....");
    return LocalDateTime.now().toString();
  }


}
