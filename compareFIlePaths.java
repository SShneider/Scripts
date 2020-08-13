
import java.io.*;  
import java.util.Scanner; 
class Main {
  public static void main(String[] args) {
    final long startTime = System.currentTimeMillis();
    boolean ransomRead = false;
    boolean pathRead = true;
    boolean saveRead = false;
    FileReader ransomewarePaths = null;
    FileReader cloudPaths = null;
    FileWriter resultFile = null;
    //////TRY OPENING RANSOMWAREPATHS START///////
    cloudPaths = openCloudPath();
    if(cloudPaths == null){
      System.out.println(cloudPaths);
      pathRead = false;
    }
    try {
      ransomewarePaths = new FileReader("ransomeware_paths.txt");
      ransomRead = true;
    }
    catch(Exception e){
      System.out.println("Couldn't open ransomeware file");
      e.printStackTrace();
    }
    //////TRY OPENING RANSOMWAREPATHS END///////
    //////TRY CREATING A NEW FILE START//////
    try{
      File file = new File("save_results.txt");
      resultFile = new FileWriter(file, true);
      saveRead = true;
    }
    catch(Exception e){
      System.out.println("Couldn't read save file");
      e.printStackTrace();
    }
    int count = 0;
    //////TRY CREATING A NEW FILE END///////
    if(ransomRead && saveRead && pathRead){
        Scanner scRansom = new Scanner(ransomewarePaths);
        //Scanner scCloud = new Scanner(cloudPaths);
        Scanner scCloud = null;
        String matchedCloud = "Matched Cloud Path: ";
        String matchedLocal = "Matched Local Path: ";
        String matched = "<--------------------MATCHED-------------------->";
        while(scRansom.hasNextLine()){
          cloudPaths = openCloudPath();
          scCloud = new Scanner(cloudPaths);
          String lineOne = scRansom.nextLine();
          String processedLineOne = lineOne.substring(0, lineOne.length()-7);
          while(scCloud.hasNextLine()){
            String lineTwo = scCloud.nextLine();
            lineTwo = lineTwo.substring(0, lineTwo.length()-1);
            if(processedLineOne.equalsIgnoreCase(lineTwo)){
              String toSave = matched+"\n"+matchedCloud+lineTwo+"\n"+matchedLocal+processedLineOne;
              //System.out.println(toSave);
              try{
                 resultFile.write(toSave);
              }catch(Exception e){
                System.out.println("Fucked");
                  e.printStackTrace();
              }
               
              // resultFile.write(matchedCloud+lineTwo);
              // resultFile.write(matchedLocal+processedLineOne);
              // System.out.println("Match");
              // System.out.println(processedLineOne);
              // System.out.println(lineTwo);
            }
          }
            scCloud.close();
             //System.out.println("end loop");
        } 
         scRansom.close();
         final long endTime = System.currentTimeMillis();
         System.out.println("Total execution time: " + (endTime - startTime)/1000 + "s");
    }
  }  
  public static FileReader openCloudPath(){

    FileReader cloudPaths = null;
    try {
      cloudPaths = new FileReader("cloud_paths.txt");
    }
    catch(Exception e){
      System.out.println("Couldn't open cloud file");
      e.printStackTrace();
    }
    return cloudPaths;
  }

}