
/**
 * Write a description of part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
public class part1 {
    
    private String countryInfo(CSVParser parser, String country){
        String output = "";
        for(CSVRecord record : parser){
          if(record.get("Country").equals(country)){
              output = (record.get("Country") + ": " + record.get("Exports") + ": " + record.get("Value (dollars)"));
           }
          //System.out.println("Value" + record.get("Country") == "Ge);
        }
        if (output.equals("")){
            return "NOT FOUND";
        }else{
            return output;
        }
    }
    
    private void listExportersTwoProducts(CSVParser parser, String exportIteam1, String exportIteam2){
        for(CSVRecord record : parser){
            String exports = record.get("Exports");
            if (exports.contains(exportIteam1) && exports.contains(exportIteam2)){
                System.out.println(record.get("Country"));
            }
        }
    }
    
    
    private int numberOfExporters(CSVParser parser, String exportItem){
        int numOfExporters = 0;
          for(CSVRecord record : parser){
            String exports = record.get("Exports");
            if (exports.contains(exportItem)){
                numOfExporters += 1;
            }
        }
        return numOfExporters;
    }
    
    private void bigExporters(CSVParser parser, String amount){
        for(CSVRecord record : parser){
            String value = record.get("Value (dollars)");
            if (value.length() > amount.length()){
                System.out.println(record.get("Country") + " " + record.get("Value (dollars)"));
            }
        }
    }
    
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //System.out.println(countryInfo(parser, "Nauru"));
        //parser = fr.getCSVParser();
        //System.out.println(countryInfo(parser, "Shambala"));
        //parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "cotton", "flowers");
        parser = fr.getCSVParser();
        System.out.println(numberOfExporters(parser, "cocoa"));
        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
    }

}
