package it.vricci.phonenumbers.core.usecases;

import it.vricci.phonenumbers.core.entities.numbers.GroupOfNumbers;
import it.vricci.phonenumbers.core.usecases.uploadData.*;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.fail;

public class ReadCheckAndStoreResultsUseCaseTest {
    private final NumbersDataParser parser = mock(NumbersDataParser.class);
    private final NumbersChecker checker = mock(NumbersChecker.class);
    private final PhoneNumbersRepository repository = mock(PhoneNumbersRepository.class);
    private final GroupOfNumbers groupOfNumbers = mock(GroupOfNumbers.class);
    private final GroupOfNumbers checkedGroupOfNumbers = mock(GroupOfNumbers.class);
    private final ReadCheckAndStoreResultsUseCase useCase = new ReadCheckAndStoreResultsUseCaseImpl(parser, checker,repository);

    @Test
    public void ifIsDataUnreadable_throwsUnreadableDataException() {
        final ReadCheckAndStoreResultsRequest request = ReadCheckAndStoreResultsRequest.nullData();
        when(parser.parse(request))
                .thenThrow(new RuntimeException());
        try {
            useCase.act(request);
            fail("BusinessException expected");
        } catch (BusinessException businessException) {
            assertThat(businessException, is(BusinessException.readDataException()));
        }
    }

    @Test
    public void ifErrorCheckingNumbers_throwsCheckNumbersException() {
        final ReadCheckAndStoreResultsRequest request = ReadCheckAndStoreResultsRequest.from(buildMockData());
        when(parser.parse(request))
                .thenReturn(groupOfNumbers);
        when(groupOfNumbers.check(checker))
                .thenThrow(new RuntimeException());
        try {
            useCase.act(request);
            fail("BusinessException expected");
        } catch (BusinessException businessException) {
            assertThat(businessException, is(BusinessException.checkNumbersException()));
        }
    }

    @Test
    public void ifErrorSavingResults_throwsSaveResultsException() {
        final ReadCheckAndStoreResultsRequest request = ReadCheckAndStoreResultsRequest.from(buildMockData());
        when(parser.parse(request))
                .thenReturn(groupOfNumbers);
        when(groupOfNumbers.check(checker))
                .thenReturn(checkedGroupOfNumbers);
        when(repository.save(checkedGroupOfNumbers))
                .thenThrow(new RuntimeException());

        try {
            useCase.act(request);
            fail("BusinessException expected");
        } catch (BusinessException businessException) {
            assertThat(businessException, is(BusinessException.saveResultsException()));
        }
    }

    @Test
    public void ifDataIsEmpty_throwsEmptyDataException() {
        final ReadCheckAndStoreResultsRequest request = ReadCheckAndStoreResultsRequest.from(buildMockData());
        when(parser.parse(request))
                .thenReturn(groupOfNumbers);
        when(groupOfNumbers.isEmpty())
                .thenReturn(true);

        try {
            useCase.act(request);
            fail("BusinessException expected");
        } catch (BusinessException businessException) {
            assertThat(businessException, is(BusinessException.emptyDataException()));
        }
    }

    @Test
    public void readData_checkNumbers_saveResults_success() throws BusinessException {
        final ReadCheckAndStoreResultsRequest request = ReadCheckAndStoreResultsRequest.from(buildMockData());
        final GroupOfNumbers resultsSaved = mock(GroupOfNumbers.class);
        when(parser.parse(request))
                .thenReturn(groupOfNumbers);
        when(groupOfNumbers.check(checker))
                .thenReturn(checkedGroupOfNumbers);
        when(repository.save(checkedGroupOfNumbers))
                .thenReturn(resultsSaved);

        final ReadCheckAndStoreResultsUseCaseResponse actual = useCase.act(request);

        assertThat(actual, is(ReadCheckAndStoreResultsUseCaseResponse.success(resultsSaved)));
    }

    private byte[] buildMockData() {
        return "data".getBytes(StandardCharsets.UTF_8);
    }

}