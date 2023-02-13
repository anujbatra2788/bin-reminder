package au.com.anuj.bin.collection;

import au.com.anuj.bin.response.BinResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BinCollectionTest {

    private BinCollection binCollection = new BinCollection();

    BinCollectionTest() throws IOException {
    }

    @Test
    void getBinsDatesAndAttributes() {
        List<BinResponse> binResponses = binCollection.getBinsDatesAndAttributes();
        assertTrue(binResponses.size() == 3);

        // Check first Bin
        assertEquals("red", binResponses.get(0).getBin());
        assertNull(binResponses.get(0).getColourCode());
        assertEquals(7, binResponses.get(0).getFrequency());
        assertNotNull(binResponses.get(0).getNextCollectionDate());
    }
}