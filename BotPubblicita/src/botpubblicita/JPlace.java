/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botpubblicita;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author stagno_alberto
 */
public class JPlace {

    String amenity;
    String road;
    String town;
    String county;
    String state;
    int postcode;
    String country;
    String country_code;
    double lat, lon;

    public JPlace(Node o) {
        Element e = (Element) o;
        lat = Double.parseDouble(e.getAttribute("lat"));
        lon = Double.parseDouble(e.getAttribute("lon"));
    }

}
