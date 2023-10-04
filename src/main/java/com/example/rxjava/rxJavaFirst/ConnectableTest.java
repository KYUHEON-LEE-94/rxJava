package com.example.rxjava.rxJavaFirst;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.ConnectableObservable;

import java.util.concurrent.TimeUnit;

public class ConnectableTest {
    public static void ConnectableObservable() throws InterruptedException {
        String[] dt = {"RED", "GREEN", "BLUE"};
        Observable<String> balls = Observable.interval(100L, TimeUnit.MILLISECONDS)
                .map(Long::intValue)
                .map(i -> dt[i])
                .take(dt.length);
        ConnectableObservable<String> source = balls.publish();
        source.subscribe(data -> System.out.println("Subscriber #1 => " + data));
        source.subscribe(data -> System.out.println("Subscriber #2 => " + data));
        source.connect(); //connect되야지만 데이터를 발행해줌

        Thread.sleep(250);
        source.subscribe(data -> System.out.println("Subscriber #3 => " + data));
        Thread.sleep(100);
    }
    
    public static void main(String[] args) throws InterruptedException {
        ConnectableObservable();
    }

    

}
