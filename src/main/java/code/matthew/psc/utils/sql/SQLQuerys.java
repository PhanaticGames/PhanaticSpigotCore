package code.matthew.psc.utils.sql;

import code.matthew.psc.PSC;
import code.matthew.psc.api.ban.Ban;
import code.matthew.psc.api.mute.Mute;
import org.apache.commons.lang.Validate;

import java.sql.PreparedStatement;

/**
 * A class to help with some general database query help
 */
public class SQLQuerys {

    /**
     * Turn a ban into a query
     *
     * @param ban Ban object
     * @return The PreparedStatement version of the ban
     */
    public static PreparedStatement banToQuery(Ban ban) {
        Validate.notNull(ban, "BAN NULL ON BAN2QUERY");
        return PSC.getInstance().getDb().preparePreparedStmt("INSERT INTO bans VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?);", ban.getUuid(), ban.getName(), ban.getStaff(), ban.getReason(), ban.getStart(), ban.getEnd(), ban.getActive());
    }

    /**
     * Generate an old ban check query
     * @param ban Ban object
     * @return The PreparedStatement version of the ban check
     */
    public static PreparedStatement checkPrevBansByCurrentBan(Ban ban) {
        // 10/10 method naming skills
        Validate.notNull(ban, "BAN NULL ON PREVBAN2CB");
        return PSC.getInstance().getDb().preparePreparedStmt("SELECT * FROM bans WHERE active='true' and uuid=?", ban.getUuid());
    }

    /**
     * Generate an unban from the bans rowID
     * @param rowId The rows id in the database table
     * @return The PreparedStatement for unbanning a row
     */
    public static PreparedStatement unbanFromRawID(int rowId) {
        return PSC.getInstance().getDb().preparePreparedStmt("UPDATE bans SET active='false' WHERE id=?;", String.valueOf(rowId));
    }

    /**
     * Turn a mute into a query
     *
     * @param mute mute object
     * @return The PreparedStatement version of the mute
     */
    public static PreparedStatement muteToQuery(Mute mute) {
        return PSC.getInstance().getDb().preparePreparedStmt("INSERT INTO mutes VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?);", mute.getUuid(), mute.getName(), mute.getStaff(), mute.getReason(), mute.getStart(), mute.getEnd(), mute.getActive());
    }

    /**
     * Generate an old mute check query
     *
     * @param mute Mute object
     * @return The PreparedStatement version of the mute check
     */
    public static PreparedStatement checkPrevMutesByCurrentMute(Mute mute) {
        // 10/10 method naming skills
        return PSC.getInstance().getDb().preparePreparedStmt("SELECT * FROM mutes WHERE active='true' and uuid=?", mute.getUuid());
    }

    /**
     * Generate an unmute from the mutes rowID
     *
     * @param rowId The rows id in the database table
     * @return The PreparedStatement for unmutting a row
     */
    public static PreparedStatement unmuteFromRawID(int rowId) {
        return PSC.getInstance().getDb().preparePreparedStmt("UPDATE mutes SET active='false' WHERE id=?;", String.valueOf(rowId));
    }

}
