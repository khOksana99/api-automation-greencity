package com.greencity.auto.dataprovider;

import com.greencity.auto.utils.Util;
import io.github.sskorol.core.DataSupplier;

import java.util.stream.Stream;

public class HabitDataProvider {
    @DataSupplier(name = "wrongDates")
    public Stream<String> wrongDates() {
        return Stream.of(
                Util.getFutureDate(),
                Util.getPastDate()
        );
    }
}
