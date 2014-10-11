package com.realdolmen.util;

import javax.faces.context.FacesContext;
import java.util.Date;

/**
 * Created by ALMAU78 on 11/10/2014.
 */
public class ValidationUtil
{
    public static boolean validateForNotNullValues(Object ... parameters)
    {
        for (int i = 0; i < parameters.length; i=i+2) {
            Object message = parameters[i];
            Object attribute = parameters[i+1];
            if(attribute==null) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.getExternalContext().getFlash().setKeepMessages(true);
                context.addMessage(null, new Message().warning( "resourceBundle/ValidationMessages",  message.toString()));
                return true;
            }


        }
        return false;
    }
    public static boolean validateDatesForBeforeAndAfter(String message,Date before,Date after )
    {
        if(before==null)
        {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            context.addMessage(null, new Message().warning( "resourceBundle/ValidationMessages",  message.toString()));
            return true;
        }
        else if (after==null)
        {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            context.addMessage(null, new Message().warning( "resourceBundle/ValidationMessages",  message.toString()));
            return true;
        }
        else if(before.getTime()>=after.getTime()) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            context.addMessage(null, new Message().warning("resourceBundle/ValidationMessages", message));
            return true;
        }
        else
        {
            return false;
        }

    }
    public static boolean validateNumber(String message,Integer number)
    {
        if(number==null)
        {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            context.addMessage(null, new Message().warning("resourceBundle/ValidationMessages", message));
            return true;
        }
        else if(number ==0) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            context.addMessage(null, new Message().warning("resourceBundle/ValidationMessages", message));
            return true;
        }
        else
        {
            return false;
        }

    }
}
