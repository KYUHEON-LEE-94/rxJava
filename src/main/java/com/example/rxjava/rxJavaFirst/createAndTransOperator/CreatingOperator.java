package com.example.rxjava.rxJavaFirst.createAndTransOperator;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

import java.util.concurrent.TimeUnit;

public class CreatingOperator {
    public static void IntervalTest(){
        Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(
                        number -> System.out.println("Interval 숫자: " + number),
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
                        number -> System.out.println("Range 숫자: " + number),
                        error -> System.err.println("에러: " + error),
                        () -> System.out.println("완료")
                );
    }

    public static void RepeatTest(){
        System.out.println("===RPEAT ===");
        // 1부터 5까지의 숫자를 발행하는 Observable 생성
        Observable<Integer> numbersObservable = Observable.range(1, 5);

        // Observable을 세 번 반복
        Disposable disposable = numbersObservable
                .repeat(3) // 세 번 반복
                .subscribe(
                        num -> System.out.println("Repeat 숫자: " + num),
                        error -> System.err.println("에러: " + error),
                        () -> System.out.println("완료")
                );

        // 잠시 기다렸다가 프로그램 종료
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Disposable 해제
        disposable.dispose();
    }


    public static void main(String[] args) {
        IntervalTest();
        RangeTest();
        RepeatTest();
    }
}
