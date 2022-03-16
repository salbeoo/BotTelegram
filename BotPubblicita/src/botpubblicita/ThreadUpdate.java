/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botpubblicita;

import TelegramApi.Comandi;
import TelegramApi.JMessage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Alber
 */
public class ThreadUpdate extends Thread {

    public void run() {
        final String baseUrl = "https://api.telegram.org/bot";
        final String token = "5211721482:AAHYppsVKlrRTeUbIRjqlfTWzYXYCac6-KU/";
        String comand = "getUpdates";
        String url = baseUrl + token + comand;

        Comandi comandi = new Comandi();
        OpenStreetMap mapStreet=new OpenStreetMap();

        JMessage lastMessage;
        int lastUpdateId = 0;
        try {
            lastUpdateId = comandi.lastMessage().update_id;
        } catch (IOException ex) {
            Logger.getLogger(ThreadUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (true) {

            try {
                Thread.sleep(1500);
                lastMessage = comandi.lastMessage();
                int newUpdateId = lastMessage.update_id;
                if (lastUpdateId != newUpdateId) {
                    lastUpdateId = newUpdateId;
//                    System.out.println(lastUpdateId);
//                    System.out.println(lastMessage.comand);
//                    System.out.println(lastMessage.text);
                    if (lastMessage.comand.equals("/citta")) {
                        JPlace paese=mapStreet.cercaPaese(lastMessage.text);
                        System.out.println(paese.display_name);
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(ThreadUpdate.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadUpdate.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(ThreadUpdate.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                Logger.getLogger(ThreadUpdate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
