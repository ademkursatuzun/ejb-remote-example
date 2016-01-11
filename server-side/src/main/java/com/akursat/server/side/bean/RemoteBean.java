/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akursat.server.side.bean;

import com.akursat.server.side.model.Users;
import javax.ejb.Remote;

/**
 *
 * @author akursat
 */
@Remote
public interface RemoteBean {
    
    public Users getUserByName(String username);

    public void updateUser(Users u);

}
