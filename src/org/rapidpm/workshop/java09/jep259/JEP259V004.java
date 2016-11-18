package org.rapidpm.workshop.java09.jep259;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

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
 * Created by RapidPM - Team on 18.11.16.
 */
public class JEP259V004 {

  public static void main(String[] args) {
    new Holder001(new Holder001(new Holder001(null))).doWork();
  }

  public static class Holder001 {
    private Holder001 holder;

    public Holder001(final Holder001 holder) {
      this.holder = holder;
    }

    public void doWork() {
      if (Objects.isNull(holder)) {
        System.out.println("end reached..");
        final StackTraceElement[] stackTraceElements = StackWalker
            .getInstance()
            .walk(sfStream -> sfStream
                .takeWhile(frame -> frame.getClassName().startsWith("org.rapidpm"))
                .collect(Collectors.toList()))
            .stream()
            .map(StackWalker.StackFrame::toStackTraceElement)
            .toArray(StackTraceElement[]::new);

        try {
          final RuntimeException runtimeException = new RuntimeException();
          runtimeException.setStackTrace(stackTraceElements);
          throw runtimeException;
        } catch (Exception e) {
          e.printStackTrace();
        }
      } else {
        holder.doWork();
      }
    }
  }

}
