/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.server.rest;

import com.mycompany.server.modelo.ListaVehiculo;
import com.mycompany.server.modelo.Vehiculo;
import com.mycompany.server.service.VehiculoService;
import java.io.StringReader;
import java.io.StringWriter;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author david
 */
@Controller
@RequestMapping("/vehiculo")
public class RestVehiculo {
    
    @Autowired
    private VehiculoService vs;
    
        
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody 
    public void getVehiculos(HttpServletResponse response) {
        ListaVehiculo listaV = vs.getAll();
        
        try{ 
            JAXBContext jaxbContext = JAXBContext.newInstance(ListaVehiculo.class);
            Marshaller marshaller = jaxbContext.createMarshaller();

            StringWriter sw = new StringWriter();
            marshaller.marshal(listaV, sw);
            
            response.getOutputStream().write(sw.toString().getBytes());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody 
    public void getGuarda(@RequestBody String vehi,  HttpServletResponse response) {        
        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(Vehiculo.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Vehiculo v = (Vehiculo) unmarshaller.unmarshal(new StringReader(vehi));
            
            vs.guardar(v);
            response.setStatus(HttpServletResponse.SC_CREATED);
        }
        catch(Exception e){
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
        }
    }
    
    
    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody 
    public void getBorrar(@RequestParam("id") String id,  HttpServletResponse response) {        
        try{             
            vs.borrar(id);
            response.setStatus(HttpServletResponse.SC_OK);
        }
        catch(Exception e){
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
        }
    }
    

}
