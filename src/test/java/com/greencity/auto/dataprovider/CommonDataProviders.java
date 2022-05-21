package com.greencity.auto.dataprovider;

import io.github.sskorol.core.DataSupplier;

import java.util.stream.Stream;

public class CommonDataProviders {
    @DataSupplier(name = "specialCharactersDataProvider")
    public Stream<String> specialCharactersDataProvider() {
        return Stream.of(
                "wrong",
                "#",
                "22 11",
                "2bad",
                "2^%",
                "11-",
                "016350fd6e777621f60e7875619b7312"
        );
    }

    @DataSupplier(name = "wrongNumberDataProvider")
    public Stream<Integer> wrongNumberDataProvider() {
        return Stream.of(
                0,
                Integer.MIN_VALUE,
                Integer.MAX_VALUE,
                -1
        );
    }
}
