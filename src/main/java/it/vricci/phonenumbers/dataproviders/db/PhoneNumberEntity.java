package it.vricci.phonenumbers.dataproviders.db;

import it.vricci.phonenumbers.core.entities.numbers.TelephoneNumberFactory;
import it.vricci.phonenumbers.core.entities.numbers.TelephoneNumber;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "phone_numbers")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneNumberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String externalId;
    private String state;
    private String errorType;
    private String original;
    private String corrected;

    public static PhoneNumberEntity from(TelephoneNumber telephoneNumber) {
        return PhoneNumberEntity.builder()
                .externalId(telephoneNumber.getId())
                .original(telephoneNumber.getOriginalNumber())
                .errorType(telephoneNumber.getCheckStatus())
                .corrected(telephoneNumber.getCorrectedNumber())
                .build();
    }

    public TelephoneNumber toPhoneNumber() {
        return TelephoneNumberFactory.from(errorType, externalId, original, corrected);
    }

}
