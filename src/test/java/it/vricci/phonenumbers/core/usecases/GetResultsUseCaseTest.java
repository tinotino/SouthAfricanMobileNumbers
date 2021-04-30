package it.vricci.phonenumbers.core.usecases;

import it.vricci.phonenumbers.MockFactory;
import it.vricci.phonenumbers.core.entities.numbers.GroupOfNumbers;
import it.vricci.phonenumbers.core.usecases.showResults.GetResultsUseCase;
import it.vricci.phonenumbers.core.usecases.showResults.GetResultsUseCaseImpl;
import it.vricci.phonenumbers.core.usecases.showResults.GetResultsUseCaseResponse;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetResultsUseCaseTest {
    private final MockFactory mockFactory = new MockFactory();
    private final PhoneNumbersRepository repository = mock(PhoneNumbersRepository.class);
    private final GetResultsUseCase useCase = new GetResultsUseCaseImpl(repository);

    @Test
    public void ifErrorRetrievingResults_throwsReadResultsException() {
        when(repository.findAll())
                .thenThrow(new RuntimeException());

        try {
            useCase.act();
            fail("BusinessException expected");
        } catch (BusinessException businessException) {
            assertThat(businessException, is(BusinessException.readResultsException()));
        }
    }

    @Test
    public void readAndReturn_success() throws BusinessException {
        final GroupOfNumbers groupOfNumbers = GroupOfNumbers.with(mockFactory.aCorrect());
        when(repository.findAll())
                .thenReturn(groupOfNumbers);

        final GetResultsUseCaseResponse actual = useCase.act();

        assertThat(actual,is(GetResultsUseCaseResponse.of(groupOfNumbers)));
    }

}