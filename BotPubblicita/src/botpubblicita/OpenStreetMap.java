/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botpubblicita;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author stagno_alberto
 */
public class OpenStreetMap {

    public JPlace cercaPaese(String paese) throws MalformedURLException, IOException, ParserConfigurationException, SAXException {
        List<JPlace> lista = new ArrayList<JPlace>();
        String queri = "https://nominatim.openstreetmap.org/search?q=" + URLEncoder.encode(paese, "UTF-8") + "&format=xml&addressdetails=1";

        URL fileUrl = new URL(queri);
        Scanner inRemote = new Scanner(fileUrl.openStream());
        inRemote.useDelimiter("\u001a");

        String content = inRemote.next();
        PrintWriter wr = new PrintWriter("out.xml");
        wr.write(content);
        wr.close();
        inRemote.close();

        ParseXML validation = new ParseXML();
        lista=validation.parseCitta("out.xml");
        
        return lista.get(0);
    }
}
