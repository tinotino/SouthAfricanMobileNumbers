package it.vricci.phonenumbers.entrypoints;

import it.vricci.phonenumbers.core.usecases.*;
import it.vricci.phonenumbers.core.usecases.showResults.GetResultsUseCase;
import it.vricci.phonenumbers.core.usecases.showResults.GetResultsUseCaseResponse;
import it.vricci.phonenumbers.core.usecases.uploadData.ReadCheckAndStoreResultsRequest;
import it.vricci.phonenumbers.core.usecases.uploadData.ReadCheckAndStoreResultsUseCase;
import it.vricci.phonenumbers.core.usecases.uploadData.ReadCheckAndStoreResultsUseCaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class ApiController {
    public static final String UPLOAD_URI = "/api/upload";
    public static final String RESULTS_URI = "/api/results";

    private final ReadCheckAndStoreResultsUseCase readCheckAndStoreResultsUseCase;
    private final GetResultsUseCase getResultsUseCase;

    @Autowired
    public ApiController(ReadCheckAndStoreResultsUseCase readCheckAndStoreResultsUseCase, GetResultsUseCase getResultsUseCase) {
        this.readCheckAndStoreResultsUseCase = readCheckAndStoreResultsUseCase;
        this.getResultsUseCase = getResultsUseCase;
    }

    @PostMapping(value = UPLOAD_URI)
    public ResponseEntity<UploadResponseDto> upload(@RequestParam("file") MultipartFile file){
        try {
            final ReadCheckAndStoreResultsUseCaseResponse response = readCheckAndStoreResultsUseCase.act(buildRequest(file));
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(UploadResponseDto.success(response));
        }catch (BusinessException businessException){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(UploadResponseDto.failure(businessException));
        }
    }

    private ReadCheckAndStoreResultsRequest buildRequest(MultipartFile file) {
        try {
            return ReadCheckAndStoreResultsRequest.from(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = RESULTS_URI)
    public ResponseEntity<GetResultsResponseDto> getResults(/*filtering??*/){
        try {
            final GetResultsUseCaseResponse response = getResultsUseCase.act();
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(GetResultsResponseDto.success(response));
        }catch (BusinessException businessException){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(GetResultsResponseDto.failure(businessException));
        }
    }

}
