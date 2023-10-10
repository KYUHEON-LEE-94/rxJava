package com.example.rxjava.rxJavaFirst.basicOperator;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapTest {

    private static Observable<Point> getPointObservable() {
        List<Point> list = new ArrayList<>(Arrays.asList(
                new Point(0, 0), new Point(0, 1), new Point(0, 2),
                new Point(1, 0), new Point(1, 1), new Point(1, 2),
                new Point(2, 0), new Point(2, 1), new Point(2, 2)
        ));

        return Observable.fromIterable(list);
    }

    public static void main(String[] args) {
        Disposable disposable = getPointObservable().map(Point::toString).subscribe(System.out::println);
        disposable.dispose();
    }
}
