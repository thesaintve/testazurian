/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.server.service;

import com.mycompany.server.modelo.ListaVehiculo;
import com.mycompany.server.modelo.Vehiculo;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.springframework.stereotype.Service;

/**
 *
 * @author david
 */
@Service
public class VehiculoService {
    private EntityManager em;

    public VehiculoService() {
        //this.em = Persistence.createEntityManagerFactory("wRestTomcat01PU").createEntityManager();
    }
    
    private void getEM(){
        this.em = Persistence.createEntityManagerFactory("wRestTomcat01PU").createEntityManager();
    }
    
    public ListaVehiculo getAll(){
        getEM();
        Query q= this.em.createQuery("select v from Vehiculo v", Vehiculo.class);
        ListaVehiculo l = new ListaVehiculo(q.getResultList());        
        em.close();
        return l;        
    }
    
    public void guardar(Vehiculo v) {
        getEM();
        em.getTransaction().begin();        
        if (this.em.find(Vehiculo.class, v.getMatricula())!=null){
        this.em.merge(v);
        }else{
        this.em.persist(v);
        }        
        em.getTransaction().commit();
        em.close();
    }
    
    public void borrar(String id) {
        getEM();
        Vehiculo v = this.em.find(Vehiculo.class, id);
        if (v!=null) {
            em.getTransaction().begin();        
            this.em.remove(v);
            em.getTransaction().commit();
            em.close();
        }
    }
    
    
}
