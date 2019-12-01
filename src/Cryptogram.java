import java.util.ArrayList;
import java.util.List;

public class Cryptogram {
    public List<Integer> chars = new ArrayList<>();

    public Cryptogram(String cipher){
        String tmp[] = cipher.split(" ");
        for (String ch : tmp
             ) { int a = Integer.parseInt(ch,2);
                chars.add(a);
        }
    }
    public Integer get_char(int index){
        if(index<chars.size()){
            return chars.get(index);
        }
        return (int) '&';
    }

    @Override
    public String toString() {
        return this.chars.toString();
    }
}
