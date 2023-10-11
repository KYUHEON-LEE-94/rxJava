package com.example.rxjava.rxJavaFirst.filterAndCombineOperator;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class FilterOperatorTest {

    public static void FilterTest() {
        //array -> map을 사용
        Observable<Integer> integerObservable = Observable.fromArray(1, 2, 3,4,5);
        integerObservable
                .filter(x ->x%2 ==0)
                .subscribe(data -> System.out.println(data));
    }

    public static void FirstTest() {
        System.out.println("===First ===");
        //처음인 값만 출력
        Single<Integer> first = Observable.fromArray(1, 2, 3, 4, 5).first(-1); //없으면 1 return
        first.subscribe(data -> System.out.println(data));

    }

    public static void main(String[] args) {
        FilterTest();
        FirstTest();
    }
}
