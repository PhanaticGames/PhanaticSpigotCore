package code.matthew.psc.utils.sql;

import code.matthew.psc.PSC;
import code.matthew.psc.api.Ban;
import org.apache.commons.lang.Validate;

import java.sql.PreparedStatement;

public class SQLQuerys {

    public static PreparedStatement banToQuery(Ban ban) {
        Validate.notNull(ban, "BAN NULL ON BAN2QUERY");
        return PSC.getInstance().getDb().preparePreparedStmt("INSERT INTO bans VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?);", ban.getUuid(), ban.getName(), ban.getStaff(), ban.getReason(), ban.getStart(), ban.getEnd(), ban.getActive());
    }

    // 10/10 method naming skills
    public static PreparedStatement checkPrevBansByCurrentBan(Ban ban) {
        Validate.notNull(ban, "BAN NULL ON PREVBAN2CB");
        return PSC.getInstance().getDb().preparePreparedStmt("SELECT * FROM bans WHERE active='true' and uuid=?", ban.getUuid());
    }

    public static PreparedStatement unbanFromRawID(int rowId) {
        Validate.notNull(rowId, "BAN NULL ON RAW ID");
        return PSC.getInstance().getDb().preparePreparedStmt("UPDATE bans SET active='false' WHERE id=?;", String.valueOf(rowId));
    }
}
