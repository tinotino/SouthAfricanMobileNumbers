package it.vricci.phonenumbers.configuration;

import it.vricci.phonenumbers.core.entities.checkers.SouthAfricanMobileNumbersChecker;
import it.vricci.phonenumbers.core.usecases.*;
import it.vricci.phonenumbers.core.usecases.checkNumber.CheckNumberUseCase;
import it.vricci.phonenumbers.core.usecases.checkNumber.CheckNumberUseCaseImpl;
import it.vricci.phonenumbers.core.usecases.showResults.GetResultsUseCase;
import it.vricci.phonenumbers.core.usecases.showResults.GetResultsUseCaseImpl;
import it.vricci.phonenumbers.core.usecases.NumbersChecker;
import it.vricci.phonenumbers.core.usecases.uploadData.NumbersDataParser;
import it.vricci.phonenumbers.core.usecases.uploadData.ReadCheckAndStoreResultsUseCase;
import it.vricci.phonenumbers.core.usecases.uploadData.ReadCheckAndStoreResultsUseCaseImpl;
import it.vricci.phonenumbers.dataproviders.db.PhoneNumberCrudRepository;
import it.vricci.phonenumbers.dataproviders.db.PhoneNumbersDbRepository;
import it.vricci.phonenumbers.dataproviders.parser.NumbersDataCsvParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PhoneNumbersApplicationContext {

    @Bean
    public PhoneNumbersRepository phoneNumbersRepository(PhoneNumberCrudRepository phoneNumberCrudRepository){
        return new PhoneNumbersDbRepository(phoneNumberCrudRepository);
    }

    @Bean
    public NumbersChecker numbersChecker(){
        return new SouthAfricanMobileNumbersChecker();
    }

    @Bean
    public NumbersDataParser numbersDataParser(){
        return new NumbersDataCsvParser();
    }

    @Bean
    public ReadCheckAndStoreResultsUseCase readCheckAndStoreNumbersUseCase(NumbersDataParser parser, NumbersChecker checker, PhoneNumbersRepository repository){
        return new ReadCheckAndStoreResultsUseCaseImpl(parser, checker, repository);
    }

    @Bean
    public GetResultsUseCase getResultsUseCase(PhoneNumbersRepository repository){
        return new GetResultsUseCaseImpl(repository);
    }

    @Bean
    public CheckNumberUseCase checkNumberUseCase(NumbersChecker checker){
        return new CheckNumberUseCaseImpl(checker);
    }
}
