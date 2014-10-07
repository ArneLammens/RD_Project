package com.realdolmen.domain.login;

import com.realdolmen.domain.AbstractRepositoy;
import com.realdolmen.domain.Enums;
import com.realdolmen.domain.person.Person;
import com.realdolmen.util.Message;

import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.Stateless;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by BPTAT47 on 3/10/2014.
 */
@Stateless
public class LoginRepository extends AbstractRepositoy<Login> {


    public String encryptPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());

        byte byteData[] = md.digest();

        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        System.out.println("Hex format : " + sb.toString());

        //convert the byte to hex format method 2
        StringBuffer hexString = new StringBuffer();
        for (int i=0;i<byteData.length;i++) {
            String hex=Integer.toHexString(0xff & byteData[i]);
            if(hex.length()==1) hexString.append('0');
            hexString.append(hex);
        }
        System.out.println("Hex format : " + hexString.toString());

        return hexString.toString();
    }
    public Login retrievePersonWithGivenNameAndPassword(Login login)throws Exception {
        try {
            Person person = entityManager.createNamedQuery("Person.retrievePersonWithGivenEmailAndPassword", Person.class)
                    .setParameter("email", login.getEmail())
                    .setParameter("password", encryptPassword(login.getPassword())).getSingleResult();
            return new Login(person.getId(), person.getEmail(), person.getPassword(), person.getRole());
        } catch (Exception e) {
            throw new NoResultException();
        }

    }
}

