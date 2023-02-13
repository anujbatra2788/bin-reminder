package au.com.anuj.bin.collection;

import au.com.anuj.bin.response.BinResponse;
import au.com.anuj.bin.config.BinConfiguration;
import au.com.anuj.bin.config.BinsConfiguration;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BinCollection {

    private ObjectMapper mapper = new ObjectMapper();
    private InputStream stream = BinCollection.class.getResourceAsStream("/config.json");
    private BinsConfiguration configuration = mapper.readValue(stream, BinsConfiguration.class);
    private BinCollectionDate dateGenerator = new BinCollectionDate();

    public BinCollection() throws IOException {
    }

    public List<BinResponse> getBinsDatesAndAttributes() {
        BinResponse response;
        Date nextBinDate;
        List<BinResponse> responses = new ArrayList<>();
        for(BinConfiguration configuration: configuration.getBins()) {
            response = new BinResponse();
            nextBinDate = dateGenerator.getNextDateForBin(configuration);
            response.setBin(configuration.getBin());
            response.setColourCode(configuration.getColourCode());
            response.setNextCollectionDate(nextBinDate);
            response.setFrequency(configuration.getFrequency());
            responses.add(response);
        }
        return responses;
    }
}
