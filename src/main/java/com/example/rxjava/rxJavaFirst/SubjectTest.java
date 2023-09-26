package com.example.rxjava.rxJavaFirst;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.subjects.AsyncSubject;

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
    }

    public static void AsyncSubjectByObeserVable(){
        Float[] temperature = {10.1f, 13.4f, 12.5f};
        Observable<Float> source = Observable.fromArray(temperature);

        AsyncSubject<Float> subject = AsyncSubject.create();
        subject.subscribe(data -> System.out.println("Subscriber #1 => " + data));

        source.subscribe(subject);
    }
    
    public static void main(String[] args) {
        AsyncSubject();
        AsyncSubjectByObeserVable();
    }

    

}
