package it.vricci.phonenumbers.core.usecases.uploadData;

import lombok.Data;
import lombok.ToString;

@Data
public class ReadCheckAndStoreResultsRequest {
    @ToString.Exclude
    private final byte[] bytes;

    private ReadCheckAndStoreResultsRequest(byte[] bytes) {
        this.bytes = bytes;
    }

    public static ReadCheckAndStoreResultsRequest nullData() {
        return new ReadCheckAndStoreResultsRequest(null);
    }

    public static ReadCheckAndStoreResultsRequest from(byte[] bytes) {
        return new ReadCheckAndStoreResultsRequest(bytes);
    }

    public boolean isNullData() {
        return this.equals(nullData());
    }
}
