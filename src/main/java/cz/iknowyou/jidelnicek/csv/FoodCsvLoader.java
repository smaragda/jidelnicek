package cz.iknowyou.jidelnicek.csv;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBeanBuilder;
import cz.iknowyou.jidelnicek.combiner.ListWrap;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.FileReader;
import java.util.List;

@Slf4j
public class FoodCsvLoader {

    private final CSVParser parser;

    public FoodCsvLoader() {
        parser = new CSVParserBuilder()
                .withSeparator(',')
                .withIgnoreQuotations(false)
                .build();
    }

    public ListWrap<Food> loadFrom(String fileName) {
        return ListWrap.<Food>builder()
                .items(load(fileName))
                .build();
    }

    @SneakyThrows
    private List<Food> load(String fileName) {
        log.debug("start loading food from excel..");

        FileReader reader = new FileReader(fileName);

        CSVReader csvReader = new CSVReaderBuilder(reader)
                .withSkipLines(0)
                .withCSVParser(parser)
                .build();

        return new CsvToBeanBuilder(csvReader)
                .withType(Food.class)
                .build()
                .parse();
    }

}
