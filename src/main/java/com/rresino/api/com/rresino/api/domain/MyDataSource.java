package com.rresino.api.com.rresino.api.domain;

import com.rresino.api.com.rresino.api.data.Event;

import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by rresino on 27/08/2016.
 */
public class MyDataSource implements Iterator<Event> {

    int index = 0;
    List<Event> innerData = new ArrayList<>();

    public MyDataSource(Stream<Event> dataGenerator) {
        this.innerData = dataGenerator.collect(Collectors.toList());
    }

    @Override
    public boolean hasNext() {
        return innerData.size() > index;
    }

    @Override
    public Event next() {
        index++;
        return innerData.get(index-1);
    }

}
