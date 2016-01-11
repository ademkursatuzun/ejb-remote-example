/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akursat.server.side.bean;

import com.akursat.server.side.model.Users;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author akursat
 */
@Stateless
@Remote(RemoteBean.class)
public class StatelessSessionBean implements RemoteBean {

    @PersistenceContext(unitName = "JPADB")
    private EntityManager entityManager;
    
    @Override
    public Users getUserByName(String username) {
        return entityManager.find(Users.class, username);
    }

    @Override
    public void updateUser(Users u) {
        entityManager.merge(u);
    }

}
