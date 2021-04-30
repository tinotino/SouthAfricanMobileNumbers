package it.vricci.phonenumbers.entrypoints;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.nio.file.Files;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class SouthAfricanMobileGroupOfNumbersEndToEndTest {
    @Autowired
    private MockMvc mockMvc;
    @Value("classpath:South_African_Mobile_Numbers_Only_One.csv")
    private Resource mobileNumbersCsvSmall;
    @Value("classpath:South_African_Mobile_Numbers_Small.csv")
    private Resource mobileNumbersCsvMedium;
    @Value("classpath:South_African_Mobile_Numbers.csv")
    private Resource mobileNumbersCsvBig;

    @Test
    public void uploalSmallCsv_Read_Check_StoreResults() throws Exception {
        mockMvc.perform(
                multipart(ApiController.UPLOAD_URI)
                        .file(buildMultipartFromSouthAfricanMobileNumbersCsv(mobileNumbersCsvSmall)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.countTotal").value(is(1)))
                .andExpect(jsonPath("$.countCorrect").value(is(1)))
                .andExpect(jsonPath("$.countIncorrect").value(is(0)))
                .andExpect(jsonPath("$.countCorrected").value(is(0)))
        ;
    }

    @Test
    public void uploadBigCsv_Read_Check_StoreResults() throws Exception {
        mockMvc.perform(
                multipart(ApiController.UPLOAD_URI)
                        .file(buildMultipartFromSouthAfricanMobileNumbersCsv(mobileNumbersCsvBig)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.countTotal").value(is(1000)))
                .andExpect(jsonPath("$.countCorrect").value(is(439)))
                .andExpect(jsonPath("$.countIncorrect").value(is(434)))
                .andExpect(jsonPath("$.countCorrected").value(is(127)))
        ;

    }

    @Test
    public void getResults() throws Exception {
        uploadData();

        mockMvc.perform(get(ApiController.RESULTS_URI))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.results").isArray())
                .andExpect(jsonPath("$.results[*].id").exists())
                .andExpect(jsonPath("$.results[*].original").exists())
                .andExpect(jsonPath("$.results[*].checkStatus").exists())
                .andExpect(jsonPath("$.results[*].corrected").exists())
        ;

    }

    private void uploadData() throws Exception {
        mockMvc.perform(
                multipart(ApiController.UPLOAD_URI)
                        .file(buildMultipartFromSouthAfricanMobileNumbersCsv(mobileNumbersCsvMedium)));
    }

    private MockMultipartFile buildMultipartFromSouthAfricanMobileNumbersCsv(Resource resource) throws IOException {
        return new MockMultipartFile("file", resource.getFilename(), MediaType.TEXT_PLAIN_VALUE, Files.readAllBytes(resource.getFile().toPath()));
    }

    @Test
    public void showForm() throws Exception {
        mockMvc.perform(get(WebController.CHECK_FORM_URI))
                .andExpect(status().isOk())
        ;
    }
}
