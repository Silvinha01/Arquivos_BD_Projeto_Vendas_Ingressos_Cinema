package util;

/**
 * Essa classe serve para trabalhar com data e hora (formatar, verificar).
 *
 * @author silvinha01
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author lhries
 */
public class DateUtil {

    /**
     * Esse método serve para receber String data.
     *
     * @author silvinha01
     */
    public static Date stringToDate(String data) throws ParseException {
        return (new SimpleDateFormat("dd/MM/yyyy").parse(data));
    }

    /**
     * Esse método serve para receber String data e hora.
     *
     * @author silvinha01
     */
    public static Date stringToDateHour(String data) throws ParseException {
        return (new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(data));
    }

    /**
     * Esse método serve para receber String hora.
     *
     * @author silvinha01
     */
    public static Date stringToHour(String hora) throws ParseException {
        return (new SimpleDateFormat("HH:mm").parse(hora));
    }

    /**
     * Esse método serve para formatar String data no padrão brasileiro.
     *
     * @author silvinha01
     */
    public static String dateToString(Date data) {
        return (new SimpleDateFormat("dd/MM/yyyy").format(data));
    }

    /**
     * Esse método serve para formatar String data e hora no padrão brasileiro.
     *
     * @author silvinha01
     */
    public static String dateHourToString(Date data) {
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String dataString = formatador.format(data);
        return (dataString);
    }

    /**
     * Esse método serve para formatar String hora no padrão brasileiro.
     *
     * @author silvinha01
     */
    public static String hourToString(Date hora) {
        SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");
        String horaString = formatador.format(hora);
        return (horaString);
    }

    /**
     * Esse método serve para formatar verificar padrão de data (00/00/0000).
     *
     * @author silvinha01
     */
    public static boolean verificaData(String data) {
        return (data.matches("\\d{2}/\\d{2}/\\d{4}"));
    }

    /**
     * Esse método serve para formatar verificar padrão de data e hora
     * (00/00/0000 - 00:00).
     *
     * @author silvinha01
     */
    public static boolean verificaDataHora(String data) {
        return (data.matches("\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}"));
    }

}
