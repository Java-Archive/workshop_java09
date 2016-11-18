package org.rapidpm.workshop.java09.jep259;

import java.util.Objects;

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
 * Created by RapidPM - Team on 17.11.16.
 */
public class JEP259V001 {

  public static void main(String[] args) {
    //StackWalker.Option.SHOW_REFLECT_FRAMES
    StackWalker.getInstance().forEach(System.out::println);

    new Holder(new Holder(new Holder(null) )).doWork();
    new Holder002(new Holder002(new Holder002(null) )).doWork();

  }

  public static class Holder {
    private Holder holder;

    public Holder(final Holder holder) {
      this.holder = holder;
    }
    public void doWork() {
      if (Objects.isNull(holder)) {
        System.out.println("end reached..");
        StackWalker.getInstance().forEach(System.out::println);
      } else {
        holder.doWork();
      }
    }
  }

  public static class Holder002 {
    private Holder002 holder;

    public Holder002(final Holder002 holder) {
      this.holder = holder;
    }
    public void doWork() {
      if (Objects.isNull(holder)) {
        System.out.println("end reached..");
        StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE)
            .forEach(stackFrame -> {
              final int byteCodeIndex = stackFrame.getByteCodeIndex();
              System.out.println("byteCodeIndex = " + byteCodeIndex);
              // byteCode Index :
              final String className = stackFrame.getClassName();
              System.out.println("className = " + className);
              final Class<?> declaringClass = stackFrame.getDeclaringClass();
              System.out.println("declaringClass = " + declaringClass);
              final String fileName = stackFrame.getFileName();
              System.out.println("fileName = " + fileName);
              final int lineNumber = stackFrame.getLineNumber();
              System.out.println("lineNumber = " + lineNumber);
              final String methodName = stackFrame.getMethodName();
              System.out.println("methodName = " + methodName);
            });
      } else {
        holder.doWork();
      }
    }
  }


}
