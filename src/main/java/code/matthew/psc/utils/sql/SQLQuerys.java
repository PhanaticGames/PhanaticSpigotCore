package code.matthew.psc.utils.sql;

import code.matthew.psc.PSC;
import code.matthew.psc.api.Ban;

import java.sql.PreparedStatement;

public class SQLQuerys {

    public static PreparedStatement banToQuery(Ban ban) {
        return PSC.getInstance().getDb().preparePreparedStmt("INSERT INTO `bans` (`UUID`, `name`, `staff`, `reason`, `start`, `end`, `active`) VALUES ('?', '?', '?', '?', '?', '?', '?')", ban.getUuid(), ban.getName(), ban.getStaff(), ban.getReason(), ban.getStart(), ban.getEnd(), ban.getActive());
    }

    // 10/10 method naming skills
    public static PreparedStatement checkPrevBansByCurrentBan(Ban ban) {
        return PSC.getInstance().getDb().preparePreparedStmt("SELECT * FROM bans WHERE active='true' and uuid=?", ban.getUuid());
    }

    public static PreparedStatement unbanFromRawID(int rowId) {
        return PSC.getInstance().getDb().preparePreparedStmt("UPDATE bans SET active='false' WHERE id=?;", String.valueOf(rowId));
    }
}
