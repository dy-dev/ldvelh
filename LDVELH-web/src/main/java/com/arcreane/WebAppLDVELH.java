package com.arcreane;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebAppLDVELH {

    public static void main(String[] args) {
        var context = SpringApplication.run(WebAppLDVELH.class, args);
       /* var datasource = context.getBean(DataSource.class);

        Connection con = null;

        try {
            con = datasource.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM Book");
            while (rs.next()){
                System.out.println(	rs.getString("Title"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        finally {
            try{
                if (con != null)
                    con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
*/
    }

}
