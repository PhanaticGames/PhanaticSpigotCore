package code.matthew.psc.runnable;

import code.matthew.psc.PSC;
import code.matthew.psc.api.mute.Mute;
import code.matthew.psc.utils.logs.Logger;
import code.matthew.psc.utils.sql.SQLQuerys;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AsyncMutes implements Runnable {

    @Override
    public void run() {
        PSC psc = PSC.getInstance();
        for (Mute mute : psc.getMm().getMutesToSyncList()) {

            ResultSet rs = psc.getDb().runQuery(SQLQuerys.checkPrevMutesByCurrentMute(mute));

            List<PreparedStatement> unmutes = new ArrayList<>();

            try {
                while (rs.next()) {
                    if (rs.getString("active").equals("true")) {
                        int id = rs.getInt("id");
                        unmutes.add(SQLQuerys.unbanFromRawID(id));
                    }
                }
            } catch (SQLException ex) {
                Logger.log(Logger.LogType.ERROR, "ERROR GETTING DATA");
                if (Logger.isDebug()) {
                    ex.printStackTrace();
                }
            }

            if (!unmutes.isEmpty()) {
                for (PreparedStatement stmt : unmutes) {
                    psc.getDb().runUpdate(stmt);
                }
            }

            if (mute.isMuted()) {
                psc.getDb().runUpdate(SQLQuerys.muteToQuery(mute));
            }
        }
        psc.getMm().getMutesToSyncList().clear();
    }
}
