package br.com.study.apildap.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LdapConnection {
    private static final String LDAP_URL = "ldap://localhost:389";
    private static final String LDAP_ADMIN_USER = "cn=admin,dc=user";
    private static final String LDAP_ADMIN_PASSWORD = "dev123#";

    public static DirContext openConnection() throws NamingException {

        try {
            return new InitialDirContext(getEnv());
        } catch (NamingException e) {
            e.printStackTrace();
            throw e;
        }

    }

    public static void closeConnection(DirContext context) {
        if (context != null) {
            try {
                context.close();
            } catch (NamingException namingException) {
                namingException.printStackTrace();
            }
        }
    }

    private static Hashtable<String, String> getEnv() {

        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, LDAP_URL);
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, LDAP_ADMIN_USER);
        env.put(Context.SECURITY_CREDENTIALS, LDAP_ADMIN_PASSWORD);

        return env;
    }

}
