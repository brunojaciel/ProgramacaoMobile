package DataAcessObject;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import Entidades.Prato;
import Serializer.BooleanSerializer;



public class PratoDAOREST implements iPlateDAO {
    private List<Prato> list= new ArrayList<>();

    @Override
    public List<Prato> obterTodosOsPratosJSON() {
        //final String url="http://192.168.0.14:3333/contacts";
        final String url="http://192.168.0.122:3333/cardapio";
        Thread t= new Thread(){
            public void run(){
                String resourceURI =  url;
                String httpParameters ="";
                String formatedURL = resourceURI + httpParameters;
                try {
                    URL url = new URL(formatedURL);
                    HttpURLConnection con = null;
                    con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    InputStream is = null;
                    is = con.getInputStream();
                    java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
                    String response = s.hasNext() ? s.next() : "";
                    Log.i("ASyncTask", "Requesting GET concluded." + response);
                    System.out.println(response);
                    GsonBuilder builder = new GsonBuilder();
                    builder.registerTypeAdapter(Boolean.class, new BooleanSerializer());
                    Gson gson = builder.create();
                    list= gson.fromJson(response, new TypeToken<ArrayList<Prato>>() {}.getType());
                    Log.i("ASyncTask", "Contacts parsed:" + list.size());
                    System.out.println(list);

                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        t.start();
        try {
            t.join();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Prato> obterTodosOsPratosXML() {
        final String url = "http://192.168.0.122:3333/cardapioXML";

        Thread t = new Thread() {
            public void run() {
                String resourceURI = url;
                String httpParameters = "";
                String formatedURL = resourceURI + httpParameters;
                try {
                    URL url = new URL(formatedURL);
                    HttpURLConnection con = null;
                    con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    con.setRequestProperty("Accept", "application/xml");
                    InputStream is;
                    is = con.getInputStream();
                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder dBuilder = null;
                    try {
                        dBuilder = dbFactory.newDocumentBuilder();
                    } catch (ParserConfigurationException e) {
                        e.printStackTrace();
                    }
                    Document doc = null;
                    try {
                        doc = dBuilder.parse(is);
                    } catch (IOException | SAXException e) {
                        e.printStackTrace();
                    }
                    NodeList nList = doc.getElementsByTagName("object");

                    for (int i = 0; i < nList.getLength(); i++) {
                        NodeList childrenTags = nList.item(i).getChildNodes();

                        int id = Integer.parseInt(childrenTags.item(0).getTextContent());
                        String nome = childrenTags.item(1).getTextContent();
                        String descricao = childrenTags.item(2).getTextContent();
                        BigDecimal preco = new BigDecimal(childrenTags.item(3).getTextContent());
                        boolean gluten = childrenTags.item(4).getTextContent().equals("1");
                        BigDecimal calorias = new BigDecimal(childrenTags.item(5).getTextContent());
                        String imagem = childrenTags.item(6).getTextContent();

                        Prato prato = new Prato(id, nome, descricao, preco, gluten, calorias, imagem);
                        list.add(prato);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        t.start();

        try {
            t.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
