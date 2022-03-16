/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botpubblicita;

/**
 *
 * @author Alber
 */
public class JUtente {

    int chat_id;
    String first_name;
    Double lat, lon;

    public JUtente(int chat_id, String first_name, Double lat, Double lon) {
        this.chat_id = chat_id;
        this.first_name = first_name;
        this.lat = lat;
        this.lon = lon;
    }

    public JUtente(String line) {
        String[] campi = line.split(";");
        this.chat_id = Integer.parseInt(campi[0]);
        this.first_name = campi[1];
        this.lat = Double.parseDouble(campi[2]);
        this.lon = Double.parseDouble(campi[3]);;
    }
}
