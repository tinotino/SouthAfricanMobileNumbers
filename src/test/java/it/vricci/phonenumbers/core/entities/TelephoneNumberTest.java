package it.vricci.phonenumbers.core.entities;

import it.vricci.phonenumbers.MockFactory;
import it.vricci.phonenumbers.core.entities.numbers.TelephoneNumber;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class TelephoneNumberTest {
    private final MockFactory mockFactory = new MockFactory();

    @Test
    public void anUncheckedMatchesPatterns(){
        assertThat(mockFactory.anUnchecked().matches(".*"),is(true));
        assertThat(mockFactory.anUnchecked().matches("5678"),is(false));
    }

    @Test
    public void unchecked_isNotCorrectOrIncorrectOrCorrected(){
        assertThat(mockFactory.anUnchecked().isCorrect(), is(false));
        assertThat(mockFactory.anUnchecked().isIncorrect(), is(false));
        assertThat(mockFactory.anUnchecked().isCorrected(), is(false));
    }

    @Test
    public void correct_isCorrect_and_isIncorrectOrCorrected(){
        assertThat(mockFactory.aCorrect().isCorrect(), is(true));
        assertThat(mockFactory.aCorrect().isIncorrect(), is(false));
        assertThat(mockFactory.aCorrect().isCorrected(), is(false));
    }

    @Test
    public void incorrect_isIncorrect_and_isNotCorrectOrCorrected(){
        assertThat(mockFactory.anIncorrect().isCorrect(), is(false));
        assertThat(mockFactory.anIncorrect().isIncorrect(), is(true));
        assertThat(mockFactory.anIncorrect().isCorrected(), is(false));
    }

    @Test
    public void corrected_isCorrected_and_isNotCorrectOrIncorrect(){
        assertThat(mockFactory.aCorrected().isCorrect(), is(false));
        assertThat(mockFactory.aCorrected().isIncorrect(), is(false));
        assertThat(mockFactory.aCorrected().isCorrected(), is(true));
    }

}