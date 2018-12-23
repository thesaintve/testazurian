/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cliente.view;

import com.mycompany.cliente.service.VehiculoServiceC;
import com.mycompany.server.modelo.Vehiculo;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.xml.bind.JAXBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author david
 */
@ManagedBean
@ViewScoped
public class ViewVehiculo {
    
    @Autowired
    VehiculoServiceC vsC = new VehiculoServiceC();
    
    private List<Vehiculo> vehiculos;
    private Vehiculo vehiculo;

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }
    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }
    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public void selectVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public ViewVehiculo() {
        try {
            vehiculos = vsC.getAll().getVehiculos();
        } catch (JAXBException ex) {
            Logger.getLogger(ViewVehiculo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ViewVehiculo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public void nuevo(){
        vehiculo = new Vehiculo();
        vehiculo.setAlta("S");
    }
    
    public String guardar(){
        try {
            vsC.guardar(vehiculo);
            vehiculos = vsC.getAll().getVehiculos();
        } catch (JAXBException ex) {
            Logger.getLogger(ViewVehiculo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
                Logger.getLogger(ViewVehiculo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    public String borrar(){
        try {
            vsC.borrar(vehiculo);
            vehiculos = vsC.getAll().getVehiculos();
        } catch (JAXBException ex) {
            Logger.getLogger(ViewVehiculo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
                Logger.getLogger(ViewVehiculo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
}
