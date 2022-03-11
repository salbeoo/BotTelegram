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
public class JChat {
        public int id;
    public String first_name;
    public String username;
    public String type;

    public JChat() {
    }

    
    public JChat(int id, String first_name, String username, String type) {
        this.id = id;
        this.first_name = first_name;
        this.username = username;
        this.type = type;
    }
}
