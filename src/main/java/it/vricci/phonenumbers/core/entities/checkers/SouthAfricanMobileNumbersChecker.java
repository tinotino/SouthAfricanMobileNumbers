package it.vricci.phonenumbers.core.entities.checkers;

import it.vricci.phonenumbers.core.entities.numbers.TelephoneNumber;
import it.vricci.phonenumbers.core.usecases.NumbersChecker;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class SouthAfricanMobileNumbersChecker implements NumbersChecker {
    private final List<SouthAfricanMobileCheckStatus> telephoneNumberHandlerList;

    public SouthAfricanMobileNumbersChecker() {
        this.telephoneNumberHandlerList = List.of(SouthAfricanMobileCheckStatus.values());
    }

    @Override
    public TelephoneNumber check(TelephoneNumber telephoneNumber) {
        return telephoneNumberHandlerList.stream()
                .filter(checkStatus -> checkStatus.canHandle(telephoneNumber))
                .findAny()
                .orElseThrow()
                .handle(telephoneNumber);
    }

}
