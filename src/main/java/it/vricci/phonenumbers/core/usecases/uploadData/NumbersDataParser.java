package it.vricci.phonenumbers.core.usecases.uploadData;

import it.vricci.phonenumbers.core.entities.numbers.GroupOfNumbers;

public interface NumbersDataParser {
    GroupOfNumbers parse(ReadCheckAndStoreResultsRequest request);
}
