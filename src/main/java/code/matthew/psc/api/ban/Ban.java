package code.matthew.psc.api.ban;

import code.matthew.psc.utils.logs.Logger;
import lombok.Getter;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The ban object
 */
public class Ban {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String uuid;

    @Getter
    @Setter
    private String staff;

    @Getter
    @Setter
    private String reason;

    @Getter
    @Setter
    private String end;

    @Getter
    @Setter
    private String start;

    @Getter
    @Setter
    private String active;

    public Ban(String name, String uuid, String staff, String reason, String end, String start) {
        this.name = name;
        this.uuid = uuid;
        this.staff = staff;
        this.reason = reason;
        this.start = start;
        this.end = end;
        this.active = "true";
    }

    public Ban(String name, String uuid, String staff, String reason, String end, String start, String active) {
        this.name = name;
        this.uuid = uuid;
        this.staff = staff;
        this.reason = reason;
        this.start = start;
        this.end = end;
        this.active = active;
    }

    public boolean isBanned() {

        if (getActive().equalsIgnoreCase("false")) {
            return false;
        }

        Date end = null;
        Date today = new Date();

        try {
            end = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy").parse(getEnd());
        } catch (ParseException ex) {
            Logger.log(Logger.LogType.INFO, "ERROR PARSEING BAN DATE");
            ex.printStackTrace();
            if (Logger.isDebug()) {
                ex.printStackTrace();
            }
        }

        if (end.after(today)) {
            return true;
        }
        setActive("false");
        return false;
    }
}
