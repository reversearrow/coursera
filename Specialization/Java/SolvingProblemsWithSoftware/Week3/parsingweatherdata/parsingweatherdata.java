
/**
 * Write a description of parsingweatherdata here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.File;
import org.apache.commons.csv.*;
public class parsingweatherdata {
   private void averageTempInFile(CSVParser parser){
       Double totalTemp = 0.0;
       int totalRows = 0;
       for(CSVRecord record: parser){
           if (!record.get("TemperatureF").equals("-9999")){
                 totalTemp +=   Double.parseDouble(record.get("TemperatureF"));
                 totalRows += 1;
           }
           
       }
       System.out.println("Average temperature in file is " + totalTemp/totalRows);
   }
    
   public void testAverageTemperatureInFile(){
       FileResource fr = new FileResource();
       CSVParser parser = fr.getCSVParser();
       averageTempInFile(parser);
       
   }
   
   private Double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
       Double totalTemp = 0.0;
       int totalRows = 0;
       for(CSVRecord record: parser){
           if (!record.get("TemperatureF").equals("-9999")){
                 Double humidity = Double.parseDouble(record.get("Humidity"));
                 if (humidity >= value){
                     totalTemp +=   Double.parseDouble(record.get("TemperatureF"));
                     totalRows += 1;
                 }
           }
           
       }
       Double averageTemp =  totalTemp/totalRows;
       if (Double.isNaN(averageTemp)){
           System.out.println("No temperatures with that humidity");
       } else {
          System.out.println("Average Temp when high Humidity is " + averageTemp);
       }
       return averageTemp;
   }
   
   public void testAverageTemperatureWithHighHumidityInFile(){
       FileResource fr = new FileResource();
       CSVParser parser = fr.getCSVParser();
       Double averageTemp = averageTemperatureWithHighHumidityInFile(parser, 80);
   }
    
   private CSVRecord lowestHumidityInFile(CSVParser parser){
       CSVRecord lowsetHumidityRecord = null;
       Double lowestHumidity = 0.0;
       String lowestHumidityTime = "";
       for(CSVRecord record: parser){
           if (!record.get("Humidity").equals("N/A")){
               if (lowsetHumidityRecord == null){
                   lowsetHumidityRecord = record;
               }
               else {
                   double currentHumidity = Double.parseDouble(record.get("Humidity"));
                   double currentLowestHumidty = Double.parseDouble(lowsetHumidityRecord.get("Humidity"));
                    if (currentHumidity < currentLowestHumidty){
                       lowestHumidity = currentHumidity;
                       lowestHumidityTime = record.get("DateUTC");
                       lowsetHumidityRecord = record;
                }
               }
           }
       }
       System.out.println("Lowest Humidity was " + lowestHumidity + lowestHumidityTime);
       return lowsetHumidityRecord;
   }
   
   
   private void lowestHumidityInManyFiles(){
       File lowestHumidityTempFile = null;
       DirectoryResource dr = new DirectoryResource();
       for(File currentFile: dr.selectedFiles()){
          if (lowestHumidityTempFile == null){
              lowestHumidityTempFile = currentFile;
          }
          else {
              lowestHumidityTempFile = getLowestHumidityOfTWOFiles(lowestHumidityTempFile, currentFile);
          }
       }
       printLowestHumidityManyFilesDetail(lowestHumidityTempFile);
    }
   
    private void printLowestHumidityManyFilesDetail(File f){
       String fileName = f.getName();
       FileResource fr = new FileResource(f);
       CSVParser parser = fr.getCSVParser();
       CSVRecord lowestHumidityTempRecordInFile = lowestHumidityInFile(parser); 
       System.out.println("Lowest Humidity was " + lowestHumidityTempRecordInFile.get("Humidity") + " " + lowestHumidityTempRecordInFile.get("DateUTC"));
    }
    
    public void testLowestHumidityInManyFiles() {
        lowestHumidityInManyFiles();
    }
    
    private File getLowestHumidityOfTWOFiles(File file1, File file2){
       File lowestHumidityTempFile = null;
       
       FileResource fr1 = new FileResource(file1);
       FileResource fr2 = new FileResource(file2);
       
       CSVParser parser1 = fr1.getCSVParser();
       CSVParser parser2 = fr2.getCSVParser();
       
       CSVRecord lowestHumidityTempRecordInFile1 = lowestHumidityInFile(parser1);
       CSVRecord lowestHumidityTempRecordInFile2 = lowestHumidityInFile(parser2);
       
       Double lowestHumidityFromFile1 = Double.parseDouble(lowestHumidityTempRecordInFile1.get("Humidity"));
       Double lowestHumidityFromFile2 = Double.parseDouble(lowestHumidityTempRecordInFile2.get("Humidity"));
       
       if (lowestHumidityFromFile1 < lowestHumidityFromFile2){
           lowestHumidityTempFile = file1;
       } else {
           lowestHumidityTempFile = file2;
       }
       
       return lowestHumidityTempFile;
   }
      
   private CSVRecord coldestHourInFile(CSVParser parser){
       CSVRecord coldestTemp = null; 
       for(CSVRecord record: parser){
           if (!record.get("TemperatureF").equals("-9999")){
          
           if (coldestTemp == null){
                   coldestTemp = record;
           }
           else {
               double currentTemp = Double.parseDouble(record.get("TemperatureF"));
               double currentColdestTemp = Double.parseDouble(coldestTemp.get("TemperatureF"));
                if (currentTemp < currentColdestTemp){
                       coldestTemp = record;
                }
                
           }
           //System.out.println(record.get("TemperatureF"));
       }
       }
   return coldestTemp;
   }
   
   
   private File getColdestOfTWOFiles(File file1, File file2){
       File coldestTempFile = null;
       
       FileResource fr1 = new FileResource(file1);
       FileResource fr2 = new FileResource(file2);
       
       CSVParser parser1 = fr1.getCSVParser();
       CSVParser parser2 = fr2.getCSVParser();
       
       CSVRecord coldestTempRecordInFile1 = coldestHourInFile(parser1);
       CSVRecord coldestTempRecordInFile2 = coldestHourInFile(parser2);
       
       Double coldestTempFromFile1 = Double.parseDouble(coldestTempRecordInFile1.get("TemperatureF"));
       Double coldestTempFromFile2 = Double.parseDouble(coldestTempRecordInFile2.get("TemperatureF"));
       
       if (coldestTempFromFile1 < coldestTempFromFile2){
           coldestTempFile = file1;
       } else {
           coldestTempFile = file2;
       }
       
       return coldestTempFile;
   }
   
   private String getDate(String fileName){
       String date = "";
       int indexOffirshDash = fileName.indexOf("-");
       int indexOfDotCSV = fileName.indexOf(".csv");
       date = fileName.substring(indexOffirshDash+1, indexOfDotCSV);
       return date; 
   }
   
   private void printColdestTempFileDetails(File f){
       String fileName = f.getName();
       FileResource fr = new FileResource(f);
       CSVParser parser = fr.getCSVParser();
       CSVRecord coldestTempRecordInFile = coldestHourInFile(parser);
       System.out.println("Coldest day was in file " + fileName);
       System.out.println("Coldest temperature on that day was " + coldestTempRecordInFile.get("TemperatureF"));
       System.out.println("All the Temperatures on the coldest day were:");
       CSVParser parser2 = fr.getCSVParser();
       for(CSVRecord record : parser2){
           String dateTime = record.get("DateUTC");
           String temp = record.get("TemperatureF");
           System.out.println(dateTime + " " + temp);
       }
   }
   
   private String fileWithColdestTemperature(){
       File coldestTempFile = null;
       DirectoryResource dr = new DirectoryResource();
       for(File currentFile: dr.selectedFiles()){
          if (coldestTempFile == null){
              coldestTempFile = currentFile;
          }
          else {
              coldestTempFile = getColdestOfTWOFiles(coldestTempFile, currentFile);
          }
       }
       printColdestTempFileDetails(coldestTempFile);
       return coldestTempFile.getName();
   }
   
   public void testColdestHourInFile(){
       FileResource fr = new FileResource();
       CSVParser parser = fr.getCSVParser();
       CSVRecord coldestTemp = coldestHourInFile(parser);
       System.out.println("Coldest Temp: " + coldestTemp.get("TemperatureF"));
   }

   public void testFileWithColdestTemperature(){
       String coldestTempFileName = fileWithColdestTemperature();
    }
    
   public void testLowestHumidityInFile(){
       FileResource fr = new FileResource();
       CSVParser parser = fr.getCSVParser();
       CSVRecord csv = lowestHumidityInFile(parser);
   }
}
