package JUnitTest;

import model.DatabaseExtractor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DatabaseExtractorTest {

    @Test
    void getTextFromDatabase() {
        DatabaseExtractor testExtractor = new DatabaseExtractor();
        testExtractor.openDatabase();
        ArrayList<String> arrayList = testExtractor.getAllLine();
        boolean b = arrayList.isEmpty();
        assertEquals(false, b);
    }

    @Test
    public void noDuplicate() {
        DatabaseExtractor test = new DatabaseExtractor();
        String[] arr= test.bankOfWords();
        boolean noDuplicate = true;
        for(int i=0; i<arr.length;i++){
            for (int k=i+1;k<arr.length;k++){
                if(arr[i]==arr[k])
                    noDuplicate= false;
            }
        }
        assertEquals(true,noDuplicate);
    }

    @Test
    public void takeAllLine() {
        DatabaseExtractor test = new DatabaseExtractor();
        test.importTheDatabase();
        ArrayList<String> arrL = test.getAllLine();
        boolean goodLength = arrL.size() == 100;

        assertEquals(true,goodLength);
    }

    @Test
    void noNull() {
        DatabaseExtractor test = new DatabaseExtractor();
        String[] arr= test.bankOfWords();
        boolean noNull = true;
        for(int i=0; i<arr.length;i++){
            if(arr[i]==null)
                noNull=false;
        }
        assertEquals(true,noNull);
    }

}

