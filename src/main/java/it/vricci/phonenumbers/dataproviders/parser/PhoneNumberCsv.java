package it.vricci.phonenumbers.dataproviders.parser;

import com.opencsv.bean.CsvBindByPosition;
import it.vricci.phonenumbers.core.entities.numbers.TelephoneNumberFactory;
import it.vricci.phonenumbers.core.entities.numbers.TelephoneNumber;

public class PhoneNumberCsv {
    @CsvBindByPosition(position = 0)
    private String id;
    @CsvBindByPosition(position = 1)
    private String number;

    public TelephoneNumber toPhoneNumber() {
        return TelephoneNumberFactory.unchecked(id, number);
    }

    public boolean isNotHeader() {
        return !(id.contains("id") && number.contains("sms_phone"));
    }
}
