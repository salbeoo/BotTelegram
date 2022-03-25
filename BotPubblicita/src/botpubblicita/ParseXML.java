/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botpubblicita;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author stagno_alberto
 */
public class ParseXML {

    public List parseCitta(String filename) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory;
        DocumentBuilder builder;
        Element root, element;
        NodeList nodelist;

        Document document;

        factory = DocumentBuilderFactory.newDefaultInstance();
        builder = factory.newDocumentBuilder();

        document = builder.parse(filename);
        root = (Element) document.getDocumentElement();

        nodelist = root.getElementsByTagName("place");
        
//        nodelist = ((Element)nodelist.item(0)).getChildNodes();
        
//        for(int i=0;i<nodelist.getLength();i++){
//            System.out.print(nodelist.item(i).getNodeName()+" - ");
//            System.out.println(nodelist.item(i).getTextContent());
//            
//        }
        List lista = new ArrayList<JPlace>();

        for (int i = 0; i < nodelist.getLength(); i++) {
//            System.out.println(parseObject(nodelist.item(i)));
            
            JPlace place = new JPlace(nodelist.item(i));
            lista.add(place);
        }

        return lista;

    }
}
