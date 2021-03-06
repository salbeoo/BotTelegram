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

        JFile fileCsv = null;
        try {
            fileCsv = new JFile("utenti.csv");
        } catch (IOException ex) {
            Logger.getLogger(ThreadUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
        Comandi comandi = new Comandi();
        OpenStreetMap mapStreet = new OpenStreetMap();

        JMessage lastMessage;
        int lastUpdateId = 0;
        try {
            lastUpdateId = comandi.lastMessage(url).update_id;
        } catch (IOException ex) {
            Logger.getLogger(ThreadUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (true) {

            try {
                Thread.sleep(1500);
                lastMessage = comandi.lastMessage(url);
                int newUpdateId = lastMessage.update_id;
                if (lastUpdateId != newUpdateId) {
                    lastUpdateId = newUpdateId;
//                    System.out.println(lastUpdateId);
//                    System.out.println(lastMessage.comand);
//                    System.out.println(lastMessage.text);
                    if (lastMessage.comand.equals("/citta")) {
                        JPlace paese = mapStreet.cercaPaese(lastMessage.text);
                        fileCsv.writeUtente(lastMessage, paese);
                        comandi.sendMessage(lastMessage.from.id, "Registrato");
//                        int updateChat=lastMessage.chat.id;
//                        String name=lastMessage.from.first_name;
//                        double lat=paese.lat;
//                        double lon=paese.lon;

                        //salva utente;
                    } else if (lastMessage.lat != -1 && lastMessage.lon != -1) {
                        JPlace paese = new JPlace(lastMessage.lat,lastMessage.lon);
                        fileCsv.writeUtente(lastMessage, paese);
                        comandi.sendMessage(lastMessage.from.id, "Registrato");
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
