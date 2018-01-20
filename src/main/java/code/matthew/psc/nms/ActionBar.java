package code.matthew.psc.nms;

import com.google.common.base.Preconditions;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

/**
 * Repersents a message displayed just above the players hot bar
 */
public class ActionBar {

    private JSONObject json;

    /**
     * Constructs an {@link ActionBar} object based on plain text.
     *
     * @param text Text to display.
     */
    public ActionBar(String text) {
        Preconditions.checkNotNull(text);
        this.json = Title.convert(text);
    }

    /**
     * Constructs an {@link ActionBar} object based on JSON-formatted text.
     *
     * @param json Text to display Must be in /tellraw JSON format.
     */
    public ActionBar(JSONObject json) {
        Preconditions.checkNotNull(json);
        Preconditions.checkArgument(!json.isEmpty());
        this.json = json;
    }

    /**
     * Sends an action bar message to a specific player.
     *
     * @param player The player to send the message to.
     */
    public void send(Player player) {
        Preconditions.checkNotNull(player);
        try {
            Class<?> clsIChatBaseComponent = ServerClass.MINECRAFT.getClass("IChatBaseComponent");
            Class<?> clsChatMessageType = ServerClass.MINECRAFT.getClass("ChatMessageType");
            Object entityPlayer = player.getClass().getMethod("getHandle").invoke(player);
            Object playerConnection = entityPlayer.getClass().getField("playerConnection").get(entityPlayer);
            Object chatBaseComponent = ServerClass.MINECRAFT.getClass("IChatBaseComponent$ChatSerializer").getMethod("a", String.class).invoke(null, json.toString());
            Object chatMessageType = clsChatMessageType.getMethod("valueOf", String.class).invoke(null, "GAME_INFO");
            Object packetPlayOutChat = ServerClass.MINECRAFT.getClass("PacketPlayOutChat").getConstructor(clsIChatBaseComponent, clsChatMessageType).newInstance(chatBaseComponent, chatMessageType);
            playerConnection.getClass().getMethod("sendPacket", ServerClass.MINECRAFT.getClass("Packet")).invoke(playerConnection, packetPlayOutChat);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sends an action bar message to all online players.
     */
    public void sendToAll() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            send(player);
        }
    }

    /**
     * Changes the text to display.
     *
     * @param text Text to display.
     */
    public void setText(String text) {
        Preconditions.checkNotNull(text);
        this.json = Title.convert(text);
    }

    /**
     * Changes the text to display.
     *
     * @param json Text to display. Must be in /tellraw JSON format.
     */
    public void setJsonText(JSONObject json) {
        Preconditions.checkNotNull(json);
        Preconditions.checkArgument(!json.isEmpty());
        this.json = json;
    }

}
