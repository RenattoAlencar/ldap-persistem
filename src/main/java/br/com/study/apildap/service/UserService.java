package br.com.study.apildap.service;

import br.com.study.apildap.config.LdapConnection;
import br.com.study.apildap.entity.User;
import br.com.study.apildap.model.UserRequest;
import org.springframework.stereotype.Service;

import javax.naming.NamingException;
import javax.naming.directory.*;

@Service
public class UserService {
    private static final String BASE_DN = "dc=user";
    private static final String USER_OBJECT = "inetOrgPerson";

    public void create(User user) throws NamingException {
        DirContext context = null;

        try {
            context = LdapConnection.openConnection();

            Attributes attributes = new BasicAttributes();
            Attribute objectClass = new BasicAttribute("objectClass");

            objectClass.add(USER_OBJECT);
            attributes.put(objectClass);
            attributes.put("cn", user.getName());
            attributes.put("sn", user.getLastName());
            attributes.put("mail", user.getEmail());
            attributes.put("uid", user.getId());

            context.createSubcontext("uid=" + user.getId() + "," + BASE_DN, attributes);
        } catch (NamingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            LdapConnection.closeConnection(context);
        }

    }

}
