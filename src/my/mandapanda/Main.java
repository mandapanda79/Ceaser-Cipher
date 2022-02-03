package my.mandapanda;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;


public class Main {
    public static void main(String[] args) {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(isr);
        System.out.print("Decrypt or Encrypt?");
        String status = "";
        try{
            status = in.readLine();
        }catch(IOException e){
            e.printStackTrace();
        }
        String code = "";
        if(Objects.equals(status, "Encrypted")){
            System.out.print("Enter your plaintext: ");
            try {
                code = in.readLine();
                code = code.toLowerCase();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(Objects.equals(status, "Decrypted")){
            System.out.print("Enter your ciphertext: ");
            try {
                code = in.readLine();
                code = code.toLowerCase();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.print("Enter the shift amount(0-25): ");
        int shift = 0;
        int count = 0;
        try {
            shift = Integer.parseInt(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String encrypt = "";
        if (Objects.equals(status, "Encrypted")){
            if (shift > 25) {
                System.out.print("Cannot shift by more than 25 characters.");
            } else {

                System.out.print("Coded: " + encryptedText(code, shift, count));
            }
        }else if(Objects.equals(status, "Decrypted")){
            if(shift > 25){
                System.out.print("Cannot shift by more than 25 characters.");
            }else{

                System.out.print("Decoded: " + decryptedText(code, shift, count));
            }
        }else{
            System.out.println("No valid input");
        }
    }

    /*
    Method to create encrypted text
     */

    private static String encryptedText(String code, int shift, int count) {
        String encrypt = "";
        if(shift != 0) {
            while (count < code.length()) {
                char add = characterShift(code, shift, count);
                encrypt += add;
                count++;
            }
        }else{
            encrypt = "A shift of 0 or 26 is not secure.";
        }
        return encrypt;
    }

    /*
    Decrypt the input
     */

    private static String decryptedText(String code, int shift, int count) {
        String decrypt = "";
        if(shift != 0) {
            while (count < code.length()) {
                char add = characterShiftDown(code, shift, count);
                decrypt += add;
                count++;
            }
        }else{
            decrypt = "A shift of 0 or 26 is not secure.";
        }
        return decrypt;
    }

   /*
    Method to shift characters
    */
    public static char characterShift(String code, int shift, int count){
        char add2 = ' ';
        if(code.charAt(count) != ' '){
            add2 = (char) (code.charAt(count) + shift);
            if(add2 > 'z'){
                add2 -= 26;
            }
        }else{
            add2 = ' ';
        }
        return add2;
    }

    /*
    Method to shift characters and decrypt
     */
    public static char characterShiftDown(String code, int shift, int count){
        char add2 = ' ';
        if(code.charAt(count) != ' '){
            add2 = (char) (code.charAt(count) - shift);
            if(add2 < 'a'){
                add2 += 26;
            }
        }else{
            add2 = ' ';
        }
        return add2;
    }

}
