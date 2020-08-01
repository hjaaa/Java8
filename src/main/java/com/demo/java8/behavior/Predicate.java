package com.demo.java8.behavior;

/**
 * 带泛型的谓词，同{@link java.util.function.Predicate}
 *
 * @param <T>
 */
@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
}
