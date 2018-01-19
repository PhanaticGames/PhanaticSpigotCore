package code.matthew.psc.runnable;

import code.matthew.psc.PSC;
import code.matthew.psc.api.ban.Ban;
import code.matthew.psc.utils.logs.Logger;
import code.matthew.psc.utils.sql.SQLQuerys;
import org.apache.commons.lang3.Validate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AsyncBans implements Runnable {

    @Override
    public void run() {
        PSC psc = PSC.getInstance();
        for (Ban ban : psc.getBm().getBanToSyncList()) {

            ResultSet rs = psc.getDb().runQuery(SQLQuerys.checkPrevBansByCurrentBan(ban));

            List<PreparedStatement> unbans = new ArrayList<>();

            try {
                while (rs.next()) {
                    if (rs.getString("active").equals("true")) {
                        int id = rs.getInt("id");
                        unbans.add(SQLQuerys.unbanFromRawID(id));
                    }
                }
            } catch (SQLException ex) {
                Logger.log(Logger.LogType.ERROR, "ERROR GETTING DATA");
                if (Logger.isDebug()) {
                    ex.printStackTrace();
                }
            }

            if (!unbans.isEmpty()) {
                for (PreparedStatement stmt : unbans) {
                    psc.getDb().runUpdate(stmt);
                }
            }

            Validate.notNull(ban, "BAN NMULL");
            Validate.notNull(psc, "PSC NULL");
            Validate.notNull(psc.getDb(), "DB NULL");

            if (ban.isBanned()) {
                psc.getDb().runUpdate(SQLQuerys.banToQuery(ban));
            }
        }
        psc.getBm().getBanToSyncList().clear();
    }
}
