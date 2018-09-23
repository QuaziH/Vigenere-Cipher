import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class KeyMap {
    public char[][] createTable() {
        char[][] table = new char[27][27];
        Map<Integer, Character> map = new HashMap<Integer, Character>();
//        ArrayList<Integer> list = new ArrayList<Integer>();

        char a = 65;
        for(int i = 1; i < 27; i++) {
            for(char j = a; j < 91;) {
                map.put(i, j);
                break;
            }
            a++;
        }

        int z = 1;
        while(z < 27) {
            table[0][z] = map.get(z);
            table[z][0] = map.get(z);
            z++;
        }

        //Creates a 26 by 26 table filled with random alphabets without any duplicates
//        for(int i = 1; i < table.length; i++) {
//            for(int j = 1; j < table[i].length; j++) {
//                int r = (int) (Math.random() * (27 - 1)) + 1;
//                while(list.contains(r)){
//                    r = (int) (Math.random() * (27 - 1)) + 1;
//                }
//                list.add(r);
//                table[i][j] = map.get(r);
//            }
//            list.clear();
//        }

        //Creates a tabula recta
        int index = 0;
        for(int i = 1; i < table.length; i++) {
            for(int j = 1; j < table[i].length; j++) {
                if(index >= 26)
                    index = 1;
                else
                    index = ++index;
                table[i][j] = map.get(index);
            }
            index = i;
        }

        for(int i = 0; i < table.length; i++) {
            for(int j = 0; j < table[i].length; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }
        return table;
    }
}