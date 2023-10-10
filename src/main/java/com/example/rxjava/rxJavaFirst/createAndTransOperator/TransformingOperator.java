package com.example.rxjava.rxJavaFirst.createAndTransOperator;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class TransformingOperator {
    static Function<Integer, String> mapper = integer -> (integer*10) + " A";

    public static void MapTest() {
        //array -> map을 사용
        Observable<Integer> integerObservable = Observable.fromArray(1, 2, 3);
        integerObservable
                .map(mapper)
                .subscribe(data -> System.out.println(data));
    }

    public static void FlatmapTest() {
        System.out.println("===FLAT ===");
        //순서를 보장하지 않음
        //just -> flatMap 사용
        Observable<Integer> numbersObservable = Observable.just(1, 2, 3);

        numbersObservable
                .flatMap(num -> {
                    return Observable.range(num, 3); // 현재 숫자부터 3개의 연속된 숫자를 발행하는 Observable 생성
                })
                .subscribe(
                        result -> System.out.println("발행된 숫자: " + result),
                        error -> System.err.println("에러: " + error),
                        () -> System.out.println("완료")
                );
    }

    public static void ConnectmapTest() {
        System.out.println("===Connect ===");
        //순서를 보장함
        Observable<String> source = Observable.just("One", "Two", "Three");

        source
                .concatMap(str -> {
                    int length = str.length();
                    return Observable.just(length);
                })
                .subscribe(
                        length -> System.out.println("Length: " + length),
                        error -> System.err.println("Error: " + error),
                        () -> System.out.println("Complete")
                );
    }

    public static void SwitchmapTest() {
        System.out.println("===SWITCH ===");
        //순서를 보장하기 위해 기존 작업을 바로 중단하는 특징
        Observable<String> source = Observable.just("One", "Two", "Three");

        source
                .switchMap(str -> {
                    // 문자열을 받아 지연 시간이 있는 Observable을 생성
                    return Observable
                            .just(str)
                            .delay(1000, TimeUnit.MILLISECONDS)
                            .subscribeOn(Schedulers.io());
                })
                .subscribe(
                        result -> System.out.println("Received: " + result),
                        error -> System.err.println("Error: " + error),
                        () -> System.out.println("Complete")
                );

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void BufferTest() {
        System.out.println("===BUFFER ===");
        //3개씩 번들로 묶어서 방출
        Observable.fromArray(1,2,3,4,5,6,7)
                .buffer(3)
                .subscribe(result -> System.out.println("buffer 숫자: " + result));

    }

    public static void ScanTest() {
        System.out.println("===SCAN ===");
        Observable.fromArray(1,2,3,4,5)
                .scan((t1,t2) -> t1+t2)
                .subscribe(result -> System.out.println("scan 숫자: " + result));
    }

    public static void groupByTest() {
        System.out.println("===GroupBy ===");
        Observable<Integer> numbersObservable = Observable.just(1, 2, 3, 4, 5);

        numbersObservable
                .groupBy(number -> number % 2 == 0 ? "짝수" : "홀수") // 짝수와 홀수로 그룹화
                .subscribe(groupedObservable -> {
                    groupedObservable
                            .subscribe(
                                    //위의 groupBy 결과가 반환된 observable의.getkey()가 됨
                                    number -> System.out.println(groupedObservable.getKey() + ": " + number),
                                    error -> System.err.println("에러: " + error),
                                    () -> System.out.println("그룹 " + groupedObservable.getKey() + " 완료")
                            );
                });
    }




    public static void main(String[] args) {
        MapTest();
        FlatmapTest();
        ConnectmapTest();
        SwitchmapTest();
        BufferTest();
        ScanTest();
        groupByTest();
    }

}
