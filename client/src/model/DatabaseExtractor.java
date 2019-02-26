package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class DatabaseExtractor {

    ArrayList<SimpleCard> database=new ArrayList<SimpleCard>();
    ArrayList<String> allLine;

    public ArrayList<String> getAllLine() {
        return this.allLine;
    }

    public ArrayList<SimpleCard> getDatabase() {
        return this.database;
    }

    public void openDatabase(){ //this method simply import the text file and store every line in the arraylist allLine

        Scanner s = null;
        try {
            s = new Scanner(new File("./src/CodenameDatabase.txt"));
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

    public void printTheDatabase(){ // print the txt file line by line

        for (String temp : this.allLine) {
            System.out.println(temp);
        }
    }

    public static String[] getAllTheHints(String line){ //take a line of the database as an argument and give you an array of all
                                                    //the possible hints linked with that word
        String allHints;
        String [] arrayOfHints;

        int  beginningIndex =line.indexOf("hints:") + 6;
        int endingIndex=line.length()-1;

        allHints= line.substring(beginningIndex, endingIndex);


        arrayOfHints = allHints.split(",");
        for(int k=0 ; k < arrayOfHints.length; k++){
          arrayOfHints[k]= arrayOfHints[k].replaceAll(" ", "");
        }

        return arrayOfHints;
    }

    public static String getTheWord(String line){//take a line of the database as an argument and give you an array of all
                                             //the possible hints linked with that word

        String word;

        int beginningIndex= line.indexOf("word:")+6;
        word= line.substring(beginningIndex);
        int endingIndex= word.indexOf(" ");
        word=word.substring(0,endingIndex);

        return  word;
    }


    public void importTheDatabase(){//take every line of the text file and transform every line into an object SimpleCard

        this.openDatabase(); //write every line in the attribute allLine
        for(String temp: this.allLine){
            database.add(new SimpleCard(getTheWord(temp),getAllTheHints(temp)));
        }

    }


    public void printDatabase(){ //print every object one by one used for testing purposes
        for(SimpleCard temp: this.database){
            System.out.println(temp.toString());
        }
    }

    public String[] bankOfWords(){ //return an array of 25 random word(string)
        this.importTheDatabase();
        Random rand = new Random();
        SimpleCard temp;
        int n;

        String[] arrayOfWords= new String[25];

        for(int i=0; i<arrayOfWords.length;i++){

             n = rand.nextInt(this.database.size()-1) + 0;
             temp= this.database.get(n);
             arrayOfWords[i]=temp.getWord();
             this.database.remove(n);

        }

        return arrayOfWords ;
    }


    public SimpleCard[] bankOfCard(){ //return array of 25 SimpleCard so a word with an array of hints
        this.importTheDatabase();
        Random rand = new Random();
        int n;

        SimpleCard[] arrayOfCards= new SimpleCard[25];

        for(int i=0; i<arrayOfCards.length;i++){

            n = rand.nextInt(this.database.size()-1) + 0;
            arrayOfCards[i]=this.database.get(n);
            this.database.remove(n);

        }

        return arrayOfCards ;
    }


}
