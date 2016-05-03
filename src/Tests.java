import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Tests {

    /**
     * @param args
     */
    public static void main(String[] args) {
    String a = "2013/12/10 18:00:17,363 GMT - INFO - domain.DomainLogger - State Machine xml file: C:/ssmclient/config/sm/SSMAppStateProcess.xml";
    SimpleDateFormat b = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss,SSS z");
    
    Date fecha;
    try {
        fecha = b.parse(a);
        System.out.println(fecha.toString());
    } catch (ParseException e) {
        System.out.println("catch");
        e.printStackTrace();
    }
    
    }

}
