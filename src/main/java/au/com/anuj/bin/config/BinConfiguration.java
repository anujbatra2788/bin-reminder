package au.com.anuj.bin.config;

import lombok.Data;

import java.util.Date;

@Data
public class BinConfiguration {
    private String bin;

    private String colourCode;

    private int day;

    private Date startDate;

    private int frequency;
}
