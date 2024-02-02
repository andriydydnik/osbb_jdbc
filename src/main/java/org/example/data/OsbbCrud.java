package org.example.data;

import org.apache.log4j.Logger;
import org.flywaydb.core.Flyway;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.example.Config.*;

public class OsbbCrud implements Closeable {
    private static final Logger logger = Logger.getLogger(OsbbCrud.class);
    private static final String sqlMembersWithAutoNotAllowedQuery = "SELECT \n" +
            "    m.*\n" +
            "FROM\n" +
            "    members m\n" +
            "WHERE\n" +
            "    id IN (SELECT \n" +
            "            m.id\n" +
            "        FROM\n" +
            "            members m\n" +
            "                INNER JOIN\n" +
            "            members_role mr ON mr.member_id = m.id\n" +
            "                INNER JOIN\n" +
            "            properties p ON p.member_id = m.id\n" +
            "                INNER JOIN\n" +
            "            tanents t ON t.member_id = m.id\n" +
            "        WHERE\n" +
            "            mr.role = 'member' AND NOT t.auto_allow)";
    private static OsbbCrud instance;
    private Connection conn = null;

    private OsbbCrud() {
    }

    public static synchronized OsbbCrud getInstance() throws SQLException {
        if (instance == null)
            (instance = new OsbbCrud())
                    .fwMigrate()
                    .init();

        return instance;
    }

    private OsbbCrud fwMigrate() {
        logger.debug("Flyway migration execute");

        Flyway.configure()
                .dataSource(jdbcUrl, username, password)
                .locations("classpath:flyway/scripts")
                .load()
                .migrate();

        return this;
    }

    private OsbbCrud init() throws SQLException {
        conn = DriverManager.getConnection(jdbcUrl, username, password);

        return this;
    }

    @Override
    public void close() throws IOException {
        try {
            conn.close();
            conn = null;
        } catch (SQLException e) {
            throw new IOException(e);
        }
    }

    public List<Member> getMembersWithAutoNotAllowed() throws SQLException {
        logger.trace("Call getting members with auto not allowed method");

        final List<Member> result = new LinkedList<>();
        try (PreparedStatement preparedStatement = conn.prepareStatement(sqlMembersWithAutoNotAllowedQuery)) {
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                result.add(
                        new Member()
                                .setId(resultSet.getInt("id"))
                                .setName(resultSet.getString("names")));
        }

        return result;
    }

    public Optional<Member> getMemberById(final int id) throws SQLException {
        logger.trace("Call getting member by ID method");

        try (PreparedStatement preparedStatement = conn.prepareStatement(sqlMembersWithAutoNotAllowedQuery)) {
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return Optional.of(
                        new Member()
                                .setId(resultSet.getInt("id"))
                                .setName(resultSet.getString("names")));
        }

        return Optional.empty();
    }
}
