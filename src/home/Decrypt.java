package home;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Decrypt {

    HashMap<Integer, Character> keyMapRead = new HashMap<>();
    private String textToDecrypt = "";
    private String upperText = "";
    private ArrayList<Integer> charArray = new ArrayList<>();
    private ArrayList<Character> charArrayDecrypted = new ArrayList<>();
    private String decryptedText = "";
    int numOfElements = 0;

    public Decrypt(){}

    private void readKeyMap(HashMap<Character, ArrayList<Integer>> keyMap){
        for(Map.Entry<Character, ArrayList<Integer>> entry : keyMap.entrySet()){
            Character tempChar = entry.getKey();
            ArrayList<Integer> tempArray = entry.getValue();
            for(int i = 0; i < tempArray.size(); i++){
                this.keyMapRead.put(tempArray.get(i),tempChar);
            }
        }
    }

    protected String getDecrypted() {
        return this.decryptedText;
    }

    protected void setTextToDecrypt(String text) {
        this.textToDecrypt = text;
    }

    private void toArray() {
        String[] temp = this.textToDecrypt.split(" ");
        for(String s : temp){
            this.charArray.add(Integer.valueOf(s));
        }
    }

    private void mergeDecrypted() {
        for(Character c : this.charArrayDecrypted){
            this.decryptedText += c;
        }
    }

    private void decryptAlgorithm() {
        for(Integer i : this.charArray) {
            Character temp = this.keyMapRead.get(i);
            if(temp != null){
                this.charArrayDecrypted.add(temp);
                this.numOfElements++;
            }else{
                this.charArrayDecrypted.add(Character.forDigit(i,10));
            }
        }
    }

    protected void decrypt(HashMap<Character, ArrayList<Integer>> keyMap){
        readKeyMap(keyMap);
        toArray();
        decryptAlgorithm();
        mergeDecrypted();
    }

    protected boolean checkKey(){
        if(this.numOfElements == this.charArrayDecrypted.size()){
            return true;
        }else{
            return false;
        }
    }

}
