import org.junit.Test;

import static junit.framework.Assert.*;

public class SolutionTest {
    @Test
        public void fromFileToDictionaryMethodWithSameWordsOrBadDictionaryTest(){
        Solution solution = new Solution("\\data.txt","\\DictionaryWithBadWord.txt");
        assertEquals("Error! Please check files and words again.",solution.findWayInDictionary());
    }
    @Test
    public void fromFileToDictionaryMethodWithIncorrectFilesTest(){
        Solution solution = new Solution("\\data.txt","\\unexistedFile.txt");
        assertEquals("File not found, please check the path.",solution.findWayInDictionary());
    }

}
