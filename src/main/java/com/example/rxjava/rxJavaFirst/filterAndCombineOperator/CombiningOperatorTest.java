package com.example.rxjava.rxJavaFirst.filterAndCombineOperator;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Function3;

public class CombiningOperatorTest {

    public static void MergeTest() {
        System.out.println("=== Merge ===");

        // 두 개의 Observable을 생성합니다.
        Observable<String> observable1 = Observable.just("A", "B", "C");
        Observable<String> observable2 = Observable.just("1", "2", "3");

        // merge 연산자를 사용하여 두 Observable을 병합합니다.
        Observable<String> mergedObservable = Observable.merge(observable1, observable2);

        // 구독을 시작합니다.
        mergedObservable.subscribe(
                item -> System.out.println("Received: " + item),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed")
        );
    }

    static BiFunction<String, Integer, String> combineFunction = (str, num) -> str + num;

    public static void ZipTest() {
        System.out.println("=== Zip ===");

        // 두 개의 Observable을 생성합니다.
        Observable<String> observable1 = Observable.just("A", "B", "C");
        Observable<Integer> observable2 = Observable.just(1, 2, 3);

        // merge 연산자를 사용하여 두 Observable을 병합합니다.
        Observable<String> mergedObservable = Observable.zip(observable1, observable2, combineFunction);

        // 구독을 시작합니다.
        mergedObservable.subscribe(
                item -> System.out.println("Received: " + item),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed")
        );
    }

    static Function3<String, Integer, Double, String> combineFunction3 = (str, num, dbl) -> str + num + dbl;


    public static void CombineLatestTest() {
        System.out.println("=== CombineLatest ===");

        Observable<String> source1 = Observable.just("A", "B", "C");
        Observable<Integer> source2 = Observable.just(1, 2, 3);
        Observable<Double> source3 = Observable.just(0.1, 0.2, 0.3);

        // combineLatest 연산자를 사용하여 최근 값들을 결합합니다.
        Observable<String> combinedObservable = Observable.combineLatest(
                source1,
                source2,
                source3,
                combineFunction3
        );

        // 구독을 시작합니다.
        combinedObservable.subscribe(
                item -> System.out.println("Received: " + item),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed")
        );
    }

    public static void main(String[] args) {
        MergeTest();
        ZipTest();
        CombineLatestTest();
    }
}
