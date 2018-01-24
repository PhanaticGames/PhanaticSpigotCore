package code.matthew.psc.nms;

import code.matthew.psc.utils.logs.Logger;
import net.minecraft.server.v1_8_R3.EntityInsentient;
import net.minecraft.server.v1_8_R3.EntityTypes;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NMSUtil {

    public void regiserEntity(String name, int id, Class<? extends EntityInsentient> nmsClass, Class<? extends EntityInsentient> customClass) {
        try {

            List<Map<?, ?>> dataMap = new ArrayList<Map<?, ?>>();
            for (Field f : EntityTypes.class.getDeclaredFields()) {
                if (f.getType().getSimpleName().equals(Map.class.getSimpleName())) {
                    f.setAccessible(true);
                    dataMap.add((Map<?, ?>) f.get(null));
                }
            }

            if (dataMap.get(2).containsKey(id)) {
                dataMap.get(0).remove(name);
                dataMap.get(2).remove(id);
            }

            Method method = EntityTypes.class.getDeclaredMethod("a", Class.class, String.class, int.class);
            method.setAccessible(true);
            method.invoke(null, customClass, name, id);


        } catch (IllegalAccessException ex) {
            Logger.log(Logger.LogType.ERROR, "ERROR ACCESSING ENTITY MAP DATA: ILLEGAL ACCESS");
            if (Logger.isDebug()) {
                ex.printStackTrace();
            }
        } catch (NoSuchMethodException ex) {
            Logger.log(Logger.LogType.ERROR, "ERROR ACCESSING ENTITY MAP DATA: NOT SUCH METHOD");
            if (Logger.isDebug()) {
                ex.printStackTrace();
            }
        } catch (InvocationTargetException ex) {
            Logger.log(Logger.LogType.ERROR, "ERROR ACCESSING ENTITY MAP DATA: INVOCATION TARGET EXCEPTION");
            if (Logger.isDebug()) {
                ex.printStackTrace();
            }
        }
    }
}
