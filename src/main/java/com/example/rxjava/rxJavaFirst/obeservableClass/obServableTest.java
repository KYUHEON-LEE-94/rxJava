package com.example.rxjava.rxJavaFirst.obeservableClass;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.disposables.Disposable;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class obServableTest {
    public void emit(){
        //인자 값을 순서대로 발행
        Observable.just("Hello", "RXJava2").subscribe(System.out::println);
    }
    
    public void unSubscribe(){
        System.out.println("====unSubscribe====");
        Observable<String> source = Observable.just("RED", "GREEN", "YELLOW");

        Disposable disposable = source.subscribe(
                v -> System.out.println("onNext() : value : " + v),
                err -> System.err.println("onError() : err : " + err.getMessage()),
                () -> System.out.println("onComplete()")
        );

        System.out.println("isDisposed() : " + disposable.isDisposed());
    }

    public void create(){
        System.out.println("====create====");
        Observable<Integer> source = Observable.create(
                (ObservableEmitter<Integer> emitter) ->{
                    emitter.onNext(100);
                    emitter.onNext(200);
                    emitter.onNext(300);
                    emitter.onComplete();
                }
        );
        source.subscribe(System.out::println);
    }

    public void fromArray(){
        System.out.println("====fromArray====");
        Integer[] arr = {100, 200, 300};
        //인자값 한번에 발행
        Observable<Integer> source = Observable.fromArray(arr);
        source.subscribe(System.out::println);
    }

    public void callable() throws InterruptedException {
        System.out.println("====callable====");
        Callable<String> callable = () ->{
            Thread.sleep(1000);
            return "HELLO Callable";
        };

        Observable<String> source = Observable.fromCallable(callable);
        source.subscribe(System.out::println);
    }

    public void fromFuture() throws InterruptedException {
        System.out.println("====fromFuture====");
        Future<String> future = Executors.newSingleThreadExecutor().submit(() ->{
            Thread.sleep(1000);
            return "Hello Future";
        });

        Observable<String> source = Observable.fromFuture(future);
        source.subscribe(System.out::println);
    }

    public static void main(String[] args) throws InterruptedException {
        obServableTest demo = new obServableTest();
        demo.emit();

        demo.unSubscribe();
        demo.create();
        demo.fromArray();
        demo.callable();
        demo.fromFuture();
    }
}
