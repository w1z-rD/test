import java.io.*;
import java.util.ArrayList;


public class Solution {
    private static String firstWord, lastWord, firstFilePath,secondFilePath;
    private static ArrayList<String> dictionaryList, finalDictionary = new ArrayList<>();

    public static String findWayInDictionary(){
        boolean flag;
        try {flag = fromFileToDictionaryMethod(firstFilePath, secondFilePath);}
        catch (FileNotFoundException ex){return "File not found, please check the path.";}
        catch (IOException e){return "Unfortunately there is an error with files";}

        if(flag)searchAndCompareMethod(dictionaryList,0,firstWord,lastWord);
        return printMethod(finalDictionary,firstWord,lastWord);
    }

    public Solution(String firstFilePath, String secondFilePath) {
        this.firstFilePath = firstFilePath;
        this.secondFilePath = secondFilePath;
    }

    private static boolean fromFileToDictionaryMethod(String firstFile, String secondFile) throws FileNotFoundException,IOException{
        try(BufferedReader readerFromFirstFile = new BufferedReader(new FileReader(firstFile));BufferedReader readerFromSecondFile = new BufferedReader(new FileReader(secondFile))) {
            firstWord = readerFromFirstFile.readLine();
            lastWord = readerFromFirstFile.readLine();
            if(firstWord.equalsIgnoreCase(lastWord)) return false;
            if (firstWord == null || lastWord == null) throw new IOException("Files are not correct");

            String tmp;
            dictionaryList = new ArrayList<>();
            while ((tmp = readerFromSecondFile.readLine()) != null) {
                if (tmp.length() == lastWord.length()) dictionaryList.add(tmp);
            }
        }
        return true;
    }
    private static String printMethod(ArrayList<String> list, String firstWord, String lastWord){
        String s = "";
        if(!list.isEmpty()){
            System.out.println(firstWord);
            for(int i = list.size()-1;i<list.size();i++){
                s += list.get(i)+"\n";
            }
                return s+lastWord;
        }
        else return "Error! Please check files and words again.";
    }

    private static void searchAndCompareMethod(ArrayList<String> dic, int startSymbol, String firstWord , String lastWord){
        ArrayList<String> temporaryDictionary = new ArrayList<>();
        for(int i = startSymbol;i<lastWord.length();i++){
            if(!String.valueOf(firstWord.charAt(i)).equalsIgnoreCase(String.valueOf(lastWord.charAt(i)))){
                for(int j = 0; j < dic.size();j++){
                    String tmp = dic.get(j);
                    if(String.valueOf(tmp.charAt(i)).equalsIgnoreCase(String.valueOf(lastWord.charAt(i))) && !tmp.equalsIgnoreCase(lastWord)){
                        temporaryDictionary.add(tmp);
                    }
                    else if(dic.size() == 2) {temporaryDictionary.add(tmp);break;}
                }
                if(!temporaryDictionary.isEmpty()) {
                    if (temporaryDictionary.size() == 1) {
                        finalDictionary.add(temporaryDictionary.get(0));
                        i++;
                        searchAndCompareMethod(temporaryDictionary, i, firstWord, temporaryDictionary.get(0));
                    } else if (temporaryDictionary.size() > 1) {
                        i++;
                        searchAndCompareMethod(temporaryDictionary,i, firstWord, lastWord);
                    }
                }

            }

        }
    }

}