package com.demo.java8.behavior;

/**
 * 定义一个接口对选择参数建模
 * Predicate(谓词) 一个返回值为boolean类型的函数
 */
@FunctionalInterface
public interface ApplePredicate {
    boolean filter(Apple apple);
}