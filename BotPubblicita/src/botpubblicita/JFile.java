/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botpubblicita;

import TelegramApi.JMessage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alber
 */
public class JFile {

    String fileName = "";

    public JFile(String fileName) throws IOException {
        this.fileName = fileName;
        File f= new File(fileName);
        if(!f.exists())
            f.createNewFile();
        
    }

    public List<JUtente> getListaUtenti() throws FileNotFoundException, IOException {
        List<JUtente> listUtenti = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line = "";
        while ((line = br.readLine()) != null) {
            listUtenti.add(new JUtente(line));
        }
        return listUtenti;
    }

    public void writeUtente(JMessage c, JPlace p) throws IOException {
        int index = checkUtente(c.chat.id);
        if (index == -1) {
            try (FileWriter writer = new FileWriter(fileName)) {
                writer.append(c.chat.id + ";" + c.from.first_name + ";" + p.lat + ";" + p.lon + "\n");
                writer.flush();
            }
        } else {
            List<JUtente> listUtenti = getListaUtenti();
            listUtenti.get(index).lat = p.lat;
            listUtenti.get(index).lon = p.lon;
            try (FileWriter writer = new FileWriter(fileName)) {
                writer.write("");
                for (JUtente u : listUtenti) {
                    writer.append(u.chat_id + ";" + u.first_name + ";" + u.lat + ";" + u.lon + "\n");
                }
                writer.flush();
            }
        }
    }

    public int checkUtente(int id) throws IOException {
        List<JUtente> listUtenti = getListaUtenti();
        for (int i = 0; i < listUtenti.size(); i++) {
            if (listUtenti.get(i).chat_id == id) {
                return i;
            }
        }
        return -1;
    }

    public void write(String text) throws IOException {
        FileWriter fw = new FileWriter(fileName);
        fw.write(text);
        fw.close();
    }

    public void append(String text) throws IOException {
        FileWriter fw = new FileWriter(fileName, true);
        fw.append(text);
        fw.close();
    }

}
