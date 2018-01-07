package code.matthew.psc.runnable;

import code.matthew.psc.PSC;
import code.matthew.psc.api.Ban;
import code.matthew.psc.utils.sql.SQLQuerys;

public class AsyncBans implements Runnable {

    @Override
    public void run() {
        PSC psc = PSC.getInstance();
        for (Ban ban : psc.getBm().getBanToSyncList()) {
            // This is incase a ban has expired before the sync
            if (ban.isBanned()) {
                psc.getDb().runUpdate(SQLQuerys.banToQuery(ban));
            }
            psc.getBm().getBanToSyncList().remove(ban);
        }
    }
}
