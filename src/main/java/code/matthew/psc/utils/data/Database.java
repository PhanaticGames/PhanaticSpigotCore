package code.matthew.psc.utils.data;

import code.matthew.psc.PSC;
import code.matthew.psc.utils.logs.Logger;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {

    @Getter
    public HikariDataSource hikariDataSource;

    private Connection conn;

    private PSC psc;

    public Database(PSC psc) {
        this.psc = psc;
        init();
    }

    private void init() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://" + psc.getFiles().getMySQLProp().getProperty("host") + ":" + psc.getFiles().getMySQLProp().getProperty("port") + "/" + psc.getFiles().getMySQLProp().getProperty("db"));
        config.setPassword(psc.getFiles().getMySQLProp().getProperty("password"));
        config.setUsername(psc.getFiles().getMySQLProp().getProperty("user"));
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.addDataSourceProperty("useServerPrepStmts", "true");
        hikariDataSource = new HikariDataSource(config);
    }

    public void connect() {
        try {
            conn = hikariDataSource.getConnection();
        } catch (SQLException ex) {
            Logger.log(Logger.LogType.ERROR, "ERROR GETTING DATABASE CONNECTION");
            if (Logger.isDebug()) {
                ex.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        if (hikariDataSource != null) {
            if (conn != null) {
                return conn;
            } else {
                try {
                    conn = hikariDataSource.getConnection();
                    return conn;
                } catch (SQLException ex) {
                    Logger.log(Logger.LogType.ERROR, "ERROR GETTING DATABASE CONNECTION");
                    if (Logger.isDebug()) {
                        ex.printStackTrace();
                    }
                    return null;
                }
            }
        } else {
            init();
            return getConnection();
        }
    }

    public PreparedStatement preparePreparedStmt(String sql, String... data) {
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            if (data != null && data.length > 0) {
                for (int i = 0; i < data.length; i++) {
                    String param = data[i];
                    stmt.setString(i + 1, param);
                }
            }
            return stmt;
        } catch (SQLException ex) {
            Logger.log(Logger.LogType.ERROR, "ERROR PREPARING STATMENT");
            if (Logger.isDebug()) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public ResultSet runQuery(PreparedStatement stmt) {
        try {
            return stmt.executeQuery();
        } catch (SQLException ex) {
            Logger.log(Logger.LogType.ERROR, "ERROR EXECUTING QUERY STATEMENT");
            if (Logger.isDebug()) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public boolean runUpdate(PreparedStatement stmt) {
        try {
            return stmt.execute();
        } catch (SQLException ex) {
            Logger.log(Logger.LogType.ERROR, "ERROR EXECUTING UPDATE STATEMENT");
            if (Logger.isDebug()) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    public void close() {
        if (hikariDataSource != null) {
            hikariDataSource.close();
        }
    }

}
