package com.realdolmen.util;

import javax.enterprise.event.Observes;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Created by BPTAT47 on 2/10/2014.
 */
public class MessageObserver {

    private void showMessage(@Observes FacesMessage message)
    {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
