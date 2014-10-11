package com.realdolmen.util;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

/**
 * Created by BPTAT47 on 11/10/2014.
 */
public class FlashUtil {
    private static Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();

    public static void addAllValuesToFlash(Object ... parameters){

        for (int i = 0; i < parameters.length; i=i+2) {
            Object objectType = parameters[i];
            Object object = parameters[i+1];

            flash.put("" + objectType ,object.getClass());
        }
    }


}
