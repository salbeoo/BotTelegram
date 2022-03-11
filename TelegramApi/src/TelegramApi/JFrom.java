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
public class JFrom {
    
    public int id;
    public boolean is_bot;
    public String first_name;
    public String username;
    public String language_code;

    public JFrom() {
    }

    
    public JFrom(int id, boolean is_bot, String first_name, String username, String language_code) {
        this.id = id;
        this.is_bot = is_bot;
        this.first_name = first_name;
        this.username = username;
        this.language_code = language_code;
    }
    
     
}
