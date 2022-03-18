/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botpubblicita;

import TelegramApi.Comandi;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author stagno_alberto
 */
public class ThreadSend extends Thread {

    public JPubblicita lastPubblicita;

    public void run() {

        final String baseUrl = "https://api.telegram.org/bot";
        final String token = "5211721482:AAHYppsVKlrRTeUbIRjqlfTWzYXYCac6-KU/";
        String comand = "getUpdates";
        String url = baseUrl + token + comand;

        JFileCSV fileCsv = new JFileCSV("utenti.csv");
        Comandi comandi = new Comandi();
        OpenStreetMap mapStreet = new OpenStreetMap();

        while (true) {
            try {
                Thread.sleep(1500);
                if (lastPubblicita != null) {
                    List<JUtente> listaUtenti = fileCsv.getListaUtenti();
                    JPlace posto = mapStreet.cercaPaese(lastPubblicita.citta);
                    for (JUtente u : listaUtenti) {
                        int raggio = (int) distanza(u.lat, u.lon, posto.lat, posto.lon);
                        if (raggio < lastPubblicita.raggio) {
                            comandi.sendMessage(u.chat_id, lastPubblicita.nome);
                        }
                    }
                    lastPubblicita = null;
                }

            } catch (IOException ex) {
                Logger.getLogger(ThreadSend.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(ThreadSend.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                Logger.getLogger(ThreadSend.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadSend.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public double degreesToRadians(double degrees) {
        return degrees * Math.PI / 180;
    }

    public double distanza(double lat1, double lon1, double lat2, double lon2) {
        var earthRadiusKm = 6371;

        var dLat = degreesToRadians(lat2 - lat1);
        var dLon = degreesToRadians(lon2 - lon1);

        lat1 = degreesToRadians(lat1);
        lat2 = degreesToRadians(lat2);

        var a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2);
        var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return earthRadiusKm * c;
    }

    public void setPubblicita(JPubblicita pu) {
        lastPubblicita = pu;
    }
}
