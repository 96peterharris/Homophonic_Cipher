package home;

import java.util.*;

public class Encrypt {

    HashMap<Character, Integer> alphabet = new HashMap<>();
    HashMap<Character, ArrayList<Integer>>  keyMap = new HashMap<>();
    ArrayList<Integer> numberArray = new ArrayList<>();
    private ArrayList<Character> charArray = new ArrayList<>();
    private ArrayList<Integer> intArrayEncrypted = new ArrayList<>();
    private String textToEncrypt = "";
    private String upperText = "";
    private String encryptedText = "";

//    ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
//   private void fillMatrix(){
//       int j = 0;
//       for(int i = 0; i < 261; i++){
//           ArrayList<Integer> temp = new ArrayList<>();
//           for(int k = 0; k < 261; k++){
//               temp.add(j);
//               j++;
//           }
//           this.matrix.add(temp);
//       }
//       for(ArrayList<Integer> l : matrix){
//           System.out.println(l);
//       }
//   }

    private Integer countSubstitutes(float value){
        Integer temp = Math.round((value * 6900) / 100);
        return temp;
    }

    public Encrypt(){
        this.alphabet.put(' ', this.countSubstitutes(0.03f));
        this.alphabet.put('A', this.countSubstitutes(8.89f));
        this.alphabet.put('Ą', this.countSubstitutes(0.99f));
        this.alphabet.put('B', this.countSubstitutes(1.47f));
        this.alphabet.put('C', this.countSubstitutes(3.96f));
        this.alphabet.put('Ć', this.countSubstitutes(0.4f));
        this.alphabet.put('D', this.countSubstitutes(3.25f));
        this.alphabet.put('E', this.countSubstitutes(7.65f));
        this.alphabet.put('Ę', this.countSubstitutes(1.11f));
        this.alphabet.put('F', this.countSubstitutes(0.3f));
        this.alphabet.put('G', this.countSubstitutes(1.42f));
        this.alphabet.put('H', this.countSubstitutes(1.08f));
        this.alphabet.put('I', this.countSubstitutes(8.21f));
        this.alphabet.put('J', this.countSubstitutes(2.28f));
        this.alphabet.put('K', this.countSubstitutes(3.51f));
        this.alphabet.put('L', this.countSubstitutes(2.1f));
        this.alphabet.put('Ł', this.countSubstitutes(1.82f));
        this.alphabet.put('M', this.countSubstitutes(2.8f));
        this.alphabet.put('N', this.countSubstitutes(5.52f));
        this.alphabet.put('Ń', this.countSubstitutes(0.2f));
        this.alphabet.put('O', this.countSubstitutes(7.75f));
        this.alphabet.put('Ó', this.countSubstitutes(0.85f));
        this.alphabet.put('P', this.countSubstitutes(3.13f));
        this.alphabet.put('Q', this.countSubstitutes(0.14f));
        this.alphabet.put('R', this.countSubstitutes(4.69f));
        this.alphabet.put('S', this.countSubstitutes(4.32f));
        this.alphabet.put('Ś', this.countSubstitutes(0.66f));
        this.alphabet.put('T', this.countSubstitutes(3.98f));
        this.alphabet.put('U', this.countSubstitutes(2.5f));
        this.alphabet.put('V', this.countSubstitutes(0.04f));
        this.alphabet.put('W', this.countSubstitutes(4.65f));
        this.alphabet.put('X', this.countSubstitutes(0.02f));
        this.alphabet.put('Y', this.countSubstitutes(3.76f));
        this.alphabet.put('Z', this.countSubstitutes(5.64f));
        this.alphabet.put('Ź', this.countSubstitutes(0.06f));
        this.alphabet.put('Ż', this.countSubstitutes(0.83f));
        this.fillNumberArray();
    }

    //This function return encrypted text
    public String getEncrypted() {
        return this.encryptedText;
    }

    //This function set text which will be encrypt
    public void setTextToEncrypt(String text) {
        this.textToEncrypt = text;
    }

    //This function change letters in text which will be encrypt to upper case
    private void toUpper() {

        this.upperText = this.textToEncrypt.toUpperCase();
    }

    //This function split upper case text into array
    private void toArray() {
        for (int i = 0; i < this.upperText.length(); i++) {
            this.charArray.add(i,this.upperText.charAt(i));
        }
    }

    //this function merge encrypted characters into string
    private void mergeEncrypted() {
        for(Integer c : this.intArrayEncrypted){
            this.encryptedText += c + " ";
        }
    }

    private Integer randInt(int min, int max){
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return (Integer) randomNum;
    }

    private void fillNumberArray(){
        for(int i = 0; i < 69000; i++){
            this.numberArray.add(i);
        }
        Collections.shuffle(this.numberArray);
    }

    private ArrayList<Integer> generateSubstitutes(int occurence){
        ArrayList<Integer> temp = new ArrayList<>();
        for(int i = 0; i < occurence; i++){
            Integer tempInt = this.numberArray.get(i);
            temp.add(tempInt);
            this.numberArray.remove(i);
        }
        return temp;
    }

    private void fillKeyMap(){
        for(Map.Entry<Character, Integer> entry : this.alphabet.entrySet()){
            this.keyMap.put(entry.getKey(),generateSubstitutes(entry.getValue()));
        }
    }

    private void encryptAlgorithm() {
        for(Character c : this.charArray) {
            ArrayList<Integer> temp = this.keyMap.get(c);
            int index = 0;
            if(temp != null){
                if(temp.size() > 1) {
                    index = (int) ((Math.random() * (temp.size())) + 0);
                    Integer tempC = temp.get(index);
                    this.intArrayEncrypted.add(tempC);
                }else{
                    Integer tempC = temp.get(index);
                    this.intArrayEncrypted.add(tempC);
                }
            }else{
                Integer notRecognize = (int) c;
                this.intArrayEncrypted.add(notRecognize);
            }
        }
    }

    public void setKeyMap(HashMap<Character, ArrayList<Integer>> keyMap){
        this.keyMap = keyMap;
    }

    public HashMap<Character, ArrayList<Integer>> getKeyMap(){
        return this.keyMap;
    }

    public void encrypt() {
        toUpper();
        toArray();
        encryptAlgorithm();
        mergeEncrypted();
    }

    public void generateRandom(){
        Collections.shuffle(this.numberArray);
        this.fillKeyMap();
    }

}
