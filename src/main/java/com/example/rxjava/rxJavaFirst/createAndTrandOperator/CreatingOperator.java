package com.example.rxjava.rxJavaFirst.createAndTrandOperator;

import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

public class CreatingOperator {
    public static void IntervalTest(){
        Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(
                        number -> System.out.println("숫자: " + number),
                        error -> System.err.println("에러: " + error),
                        () -> System.out.println("완료")
                );
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void RangeTest(){
        System.out.println("===RANGE ===");
        Observable.range(3, 5)
                .subscribe(
                        number -> System.out.println("숫자: " + number),
                        error -> System.err.println("에러: " + error),
                        () -> System.out.println("완료")
                );
    }


    public static void main(String[] args) {
        IntervalTest();
        RangeTest();
    }
}
