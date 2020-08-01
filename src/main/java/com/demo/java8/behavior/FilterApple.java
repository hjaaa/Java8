package com.demo.java8.behavior;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilterApple {

    /**
     * 定义一个绿色的，重量超过150g苹果的谓词
     */
    public static class AppleGreenColorAndHeavyPredicate implements ApplePredicate {
        @Override
        public boolean filter(Apple apple) {
            return "green".equals(apple.getColor()) && apple.getWeight() > 150;
        }
    }

    /**
     * 从库存苹果中筛选出所有的绿苹果
     *
     * @param inventory 库存苹果
     * @return
     */
    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> greenApples = new ArrayList<>();
        for (Apple apple : inventory) {
            if ("green".equals(apple.getColor())) {
                greenApples.add(apple);
            }
        }
        return greenApples;
    }

    /**
     * 从库存苹果中筛选出所有的特定颜色的苹果
     *
     * @param inventory 库存苹果
     * @param color     苹果颜色
     * @return
     */
    public static List<Apple> filterApplesByColor(List<Apple> inventory, String color) {
        List<Apple> apples = new ArrayList<>();
        for (Apple apple : inventory) {
            if (color.equals(apple.getColor())) {
                apples.add(apple);
            }
        }
        return apples;
    }

    /**
     * 筛选出符合条件的苹果
     *
     * @param inventory 库存苹果
     * @param p         筛选谓词
     * @return
     */
    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
        List<Apple> apples = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.filter(apple)) {
                apples.add(apple);
            }
        }
        return apples;
    }

    /**
     * 筛选出符合条件的元素
     *
     * @param list 元素集合
     * @param p    筛选谓词
     * @param <T>
     * @return
     */
    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (p.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    /**
     * 筛选出绿色苹果 {@link FilterApple#filterGreenApples(List)}
     * 筛选出特定颜色的苹果 {@link FilterApple#filterApplesByColor(List, String)}
     * 筛选出符合条件的苹果{@link FilterApple#filterApples(List, ApplePredicate)}
     * 通过定义一个谓词接口，实现了行为参数化，使得迭代的行为和迭代的逻辑分离开，
     * 这样可以重复使用一个方法，给它不同的行为来达到不同的目的
     * 筛选出符合条件的元素{@link FilterApple#filter(List, Predicate)}
     *
     * @param args
     */
    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(new Apple("green", 150), new Apple("red", 120), new Apple("green", 170));
//        List<Apple> greenApples = filterGreenApples(apples);
//        assert greenApples.size() == 2;
//        List<Apple> greenApples = filterApplesByColor(apples, "green");
//        System.out.println(greenApples);
//        List<Apple> redApples = filterApplesByColor(apples, "red");
//        System.out.println(redApples);
        List<Apple> greenAndHeavyApples = filterApples(apples, new AppleGreenColorAndHeavyPredicate());
        System.out.println(greenAndHeavyApples);

        //匿名内部类实现一个谓词
        List<Apple> redApples = filterApples(apples, new ApplePredicate() {
            @Override
            public boolean filter(Apple apple) {
                return "red".equals(apple.getColor());
            }
        });
        System.out.println(redApples);

        //Lambda
        List<Apple> redApplesOfLambda = filterApples(apples, (Apple apple) -> "red".equals(apple.getColor()));
        System.out.println(redApplesOfLambda);


        //通用集合筛选
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> evenNumbers = filter(numbers, i -> i % 2 == 0);
        System.out.println(evenNumbers);

    }
}
