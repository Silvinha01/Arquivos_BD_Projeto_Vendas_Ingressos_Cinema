package util;

/**
 * Essa classe serve para trabalhar com os tipos de parâmetros (String, int,
 * double, float, boolean, char).
 *
 * @author silvinha01
 */
import java.util.Scanner;

public class Console {

    /**
     * Esse método serve para trabalhar com o tipo de parâmetro String.
     *
     * @author silvinha01
     */
    public static String scanString(Object out) {
        System.out.print(out);
        Scanner scanner = new Scanner(System.in);
        return (scanner.nextLine());
    }

    /**
     * Esse método serve para trabalhar com o tipo de parâmetro int.
     *
     * @author silvinha01
     */
    public static int scanInt(Object out) {
        System.out.print(out);
        Scanner scanner = new Scanner(System.in);
        return (scanner.nextInt());
    }

    /**
     * Esse método serve para trabalhar com o tipo de parâmetro double.
     *
     * @author silvinha01
     */
    public static double scanDouble(Object out) {
        System.out.print(out);
        Scanner scanner = new Scanner(System.in);
        return (scanner.nextDouble());
    }

    /**
     * Esse método serve para trabalhar com o tipo de parâmetro float.
     *
     * @author silvinha01
     */
    public static float scanFloat(Object out) {
        System.out.print(out);
        Scanner scanner = new Scanner(System.in);
        return (scanner.nextFloat());
    }

    /**
     * Esse método serve para trabalhar com o tipo de parâmetro boolean.
     *
     * @author silvinha01
     */
    public static boolean scanBoolean(Object out) {
        System.out.print(out);
        Scanner scanner = new Scanner(System.in);
        return (scanner.nextBoolean());
    }

    /**
     * Esse método serve para trabalhar com o tipo de parâmetro char.
     *
     * @author silvinha01
     */
    public static char scanChar(Object out) {
        System.out.print(out);
        Scanner scanner = new Scanner(System.in);
        return (scanner.next().charAt(0));
    }

}
