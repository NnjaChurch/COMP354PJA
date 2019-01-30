import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class DatabaseExtractor {

    ArrayList<SimpleCard> database=new ArrayList<SimpleCard>();
    ArrayList<String> allLine;

    void openDatabase(){

        Scanner s = null;
        try {
            s = new Scanner(new File("/home/karim/IdeaProjects/comp_354_Model/src/Imports_Test.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<String> list = new ArrayList<String>();
        while (s.hasNextLine()){
            list.add(s.nextLine());
        }
        s.close();

        this.allLine=list;

    }

    void printTheDatabase(){

        for (String temp : this.allLine) {
            System.out.println(temp);
        }


    }

    static String[] getAllTheHints(String line){
        String allHints;
        String [] arrayOfHints;

        int  beginningIndex =line.indexOf("hints:") + 6;
        int endingIndex=line.length()-1;

        allHints= line.substring(beginningIndex, endingIndex);
        System.out.println(allHints);

        arrayOfHints = allHints.split(",");
        for(int k=0 ; k < arrayOfHints.length; k++){
          arrayOfHints[k]= arrayOfHints[k].replaceAll(" ", "");
        }

        for(String temp: arrayOfHints){
            System.out.println(temp);
        }
        return arrayOfHints;
    }

    static String getTheWord(String line){

        String word;

        int beginningIndex= line.indexOf("word:")+6;
        word= line.substring(beginningIndex);
        int endingIndex= word.indexOf(" ");
        word=word.substring(0,endingIndex);

        return  word;
    }


    void importTheDatabase(){

        for(String temp: this.allLine){
            database.add(new SimpleCard(getTheWord(temp),getAllTheHints(temp)));
        }

    }


    void printDatabase(){
        for(SimpleCard temp: this.database){
            System.out.println(temp.toString());
        }
    }


}
