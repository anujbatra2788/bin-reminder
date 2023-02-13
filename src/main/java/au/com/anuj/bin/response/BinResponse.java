package au.com.anuj.bin.response;

import lombok.Data;

import java.util.Date;

@Data
public class BinResponse {

    private String bin;

    private String colourCode;

    private Date nextCollectionDate;

    private int frequency;
}
