/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TelegramApi;

import org.json.*;

/**
 *
 * @author stagno_alberto
 */
public class Test {

    public void foo() {
        String jsonString ="{nome: 'mario', messaggi:['ciao','amico']}";
        JSONObject obj = new JSONObject(jsonString);
        String name = obj.getString("nome");
        System.out.println(name);
        JSONArray arr = obj.getJSONArray("messaggi"); // notice that `"posts": [...]`
        for (int i = 0; i < arr.length(); i++) {
            String messaggio = arr.getString(i);
            System.out.println(messaggio);
        }
    }
}
