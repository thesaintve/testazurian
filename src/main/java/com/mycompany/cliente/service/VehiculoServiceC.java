/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cliente.service;

import com.mycompany.server.modelo.ListaVehiculo;
import com.mycompany.server.modelo.Vehiculo;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
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
public class VehiculoServiceC extends FacadeServiceC {
    private static final String urlRuta = "http://35.175.88.180:8585/mavenSpringRest-1.0/vehiculo";
//    private static final String urlRuta = "http://localhost:8080/mavenSpringRest/vehiculo";
    
    @Override
    public ListaVehiculo getAll() throws JAXBException, IOException{
        JAXBContext jaxbContext = JAXBContext.newInstance(ListaVehiculo.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        
        ListaVehiculo lv = (ListaVehiculo)unmarshaller.unmarshal(new StringReader(get(urlRuta)));
            
        return lv;
    }
    
    @Override
    public void guardar(Vehiculo v) throws JAXBException{
        JAXBContext jaxbContext = JAXBContext.newInstance(Vehiculo.class);
        Marshaller marshaller = jaxbContext.createMarshaller();

        StringWriter sw = new StringWriter();
        marshaller.marshal(v, sw);

        post(sw.toString(),urlRuta);
    }
    
    @Override
    public void borrar(Vehiculo v) throws JAXBException, IOException{
        delete(v.getMatricula(),urlRuta);
    }
    

    
}
