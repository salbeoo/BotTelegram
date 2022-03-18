/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TelegramApi;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.*;

/**
 *
 * @author stagno_alberto
 */
public class Comandi {

    public void foo() {
        String jsonString = "{nome: 'mario', messaggi:['ciao','amico']}";
        JSONObject obj = new JSONObject(jsonString);
        String name = obj.getString("nome");
        System.out.println(name);
        JSONArray arr = obj.getJSONArray("messaggi"); // notice that `"posts": [...]`
        for (int i = 0; i < arr.length(); i++) {
            String messaggio = arr.getString(i);
            System.out.println(messaggio);
        }
    }

    public ArrayList<JMessage> getUpdates(String jsonS) throws IOException {

        ArrayList<JMessage> listaMessaggi = new ArrayList<JMessage>();

        URL fileUrl = new URL(jsonS);
        Scanner inRemote = new Scanner(fileUrl.openStream());
        inRemote.useDelimiter("\u001a");

        String content = inRemote.next();

        String jsonString = content;
        JSONObject obj = new JSONObject(jsonString);

        JMessage messaggino;
        JFrom from;
        JChat chat;

        JSONArray arr = obj.getJSONArray("result"); // notice that `"posts": [...]`
        for (int i = 0; i < arr.length(); i++) {
            JSONObject messaggio = arr.getJSONObject(i);

            int update_id = messaggio.getInt("update_id");

//            JSONObject message=arr.getJSONObject(1);
            JSONObject arrMessage = messaggio.getJSONObject("message");
            int message_id = arrMessage.getInt("message_id");

            JSONObject arrFrom = arrMessage.getJSONObject("from");
            int id = arrFrom.getInt("id");
            boolean is_bot = arrFrom.getBoolean("is_bot");
            String first_name = arrFrom.getString("first_name");
            String username = arrFrom.getString("username");
            String language_code = arrFrom.getString("language_code");
            from = new JFrom(id, is_bot, first_name, username, language_code);
//            
            JSONObject arrChat = arrMessage.getJSONObject("chat");
            int idC = arrChat.getInt("id");
            String first_nameC = arrChat.getString("first_name");
            String usernameC = arrChat.getString("username");
            String type = arrChat.getString("type");
            chat = new JChat(idC, first_nameC, usernameC, type);
//            
            int date = arrMessage.getInt("date");
            if (arrMessage.has("text")) {
                String text = arrMessage.getString("text");

                messaggino = new JMessage(update_id, message_id, from, chat, date, text);

                if (text.contains("/")) {
                    int indicePrimoSpazio = text.indexOf(" ");
                    if (indicePrimoSpazio != -1) {
                        String comand = text.substring(0, indicePrimoSpazio);
                        text = text.substring(indicePrimoSpazio + 1);
                        messaggino.comand = comand;
                        messaggino.text = text;
                        listaMessaggi.add(messaggino);
                    }
                }
            } else {
                
            }

        }

        return listaMessaggi;
    }

    public JMessage lastMessage() throws IOException {
        ArrayList<JMessage> listaMessaggi = getUpdates("https://api.telegram.org/bot5211721482:AAHYppsVKlrRTeUbIRjqlfTWzYXYCac6-KU/getUpdates");

        return listaMessaggi.get(listaMessaggi.size() - 1);

    }

    public void sendMessage(int idDestinatario, String testo) throws MalformedURLException, IOException {
        String url = "https://api.telegram.org/bot5211721482:AAHYppsVKlrRTeUbIRjqlfTWzYXYCac6-KU/sendMessage?";
        String path = "chat_id=" + idDestinatario + "&text=" + URLEncoder.encode(testo, "UTF-8");
        url += path;
        URL fileUrl = new URL(url);
        Scanner inRemote = new Scanner(fileUrl.openStream());
        inRemote.useDelimiter("\u001a");

        String content = inRemote.next();
        inRemote.close();
    }

    public void leggi(String url) throws FileNotFoundException, IOException {
        URL fileUrl = new URL(url);
        Scanner inRemote = new Scanner(fileUrl.openStream());
        inRemote.useDelimiter("\u001a");

        String content = inRemote.next();
        PrintWriter wr = new PrintWriter("out.xml");
        wr.write(content);
        wr.close();
        inRemote.close();
    }
}
