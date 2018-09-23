import javax.swing.*;
import java.io.*;

public class Cryptography{
    public static void main(String[] args) {
        KeyMap keyMap = new KeyMap();
        char[][] table = keyMap.createTable();

        System.out.println();

        System.out.print("The previous table is for you to check your encryption/decryption manually.");

        String option = JOptionPane.showInputDialog(null,"Would you like to Encrypt or Decrypt ?");
        if(option.toLowerCase().equals("encrypt")){
            String key = JOptionPane.showInputDialog(null,"Enter personal key: ");
            String input = JOptionPane.showInputDialog(null,"Enter word to encrypt: ");
            String enc = encrypt(key, input, table);
            JOptionPane.showMessageDialog(null,"Your encrypted message is: " + enc);
        }
        else if(option.toLowerCase().equals("decrypt"))	{
            String key = JOptionPane.showInputDialog("Enter personal key: ").toUpperCase();
            String input = JOptionPane.showInputDialog("Enter word to decrypt: ");
            String dec = decrypt(key, input, table);
            JOptionPane.showMessageDialog(null,"Your decrypted message is: " + dec);
        }

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("Key Map.txt"), "UTF-8"))) {
            for (char[] row : table) {
                for (char column : row) {
                    writer.write(Character.toString(column) + " ");
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            System.out.print("Key Map did not get created.");
        }
    }

    public static String encrypt(String key, String text, char[][] table){
        String res = "";
        char[] keyArr = new char[text.length()];
        char[] textArr = new char[text.length()];
        key = key.toUpperCase();
        text = text.toUpperCase();

        for(int i = 0, j = 0; i < text.length(); i++) {
            if((j % key.length()) == 0) {
                j = 0;
            }
            keyArr[i] = key.charAt(j);
            j++;
        }
        for(int i = 0; i < text.length(); i++) {
            textArr[i] = text.charAt(i);
        }

        int row = 0;
        int col = 0;
        for(int i = 0; i < textArr.length; i++) {
            for(int j = 1; j < table.length; j++) {
                for(int k = 1; k < table[j].length; k++) {
                    if(table[k][0] == keyArr[i])
                        row = k;
                }
                if(table[0][j] == textArr[i])
                    col = j;
            }
            res += Character.toString(table[row][col]);
        }

        return res;
    }

    public static String decrypt(String key, String text, char[][] table){
        String res = "";
        key = key.toUpperCase();
        text = text.toUpperCase();
        char[] keyArr = new char[text.length()];
        char[] textArr = new char[text.length()];

        for(int i = 0, j = 0; i < text.length(); i++) {
            if((j % key.length()) == 0)
                j = 0;
            keyArr[i] = key.charAt(j);
            j++;
        }
        for(int i = 0; i < text.length(); i++) {
            textArr[i] = text.charAt(i);
        }

        int row = 0;
        int col = 0;
        for(int i = 0; i < textArr.length; i++) {
            for(int j = 1; j < table.length; j++) {
                if(keyArr[i] == table[j][0]){
                    row = j;
                }
                for(int k = 1; k < table[j].length; k++) {
                    if(textArr[i] == table[row][k]){
                        col = k;
                    }
                }
            }
            res += Character.toString(table[0][col]);
        }

        return res;
    }
}
