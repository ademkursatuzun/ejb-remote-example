package com.akursat.client;

import com.akursat.server.side.model.Users;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.naming.Context;
import javax.naming.InitialContext;
import com.akursat.server.side.bean.RemoteBean;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author akursat
 */
public class RemoteClient {

    final String lookupStateful = "ejb:/server-side-1//StatefulSessionBean!com.akursat.server.side.bean.RemoteBean?stateful";
    final String lookupStateless = "server-side-1/StatelessSessionBean!com.akursat.server.side.bean.RemoteBean";

    public static void main(String[] args) {

        RemoteClient rc = new RemoteClient();

        System.out.println("Starting...");
        try {
            ////////////Configure jndi//////////
            final Hashtable jndiProperties = new Hashtable();
            jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
            jndiProperties.put("jboss.naming.client.connect.options.org.xnio.Options.SASL_POLICY_NOPLAINTEXT", "false"); // needed for a login module that requires the password in plaintext
            jndiProperties.put(Context.PROVIDER_URL, "remote://localhost:4447");
            jndiProperties.put(Context.SECURITY_PRINCIPAL, "jboss");
            jndiProperties.put(Context.SECURITY_CREDENTIALS, "123456");
            jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
            jndiProperties.put("jboss.naming.client.ejb.context", true);
            final Context context = new InitialContext(jndiProperties);

            //////////StatelessBean//////////
            System.out.println("//////////StatelessBean//////////");
            RemoteBean statelessRemote = (RemoteBean) context.lookup(rc.lookupStateless);
            System.out.println("getUserByName(admin):getting Admin ");

            System.out.println("User's birthday: " + statelessRemote.getUserByName("admin").getBirthday());

            Date date = new SimpleDateFormat("yyyyMMdd").parse("19910520");
            Users u = statelessRemote.getUserByName("admin");
            u.setBirthday(date);
            statelessRemote.updateUser(u);

            System.out.println("Current Birthday: " + statelessRemote.getUserByName("admin").getBirthday());

            RemoteBean statelessRemote2 = (RemoteBean) context.lookup(rc.lookupStateless);
            Users u2 = statelessRemote2.getUserByName("admin");
            u2.setSex((short) 0);
            statelessRemote2.updateUser(u2);

            System.out.println("Current Sex: " + statelessRemote2.getUserByName("admin").getSex());

            //////////StatefullBean//////////
            System.out.println("//////////StatefullBean//////////");
            RemoteBean statefulRemote = (RemoteBean) context.lookup(rc.lookupStateful);
            System.out.println("getUserByName(admin):getting Admin ");

            System.out.println("User's birthday: " + statefulRemote.getUserByName("admin"));

            Date date2 = new SimpleDateFormat("yyyyMMdd").parse("19910520");
            Users u3 = statefulRemote.getUserByName("admin");
            u3.setBirthday(date2);
            statelessRemote.updateUser(u3);

            System.out.println("Current Birthday: " + statefulRemote.getUserByName("admin").getBirthday());

            RemoteBean statefulRemote2 = (RemoteBean) context.lookup(rc.lookupStateful);
            Users u4 = statefulRemote2.getUserByName("admin");
            u4.setSex((short) 1);
            statelessRemote2.updateUser(u4);

            System.out.println("Current Sex: " + statefulRemote2.getUserByName("admin").getSex());

        } catch (NamingException ex) {
            Logger.getLogger(RemoteClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(RemoteClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
