package it.vricci.phonenumbers.core.entities.numbers;

import it.vricci.phonenumbers.core.usecases.NumbersChecker;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@EqualsAndHashCode
@ToString
public class GroupOfNumbers {
    private final List<TelephoneNumber> list;

    private GroupOfNumbers(TelephoneNumber[] list) {
        this.list = Arrays.stream(list)
                .sorted(
                        Comparator
                                .comparing(TelephoneNumber::getCheckStatus)
                                .thenComparing(TelephoneNumber::getOriginalNumber))
                .collect(Collectors.toUnmodifiableList());
    }

    public static GroupOfNumbers with(TelephoneNumber... telephoneNumber) {
        return new GroupOfNumbers(telephoneNumber);
    }

    public static GroupOfNumbers empty() {
        return new GroupOfNumbers(new TelephoneNumber[]{});
    }

    public static GroupOfNumbers with(@NonNull List<TelephoneNumber> telephoneNumbers) {
        return new GroupOfNumbers(telephoneNumbers.toArray(new TelephoneNumber[]{}));
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public GroupOfNumbers check(NumbersChecker checker) {
        return GroupOfNumbers.with(list.stream()
                .map(checker::check)
                .collect(Collectors.toList()));
    }

    public Integer countTotal() {
        return list.size();
    }

    public Integer countCorrect() {
        return count(TelephoneNumber::isCorrect);
    }

    public Integer countIncorrect() {
        return count(TelephoneNumber::isIncorrect);
    }

    public Integer countCorrected() {
        return count(TelephoneNumber::isCorrected);
    }

    private int count(Predicate<TelephoneNumber> predicate) {
        return Long
                .valueOf(list.stream()
                        .filter(predicate)
                        .count())
                .intValue();
    }

    public List<TelephoneNumber> getList() {
        return list;
    }
}
