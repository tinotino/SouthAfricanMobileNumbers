package it.vricci.phonenumbers.core.entities;

import it.vricci.phonenumbers.MockFactory;
import it.vricci.phonenumbers.core.entities.numbers.GroupOfNumbers;
import it.vricci.phonenumbers.core.entities.numbers.TelephoneNumber;
import it.vricci.phonenumbers.core.entities.numbers.TelephoneNumberFactory;
import it.vricci.phonenumbers.core.usecases.NumbersChecker;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

class GroupOfNumbersTest {
    private final MockFactory mockFactory = new MockFactory();
    private final GroupOfNumbers groupOfNumbers = GroupOfNumbers.with(
            mockFactory.anUnchecked(),
            mockFactory.aCorrect(),
            mockFactory.aCorrect(),
            mockFactory.anIncorrect(),
            mockFactory.anIncorrect(),
            mockFactory.anIncorrect(),
            mockFactory.aCorrected(),
            mockFactory.aCorrected(),
            mockFactory.aCorrected(),
            mockFactory.aCorrected()
    );

    @Test
    public void checkList_callCheckerCheckForEveryNumber(){
        final TelephoneNumber unchecked = mockFactory.anUnchecked();
        final TelephoneNumber unchecked2 = TelephoneNumberFactory.unchecked("id", "unchecked");
        final GroupOfNumbers groupOfNumbers = GroupOfNumbers.with(unchecked,unchecked2);
        final NumbersChecker checker = mock(NumbersChecker.class);
        when(checker.check(unchecked))
                .thenReturn(TelephoneNumberFactory.correct(unchecked));
        when(checker.check(unchecked2))
                .thenReturn(TelephoneNumberFactory.correct(unchecked2));

        final GroupOfNumbers checked = groupOfNumbers.check(checker);

        assertThat(checked,is(GroupOfNumbers.with(TelephoneNumberFactory.correct(unchecked), TelephoneNumberFactory.correct(unchecked2))));
        verify(checker,times(1)).check(unchecked);
        verify(checker,times(1)).check(unchecked2);
    }

    @Test
    public void checkEmptyList_doNothing(){
        final GroupOfNumbers groupOfNumbers = GroupOfNumbers.empty();
        final NumbersChecker checker = mock(NumbersChecker.class);

        final GroupOfNumbers checked = groupOfNumbers.check(checker);

        assertThat(checked,is(GroupOfNumbers.empty()));
        verify(checker,times(0)).check(any());
    }

    @Test
    public void countTotal(){
        final Integer countTotal = groupOfNumbers.countTotal();

        assertThat(countTotal, is(10));
    }

    @Test
    public void countCorrect(){
        final Integer countCorrect = groupOfNumbers.countCorrect();

        assertThat(countCorrect, is(2));
    }

    @Test
    public void countIncorrect(){
        final Integer countIncorrect = groupOfNumbers.countIncorrect();

        assertThat(countIncorrect, is(3));
    }

    @Test
    public void countCorrected(){
        final Integer countCorrected = groupOfNumbers.countCorrected();

        assertThat(countCorrected, is(4));
    }
}