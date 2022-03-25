/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botpubblicita;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
    String display_name;

    public JPlace(Node o) {
        Element e = (Element) o;
        NodeList nodeList = e.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            SetAttribute(nodeList.item(i).getNodeName(), nodeList.item(i).getTextContent());
        }
        display_name = e.getAttribute("display_name");
        lat = Double.parseDouble(e.getAttribute("lat"));
        lon = Double.parseDouble(e.getAttribute("lon"));
    }

    public void SetAttribute(String nome, String val) {
        switch (nome) {
            case "town":
                town = val;
                break;
            case "county":
                county = val;
                break;
            case "state":
                state = val;
                break;
            case "postcode":
                postcode = Integer.parseInt(val);
                break;
            case "country":
                country = val;
                break;
            case "country_code":
                country_code = val;
                break;
        }
    }

    public JPlace(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

}
