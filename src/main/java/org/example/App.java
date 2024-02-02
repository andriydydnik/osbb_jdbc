package org.example;

import org.apache.log4j.Logger;
import org.example.data.Member;
import org.example.data.OsbbCrud;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Hello world!
 */
public class App {
    private static final Logger logger = Logger.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("Program has started");

        try (OsbbCrud crud = OsbbCrud.getInstance()) {
            for (Member member : crud.getMembersWithAutoNotAllowed()) {
                final StringBuffer sb = new StringBuffer();
                sb.append(member.getId())
                        .append(" : ")
                        .append(member.getName())
                        .append(" : ")
                        .append("\r\n");
                System.out.println(sb);
            }

            logger.info("Program has successfully finished");
        } catch (IOException | SQLException e) {
            logger.fatal(e);
        }
    }
}
