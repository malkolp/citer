import com.malk.citer.Citer;

public class Runner {

    public static void main(String[] args) {

        Citer database = new Citer("localhost","3306","root","");
        database.createDatabase("SQLMAP_LIST");
        database.deleteDatabase("SQLMAP_LIST");
        database.close();

    }

}
