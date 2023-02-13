package au.com.anuj.bin.config;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ConfigTests {

    private static ObjectMapper mapper = new ObjectMapper();;

    @BeforeAll
    public static void readConfig() {
    }

    @Test
    public void shouldReadConfig() throws IOException, ParseException {
        InputStream stream = ConfigTests.class.getResourceAsStream("/config.json");
        BinsConfiguration configuration = mapper.readValue(stream, BinsConfiguration.class);
        assertEquals(3, configuration.getBins().size());

        assertEquals(3, configuration.getBins().size());

        // Check for first config of red Bin
        assertEquals("red", configuration.getBins().get(0).getBin());
        assertNull(configuration.getBins().get(0).getColourCode());
        assertEquals(2, configuration.getBins().get(0).getDay());
        assertEquals(7, configuration.getBins().get(0).getFrequency());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        assertEquals("2023-02-14", format.format(configuration.getBins().get(0).getStartDate()));

        // Check for second config of green Bin

        assertEquals("green", configuration.getBins().get(1).getBin());
        assertEquals("#FF2345", configuration.getBins().get(1).getColourCode());
        assertEquals(2, configuration.getBins().get(1).getDay());
        assertEquals(14, configuration.getBins().get(1).getFrequency());
        assertEquals("2023-02-21", format.format(configuration.getBins().get(1).getStartDate()));
    }
}
