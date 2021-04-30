package it.vricci.phonenumbers.dataproviders.parser;

import it.vricci.phonenumbers.core.entities.numbers.GroupOfNumbers;
import it.vricci.phonenumbers.core.entities.numbers.TelephoneNumberFactory;
import it.vricci.phonenumbers.core.usecases.uploadData.NumbersDataParser;
import it.vricci.phonenumbers.core.usecases.uploadData.ReadCheckAndStoreResultsRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.file.Files;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@SpringBootTest
public class GroupOfNumbersDataParserIntegrationTest {
    @Value("classpath:South_African_Mobile_Numbers_Only_One.csv")
    private Resource smallFile;
    @Value("classpath:South_African_Mobile_Numbers.csv")
    private Resource bigFile;
    @Autowired
    private NumbersDataParser parser;

    @Test
    public void ifNullRequest_readNumbers_ok() {
        final GroupOfNumbers groupOfNumbers = parser.parse(ReadCheckAndStoreResultsRequest.nullData());

        assertThat(groupOfNumbers, is(GroupOfNumbers.empty()));
    }

    @Test
    public void readData_fromSmallFile_ok() throws IOException {
        final GroupOfNumbers groupOfNumbers = parser.parse(buildRequest());

        assertThat(groupOfNumbers, is(GroupOfNumbers.with(TelephoneNumberFactory.unchecked("103426733","27736529279"))));
    }

    @Test
    public void readData_fromBigFile_ok() throws IOException {
        final GroupOfNumbers groupOfNumbers = parser.parse(buildRequest());

        assertThat(groupOfNumbers.isEmpty(), is(false));
    }

    private ReadCheckAndStoreResultsRequest buildRequest() throws IOException {
        return ReadCheckAndStoreResultsRequest.from(Files.readAllBytes(smallFile.getFile().toPath()));
    }
}