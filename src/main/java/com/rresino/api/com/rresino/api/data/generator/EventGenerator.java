package com.rresino.api.com.rresino.api.data.generator;

import com.rresino.api.com.rresino.api.data.Event;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Created by rresino on 27/08/2016.
 */
public class EventGenerator {

    final private static int DATE_MAX_DELAY_DAYS_FOR_RANDOM = 120;
    final private static int MESSAGE_MAX_WORDS_FOR_RANDOM = 240;

    private final static String[] WORDS = new String[] {"Lorem", "ipsum", "dolor", "sit", "amet", "consectetur", "adipiscing", "elit", "Vestibulum", "vel", "nibh", "ac", "enim", "tristique", "accumsan", "Vivamus", "varius", "sapien", "libero", "Nullam", "placerat", "lectus", "nec", "lorem", "interdum", "quis", "viverra", "purus", "elementum", "Maecenas", "aliquet", "elit", "sodales", "ante", "porta", "eleifend", "Suspendisse", "est", "orci", "suscipit", "a", "turpis", "vitae", "lobortis", "iaculis", "mauris", "Praesent", "cursus", "eros", "et", "sem", "accumsan", "faucibus", "Morbi", "tincidunt", "nunc", "a", "neque", "maximus", "convallis", "Integer", "sed", "molestie", "nisl",
            "Vestibulum", "quis", "magna", "porttitor", "fermentum", "ex", "at", "lobortis", "dolor", "Nam", "eu", "rhoncus", "nisi", "et", "bibendum", "elit", "Aenean", "pulvinar", "blandit", "massa", "eu", "condimentum", "Duis", "congue", "felis", "ac", "placerat", "feugiat", "Maecenas", "lacinia", "hendrerit", "nunc", "a", "tristique", "est", "sodales", "sed", "Maecenas", "varius", "pharetra", "felis", "ultrices", "accumsan", "ex", "Morbi", "et", "porttitor", "ipsum", "Maecenas", "in", "vulputate", "nisl", "in", "ullamcorper", "ante"};

    private final static String[] LEVELS = new String[] {"FATAL", "ERROR", "WARN", "INFO", "DEBUG"};

    private static Random random = new Random(System.nanoTime());

    private static Supplier<String> wordsSupplier = () -> WORDS[random.nextInt(WORDS.length)];

    private static Supplier<String> levelsSupplier = () -> LEVELS[random.nextInt(LEVELS.length)];

    private static Supplier<LocalDateTime> dateSupplier = () -> {
        LocalDateTime dateTime = LocalDateTime.now();
        dateTime.minusDays(random.nextInt(DATE_MAX_DELAY_DAYS_FOR_RANDOM));
        return dateTime;
    };

    private static Supplier<String> messageSupplier = () -> {
        return Stream.generate(wordsSupplier)
                .limit(MESSAGE_MAX_WORDS_FOR_RANDOM)
                .reduce((a, b) -> a + " " + b).orElse("");
    };

    private static Supplier<Event> eventSupplier = () -> {
        return new Event(dateSupplier.get(),
                levelsSupplier.get(),
                messageSupplier.get());
    };

    public static Stream<Event> generateEvents(int numberOfEvent) {
        return Stream.generate(eventSupplier).limit(numberOfEvent);
    }
}
