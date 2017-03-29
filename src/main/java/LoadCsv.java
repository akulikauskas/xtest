import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Created by Bendras on 2017-03-29.
 */
public class LoadCsv {


  public static void loadCsv(String fileName) throws IOException {
      Logger logger = LoggerFactory.getLogger("LoadCsv");

      Reader in = new FileReader(fileName);
      Iterable<CSVRecord> records = CSVFormat.newFormat(';').withFirstRecordAsHeader().parse(in);
      for (CSVRecord record : records) {
          String lastName = record.get("Pavarde");
          String firstName = record.get("Vardas");
          String age = record.get("Amzius");
          logger.info ("Vardas:{} Pavarde:{} Amzius:{}", firstName, lastName, age);
      }
  }


}
