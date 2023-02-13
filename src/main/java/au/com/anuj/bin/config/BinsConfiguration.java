package au.com.anuj.bin.config;

import lombok.Data;

import java.util.List;

@Data
public class BinsConfiguration {
    private List<BinConfiguration> bins;
}
