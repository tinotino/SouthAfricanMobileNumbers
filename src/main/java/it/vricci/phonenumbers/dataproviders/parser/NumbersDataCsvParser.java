package it.vricci.phonenumbers.dataproviders.parser;

import com.opencsv.bean.CsvToBeanBuilder;
import it.vricci.phonenumbers.core.entities.numbers.GroupOfNumbers;
import it.vricci.phonenumbers.core.usecases.uploadData.NumbersDataParser;
import it.vricci.phonenumbers.core.usecases.uploadData.ReadCheckAndStoreResultsRequest;
import lombok.NonNull;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class NumbersDataCsvParser implements NumbersDataParser {
    @Override
    public GroupOfNumbers parse(@NonNull ReadCheckAndStoreResultsRequest request) {
        if(request.isNullData()) {
            return GroupOfNumbers.empty();
        }
        final InputStreamReader reader = new InputStreamReader(new ByteArrayInputStream(request.getBytes()));
        final List<PhoneNumberCsv> phoneNumberCsvList = extractPhoneNumberCsvList(reader);
        return phoneNumberCsvListToPhoneNumbers(phoneNumberCsvList);
    }


    private List<PhoneNumberCsv> extractPhoneNumberCsvList(InputStreamReader reader) {
        return (List<PhoneNumberCsv>) new CsvToBeanBuilder(reader)
                .withType(PhoneNumberCsv.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build()
                .parse();
    }

    private GroupOfNumbers phoneNumberCsvListToPhoneNumbers(List<PhoneNumberCsv> phoneNumberCsvList) {
        return GroupOfNumbers.with(phoneNumberCsvList.stream()
                .filter(PhoneNumberCsv::isNotHeader)
                .map(PhoneNumberCsv::toPhoneNumber)
                .collect(Collectors.toList()));
    }

}
