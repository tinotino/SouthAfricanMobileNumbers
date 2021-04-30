package it.vricci.phonenumbers.entrypoints;

import it.vricci.phonenumbers.core.entities.numbers.TelephoneNumber;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PhoneNumbersDto {
    private String id;
    private String corrected;
    private String original;
    private String checkStatus;

    public static PhoneNumbersDto from(TelephoneNumber telephoneNumber) {
        return PhoneNumbersDto.builder()
                .id(telephoneNumber.getId())
                .corrected(telephoneNumber.getCorrectedNumber())
                .checkStatus(telephoneNumber.getCheckStatus())
                .original(telephoneNumber.getOriginalNumber())
                .build();
    }
}
