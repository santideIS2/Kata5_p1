package k5p1;

import java.util.List;
import java.io.IOException;

public class K5p1 {

    public static void main(String[] args) throws IOException{

        MailListReader rdr = new MailListReader();
        List<String> maillist = rdr.read("email.txt");
        DBUtils app = new DBUtils(maillist);
        app.createNewTable();
        app.insert();
    }
    
}
