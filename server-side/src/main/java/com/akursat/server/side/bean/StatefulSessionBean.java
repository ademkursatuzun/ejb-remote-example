/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akursat.server.side.bean;

import com.akursat.server.side.model.Users;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Init;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author akursat
 */
@Stateful
@Remote(RemoteBean.class)
public class StatefulSessionBean implements RemoteBean,Serializable {

    @PersistenceContext(unitName = "JPADB")
    private EntityManager entityManager;

    public StatefulSessionBean() {
    }
    
    @Override
    public Users getUserByName(String username) {
        return entityManager.find(Users.class, username);
    }

    @Override
    public void updateUser(Users u) {
        entityManager.merge(u);
    }
   
}
