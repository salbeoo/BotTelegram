/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botpubblicita;

import TelegramApi.*;
import java.io.IOException;

/**
 *
 * @author stagno_alberto
 */
public class BotPubblicita {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        final String baseUrl="https://api.telegram.org/bot";
        final String token="5211721482:AAHYppsVKlrRTeUbIRjqlfTWzYXYCac6-KU/";
        String comand="getUpdates";
        String url=baseUrl+token+comand;
        
        Comandi t=new Comandi();
        t.sendMessage(1152378424, "ciao bello");
        t.getUpdates(url);
    }
    
}
