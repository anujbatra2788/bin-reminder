package au.com.anuj.bin;

import au.com.anuj.bin.config.BinsConfiguration;
import au.com.anuj.bin.config.ConfigTests;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;

public class BaseTest {


    private static ObjectMapper mapper = new ObjectMapper();
    protected static BinsConfiguration configuration;

    protected SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @BeforeAll
    public static void readConfig() throws IOException {
        InputStream stream = ConfigTests.class.getResourceAsStream("/config.json");
        configuration = mapper.readValue(stream, BinsConfiguration.class);
    }
}
