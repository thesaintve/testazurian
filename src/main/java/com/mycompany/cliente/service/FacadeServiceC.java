/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cliente.service;

import com.mycompany.server.modelo.ListaVehiculo;
import com.mycompany.server.modelo.Vehiculo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.springframework.stereotype.Service;



/**
 *
 * @author david
 */
@Service
public abstract class FacadeServiceC {
    
    public String get(String ruta) throws MalformedURLException, IOException{
        URL url = new URL(ruta);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Fallo : HTTP error code : " + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

        String output;
        String strSalida="";
        while ((output = br.readLine()) != null) strSalida += output;        
        
        conn.disconnect();
        return strSalida;
    }
    
    public void post(String input, String ruta){
        try {
            URL url = new URL(ruta);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/xml");

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                throw new RuntimeException("Falla : HTTP error code : " + conn.getResponseCode());
            }

            conn.disconnect();

	} catch (MalformedURLException e) {
            e.printStackTrace();
	} catch (IOException e) {
            e.printStackTrace();
	 }
    }
    
    public void delete(String id, String ruta) throws MalformedURLException, IOException{
        URL url = new URL(ruta+"?id="+id);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("DELETE");
        conn.setRequestProperty("Accept", "application/xml");

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Fallo : HTTP error code : " + conn.getResponseCode());
        }
        
        conn.disconnect();
    }
    
    
    public abstract ListaVehiculo getAll() throws JAXBException, IOException;
    
    public abstract void guardar(Vehiculo v) throws JAXBException;
    
    public abstract void borrar(Vehiculo v) throws JAXBException, IOException;
    

    
}
