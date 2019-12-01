import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Decryptor {

    HashMap<Integer,Integer> freq = new HashMap<>();
    ArrayList<Cryptogram> cryptograms = new ArrayList<>();


    public Decryptor(){
        freq.put((int)' ', 150);
        freq.put((int)'a', 89);
        freq.put((int)'i', 82);
        freq.put((int)'o', 78);
        freq.put((int)'e', 77);
        freq.put((int)'z', 56);
        freq.put((int)'n', 55);
        freq.put((int)'r', 47);
        freq.put((int)'w', 47);
        freq.put((int)'s', 43);
        freq.put((int)'t', 40);
        freq.put((int)'c', 40);
        freq.put((int)'y', 38);
        freq.put((int)'k', 35);
        freq.put((int)'d', 33);
        freq.put((int)'p', 31);
        freq.put((int)'m', 28);
        freq.put((int)'u', 25);
        freq.put((int)'j', 23);
        freq.put((int)'l', 21);
        freq.put((int)'b', 15);
        freq.put((int)'g', 14);
        freq.put((int)'h', 11);
        freq.put((int)'f', 3);
        freq.put((int)'q', 1);
        freq.put((int)'v', 1);
        freq.put((int)'x', 1);
//        freq.put((int)',', 10);
//        freq.put((int)'.', 10);
//        freq.put((int)'-', 10);
//        freq.put((int)'"', 10);
//        freq.put((int)'!', 10);
//        freq.put((int)'?', 10);
//        freq.put((int)':', 10);
//        freq.put((int)';', 10);
//        freq.put((int)'(', 10);
//        freq.put((int)')', 10);
//
//        for (int i = 65; i < 91; i++) {
//            freq.put(i, 10);
//        }
//        for (int i = 48; i <= 57; i++) {
//            freq.put(i, 10);
//        }
     }

    public void get_cryptograms(String filepath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            Cryptogram cryptogram;
            while ((line = reader.readLine()) != null) {
                cryptogram = new Cryptogram(line);
                cryptograms.add(cryptogram);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Integer> decrypt(){
        ArrayList<Integer> key = new ArrayList<>();
        int longest = 0;
        for (Cryptogram cryptogram: cryptograms
             ) {
            if(cryptogram.chars.size()>longest) longest = cryptogram.chars.size();
        }

        for ( int i = 0;i<longest; i++){
            HashMap<Integer, Integer> kay_candidate = new HashMap<>();
            ArrayList<Cryptogram> match_crypto = new ArrayList<>();

            for (Cryptogram crypt:cryptograms
                 ) {
                if(crypt.chars.size() > i) match_crypto.add(crypt);
            }
            for (Cryptogram crypt:match_crypto
            ) {
                for (Integer possible:freq.keySet()
                     ) {
                    Integer tmp = crypt.get_char(i) ^ possible;
                    Integer tmp2 = freq.get(possible);

                    if(!kay_candidate.containsKey(tmp)){ kay_candidate.put(tmp,tmp2);
                    }
                    else kay_candidate.put(tmp,kay_candidate.get(tmp) + freq.get(possible));
                }
            }

            int max_candidate = (int) ' ';
            int max_match = 0;
            for (Integer possible:kay_candidate.keySet()
                 ) { int counter = 0;

                for (Cryptogram crypt: match_crypto
                     ) {    if(freq.containsKey(crypt.get_char(i) ^ possible)) counter++;
                    
                }
                if(counter>max_match){
                    max_candidate = possible;
                    max_match = counter;
                }
                
            }
            key.add(max_candidate);

        }
        return key;
    }

    public void hax(){
        ArrayList<Integer> key = this.decrypt();
        for (Cryptogram crypt: cryptograms
             ) {

            for(int i=0;i<crypt.chars.size();i++){
                System.out.print(String.valueOf(Character.toChars(crypt.get_char(i) ^ key.get(i))));
            }
            System.out.println();
            
        }
    }
}
