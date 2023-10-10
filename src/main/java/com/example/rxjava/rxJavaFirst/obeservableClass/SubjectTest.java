package com.example.rxjava.rxJavaFirst.obeservableClass;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.subjects.AsyncSubject;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.ReplaySubject;

public class SubjectTest {
    public static void AsyncSubject(){
        //AsyncSubject는 onComplete 기준으로 제일 마지막 데이터만 관심 있다.
        AsyncSubject<String> subject = AsyncSubject.create();
        subject.subscribe(data -> System.out.println("Subscriber #1 => " + data));
        subject.onNext("RED");
        subject.onNext("GREEN");
        subject.subscribe(data -> System.out.println("Subscriber #2 =>"+data));
        subject.onNext("BLUE");
        subject.onComplete();
        //그전의 여부는 상관없이 제일 마지막 정보만 전달받음.
    }

    public static void AsyncSubjectByObeserVable(){
        Float[] temperature = {10.1f, 13.4f, 12.5f};
        Observable<Float> source = Observable.fromArray(temperature);

        AsyncSubject<Float> subject = AsyncSubject.create();
        subject.subscribe(data -> System.out.println("Subscriber #1 => " + data));

        source.subscribe(subject);
        //그전의 여부는 상관없이 제일 마지막 정보만 전달받음.
    }

    public static void beHaviorSubject(){
        System.out.println("==beHaviorSubject==");
        BehaviorSubject<String> subject = BehaviorSubject.createDefault("Pink");
        subject.subscribe(data -> System.out.println("Subscriber #1 =>" + data));
        subject.onNext("RED");
        subject.onNext("GREEN");
        subject.subscribe(data -> System.out.println("Subscriber #2 =>" + data));
        subject.onNext("BLUE");
        subject.onComplete();
        //1번 구독자는 처음부터 구독했기 때문에 모든 색을 전달받음.
        //2번 구독자는 GREEN 후에 구독했기때문에 GREEN과 BLUE만 전달받음.
    }

    //가장 보편적인 구독방법
    public static void publishSubject(){
        System.out.println("==publishSubject==");
        PublishSubject<String> subject = PublishSubject.create();
        subject.subscribe(data -> System.out.println("Subscriber #1 => " + data));
        subject.onNext("RED");
        subject.onNext("GREEN");
        subject.subscribe(data -> System.out.println("Subscriber #2 => " + data));
        subject.onNext("BLUE");
        subject.onComplete();
        //1번 구독자는 전부 전달받음.
        //2번 구독자는 자신이 구독한 시점 후의 정보만 전달받음
    }

    public static void replaySubject (){
        System.out.println("==replaySubject==");
        ReplaySubject<String> subject = ReplaySubject.create();
        subject.subscribe(data -> System.out.println("Subscriber #1 => " + data));
        subject.onNext("RED");
        subject.onNext("GREEN");
        subject.subscribe(data -> System.out.println("Subscriber #2 => " + data));
        subject.onNext("BLUE");
        subject.onComplete();
    }
    
    public static void main(String[] args) {
        AsyncSubject();
        AsyncSubjectByObeserVable();
        beHaviorSubject();
        publishSubject();
        replaySubject();
    }

    

}
