# New Methods inside the Streams API

## Stream
What are the news from Java8 to Java9?

### default Stream<T> dropWhile(Predicate<? super T> predicate)

```java
  public static void main(String[] args) {
    workOn(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
    workOn(new Integer[]{9, 8, 7, 6, 5, 4, 3, 2, 1});
    workOn(new Integer[]{1, 9, 2, 8, 3, 7, 4, 6, 5});
    workOn(new Integer[]{9, 1, 8, 2, 7, 3, 6, 4, 5});
    workOnParallel(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
    workOnParallel(new Integer[]{9, 8, 7, 6, 5, 4, 3, 2, 1});
    workOnParallel(new Integer[]{1, 9, 2, 8, 3, 7, 4, 6, 5});
    workOnParallel(new Integer[]{9, 1, 8, 2, 7, 3, 6, 4, 5});
  }

  private static void workOn(final Integer[] integers) {
    final List<Integer> collect = Stream
        .of(integers)
        .dropWhile(integer -> integer > 5)
        .collect(Collectors.toList());
    System.out.println("dropWhile.isEmpty() = " + collect.isEmpty());
    System.out.println("dropWhile = " + collect);
  }
  private static void workOnParallel(final Integer[] integers) {
    final List<Integer> collect = Stream
        .of(integers)
        .parallel()
        .dropWhile(integer -> integer > 5)
        .collect(Collectors.toList());
    System.out.println("dropWhileParallel.isEmpty() = " + collect.isEmpty());
    System.out.println("dropWhileParallel = " + collect);
  }
```

The result will be.

```text
dropWhile.isEmpty() = false
dropWhile = [1, 2, 3, 4, 5, 6, 7, 8, 9]
dropWhile.isEmpty() = false
dropWhile = [5, 4, 3, 2, 1]
dropWhile.isEmpty() = false
dropWhile = [1, 9, 2, 8, 3, 7, 4, 6, 5]
dropWhile.isEmpty() = false
dropWhile = [1, 8, 2, 7, 3, 6, 4, 5]
dropWhileParallel.isEmpty() = false
dropWhileParallel = [1, 2, 3, 4, 5, 6, 7, 8, 9]
dropWhileParallel.isEmpty() = false
dropWhileParallel = [5, 4, 3, 2, 1]
dropWhileParallel.isEmpty() = false
dropWhileParallel = [1, 9, 2, 8, 3, 7, 4, 6, 5]
dropWhileParallel.isEmpty() = false
dropWhileParallel = [1, 8, 2, 7, 3, 6, 4, 5]
```

### default Stream<T> takeWhile(Predicate<? super T> predicate)

```java
  public static void main(String[] args) {
    workOn(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
    workOn(new Integer[]{9, 8, 7, 6, 5, 4, 3, 2, 1});
    workOn(new Integer[]{1, 9, 2, 8, 3, 7, 4, 6, 5});
    workOn(new Integer[]{9, 1, 8, 2, 7, 3, 6, 4, 5});
    workOnParallel(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
    workOnParallel(new Integer[]{9, 8, 7, 6, 5, 4, 3, 2, 1});
    workOnParallel(new Integer[]{1, 9, 2, 8, 3, 7, 4, 6, 5});
    workOnParallel(new Integer[]{9, 1, 8, 2, 7, 3, 6, 4, 5});
  }

  private static void workOn(final Integer[] integers) {
    final List<Integer> collect = Stream
        .of(integers)
        .takeWhile(integer -> integer > 5)
        .collect(Collectors.toList());
    System.out.println("takeWhile.isEmpty() = " + collect.isEmpty());
    System.out.println("takeWhile = " + collect);
  }
  private static void workOnParallel(final Integer[] integers) {
    final List<Integer> collect = Stream
        .of(integers)
        .parallel()
        .takeWhile(integer -> integer > 5)
        .collect(Collectors.toList());
    System.out.println("takeWhileParallel.isEmpty() = " + collect.isEmpty());
    System.out.println("takeWhileParallel = " + collect);
  }
```

```text
takeWhile.isEmpty() = true
takeWhile = []
takeWhile.isEmpty() = false
takeWhile = [9, 8, 7, 6]
takeWhile.isEmpty() = true
takeWhile = []
takeWhile.isEmpty() = false
takeWhile = [9]
takeWhileParallel.isEmpty() = true
takeWhileParallel = []
takeWhileParallel.isEmpty() = false
takeWhileParallel = [9, 8, 7, 6]
takeWhileParallel.isEmpty() = true
takeWhileParallel = []
takeWhileParallel.isEmpty() = false
takeWhileParallel = [9]
```



### public static<T> Stream<T> ofNullable(T t)

### public static<T> Stream<T> iterate(T seed, Predicate<? super T> hasNext, UnaryOperator<T> next)

## Collectors

### public static <T, U, A, R> Collector<T, ?, R> flatMapping(Function<? super T, ? extends Stream<? extends U>> mapper,Collector<? super U, A, R> downstream)
                                       
### public static <T, A, R> Collector<T, ?, R> filtering(Predicate<? super T> predicate, Collector<? super T, A, R> downstream)

## IntStream
same as Stream
### public static IntStream iterate(int seed, IntPredicate hasNext, IntUnaryOperator next)

