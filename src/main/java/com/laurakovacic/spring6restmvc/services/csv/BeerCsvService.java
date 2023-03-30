package com.laurakovacic.spring6restmvc.services.csv;

import com.laurakovacic.spring6restmvc.model.BeerCSVRecord;

import java.io.File;
import java.util.List;

public interface BeerCsvService {
    List<BeerCSVRecord> convertCSV(File csvFile);
}
