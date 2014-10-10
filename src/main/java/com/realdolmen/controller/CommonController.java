package com.realdolmen.controller;

import com.realdolmen.domain.Enums;
import com.realdolmen.util.RedirectEnum;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by BPTAT47 on 8/10/2014.
 */
@Named
@ViewScoped
public class CommonController implements Serializable{

    public String redirectToAdminPage(){
        return RedirectEnum.REDIRECT.ADMIN_COUNTRY.getUrl();
    }
    public String redirectToAdminPageForMargins(){
        return RedirectEnum.REDIRECT.ADMIN_MARGIN.getUrl();
    }
    public String redirectToFLightAdminCreateFlight(){
        return RedirectEnum.REDIRECT.CREATE_FLIGHT.getUrl();
    }



}
