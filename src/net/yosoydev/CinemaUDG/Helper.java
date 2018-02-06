package net.yosoydev.CinemaUDG;

/**
 *
 * @author jesus
 */
public class Helper {
    
    private static String computeSpaces(String str) {
        int size = 62;
        int left = (size - str.length()) / 2;
        int right = size - left - str.length();
        String repeatedChar = " ";
        StringBuilder buff = new StringBuilder();
        for (int i = 0; i < left; i++) {
            buff.append(repeatedChar);
        }
        buff.append(str);
        for (int i = 0; i < right; i++) {
            buff.append(repeatedChar);
        }
        return buff.toString();
    }
    
    /**
     * Centra el texto en pantalla con salto de 
     * linea.
     * @param str
     */
    public static void println(String str) {
        System.out.println(computeSpaces(str));
    }
    
    /**
     * Centra el texto en pantalla sin salto de 
     * linea.
     * @param str
     */
    public static void print(String str) {
        System.out.print(computeSpaces(str));
    }
    
    /**
     * Genera un numero aleatorio entre n y m
     * @param n
     * @param m
     * @return 
     */
    public static int obtenerNumeroAleatorio(int n, int m) {
        return (int) (Math.random() * m) + n;
    }
}
