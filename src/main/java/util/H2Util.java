package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class H2Util {
    public static String url = "jdbc:h2:./h2/db";
    public static String username = "admin";
    public static String password = "p@ss";

    public static void createTable() {

        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            String sql = "CREATE TABLE reimbursement(\n" +
                    "\treimb_id SERIAL PRIMARY KEY,\n" +
                    "\treimb_amount DOUBLE PRECISION DEFAULT 0.00,\n" +
                    "\treimb_submitted TIMESTAMP DEFAULT NOW(),\n" +
                    "\treimb_resolved TIMESTAMP DEFAULT NOW(),\n" +
                    "\treimb_description VARCHAR(250) NOT NULL,\n" +
                    "\treimb_receipt BYTEA,\n" +
                    "\treimb_author INT NOT NULL,\n" +
                    "\treimb_resolver INT NOT NULL,\n" +
                    "\treimb_status_id INT NOT NULL,\n" +
                    "\treimb_type_id INT NOT NULL,\n" +
                    "\tCONSTRAINT users_fk_auth FOREIGN KEY (reimb_author) REFERENCES users(user_id),\n" +
                    "\tCONSTRAINT users_fk_reslvr FOREIGN KEY (reimb_resolver) REFERENCES users(user_id),\n" +
                    "\tCONSTRAINT reimbursement_status_fk FOREIGN KEY (reimb_status_id) REFERENCES reimbursementstatus(reimb_status_id),\n" +
                    "\tCONSTRAINT reimbursement_type_fk FOREIGN KEY (reimb_type_id) REFERENCES reimbursementtype(reimb_type_id) \n" +
                    ");\n";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            conn.close();

        } catch (SQLException h) {
            h.printStackTrace();
        }
    }

        public static void dropTable() {

            try {
                Connection conn = DriverManager.getConnection(url, username, password);

                String sql = "DROP TABLE reimbursement";

                PreparedStatement ps = conn.prepareStatement(sql);

                ps.executeUpdate();

                conn.close();

            } catch (SQLException h) {
                h.printStackTrace();
            }

        }
    }

