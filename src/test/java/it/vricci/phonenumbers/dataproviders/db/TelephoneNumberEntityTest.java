package it.vricci.phonenumbers.dataproviders.db;

import it.vricci.phonenumbers.MockFactory;
import it.vricci.phonenumbers.core.entities.checkers.SouthAfricanMobileCheckStatus;
import it.vricci.phonenumbers.core.entities.numbers.TelephoneNumber;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class TelephoneNumberEntityTest {
    private final MockFactory mockFactory = new MockFactory();

    @Test
    public void fromTelephoneNumber_verifyAllTypes() {
        verifyEntityFieldsAfterTranslationFrom(mockFactory.aCorrect());
        verifyEntityFieldsAfterTranslationFrom(mockFactory.anIncorrect());
        verifyEntityFieldsAfterTranslationFrom(mockFactory.aCorrected());
        verifyEntityFieldsAfterTranslationFrom(mockFactory.anUnchecked());
    }

    private void verifyEntityFieldsAfterTranslationFrom(TelephoneNumber anUnchecked) {
        final PhoneNumberEntity actual = PhoneNumberEntity.from(anUnchecked);

        assertThat(actual.getCorrected(), is(anUnchecked.getCorrectedNumber()));
        assertThat(actual.getOriginal(), is(anUnchecked.getOriginalNumber()));
        assertThat(actual.getErrorType(), is(anUnchecked.getCheckStatus()));
        assertThat(actual.getExternalId(), is(anUnchecked.getId()));
    }

    @Test
    public void toPhoneNumber_verifyAllTypes() {
        verifyTelephoneNumberFieldsAfterTranslationFrom(aCorrectEntity());
        verifyTelephoneNumberFieldsAfterTranslationFrom(anIncorrectEntity());
        verifyTelephoneNumberFieldsAfterTranslationFrom(anUncheckedEntity());
        verifyTelephoneNumberFieldsAfterTranslationFrom(aCorrectedEntity());
    }

    private PhoneNumberEntity aCorrectEntity() {
        return getNotCorrectedEntityOf(SouthAfricanMobileCheckStatus.CORRECT);
    }

    private PhoneNumberEntity anIncorrectEntity() {
        return getNotCorrectedEntityOf(SouthAfricanMobileCheckStatus.INCORRECT);
    }

    private PhoneNumberEntity anUncheckedEntity() {
        return getNotCorrectedEntityOf(SouthAfricanMobileCheckStatus.UNCHECKED);
    }

    private PhoneNumberEntity getNotCorrectedEntityOf(SouthAfricanMobileCheckStatus incorrect) {
        return PhoneNumberEntity.builder()
                .errorType(incorrect.name())
                .original("original")
                .externalId("id")
                .build();
    }

    private PhoneNumberEntity aCorrectedEntity() {
        return PhoneNumberEntity.builder()
                .corrected("corrected")
                .errorType(SouthAfricanMobileCheckStatus.WRONG_COUNTRY_CODE.name())
                .original("original")
                .externalId("id")
                .build();
    }

    private void verifyTelephoneNumberFieldsAfterTranslationFrom(PhoneNumberEntity phoneNumberEntity) {
        final TelephoneNumber actual = phoneNumberEntity.toPhoneNumber();

        assertThat(actual.getCorrectedNumber(), is(phoneNumberEntity.getCorrected()));
        assertThat(actual.getOriginalNumber(), is(phoneNumberEntity.getOriginal()));
        assertThat(actual.getCheckStatus(), is(phoneNumberEntity.getErrorType()));
        assertThat(actual.getId(), is(phoneNumberEntity.getExternalId()));
    }

}