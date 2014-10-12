package com.realdolmen.util;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by BPTAT47 on 2/10/2014.
 */


public class Message {

    Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();


    public FacesMessage info(String message)
    {
        return createMessage(message, FacesMessage.SEVERITY_INFO);
    }

    public FacesMessage info(String resourceBundle, String key, Object... parameters)
    {

        return createMessage(resourceBundle, key, parameters, FacesMessage.SEVERITY_INFO);
    }

    public FacesMessage warning(String message)
    {
        return createMessage(message, FacesMessage.SEVERITY_WARN);
    }

    public FacesMessage warning(String resourceBundle, String key, Object... parameters)
    {
        return createMessage(resourceBundle, key, parameters, FacesMessage.SEVERITY_WARN);
    }

    public FacesMessage fatal(String message)
    {
        return createMessage(message, FacesMessage.SEVERITY_FATAL);
    }

    public FacesMessage fatal(String resourceBundle, String key, Object... parameters)
    {
        return createMessage(resourceBundle, key, parameters, FacesMessage.SEVERITY_FATAL);
    }

    public FacesMessage error(String message)
    {
        return createMessage(message, FacesMessage.SEVERITY_ERROR);
    }

    public FacesMessage error(String resourceBundle, String key, Object... parameters)
    {
        return createMessage(resourceBundle, key, parameters, FacesMessage.SEVERITY_ERROR);
    }

    private FacesMessage createMessage(String message, FacesMessage.Severity severity)
    {
        FacesMessage facesMessage = new FacesMessage(message);
        facesMessage.setSeverity(severity);
        return facesMessage;
    }


    private FacesMessage createMessage(String resourceBundle, String key, Object[] parameters, FacesMessage.Severity severity)
    {
        ResourceBundle bundle = ResourceBundle.getBundle(resourceBundle, this.locale);
        FacesMessage facesMessage = new FacesMessage(MessageFormat.format(bundle.getString(key).replaceAll("'", "''")," ", parameters)," ");
        facesMessage.setSeverity(severity);
        return facesMessage;
    }

}
