import java.util.Random;

// https://www.youtube.com/watch?v=KwnavHTOBiA tutorial
// https://www.youtube.com/watch?v=qI7hTw8aMaU - spring boot
// https://www.youtube.com/watch?v=Z0JfmObjKRw - tutorial spring boot

// import java.util.Scanner;

// class Test {
//     public static void main(String[] args) {

//         Scanner input = new Scanner(System.in);

//         System.out.println("input your name:");
//         String first= input.next();


//         System.out.println("input your name:");
//         String second= input.next();


//         System.out.println(" your name :" + first + " " + second);
//     }
// }

class Generator {
    public static void main(String[] args) {

        String uCode = "ABCDEFGHIJKLMNOPQRSTUWVXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();

        while(salt.length() < 15) {
            int index = (int) (rnd.nextFloat() * uCode.length());
            salt.append(uCode.charAt(index));
        }
        String saltStr = salt.toString();
        System.out.println(saltStr);
    }
}