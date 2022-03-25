/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TelegramApi;

/**
 *
 * @author stagno_alberto
 */
public class JMessage {
    //    public boolean ok;
//    public ArrayList<JMessage> result;

    public int update_id;

    public int message_id;

    public JFrom from;
    public JChat chat;

    public int date;
    public String text;

    public double lat, lon;

    public String comand;

    public JMessage() {
    }

    public JMessage(int update_id, int message_id, JFrom from, JChat chat, int date, String text) {
        this.update_id = update_id;
        this.message_id = message_id;
        this.from = from;
        this.chat = chat;
        this.date = date;
        this.text = text;
        this.lat = -1;
        this.lon = -1;
    }

    public JMessage(int update_id, int message_id, JFrom from, JChat chat, int date, double lat, double lon) {
        this.update_id = update_id;
        this.message_id = message_id;
        this.from = from;
        this.chat = chat;
        this.date = date;
        this.text = "";
        this.comand="";
        this.lat = lat;
        this.lon = lon;
    }
}
