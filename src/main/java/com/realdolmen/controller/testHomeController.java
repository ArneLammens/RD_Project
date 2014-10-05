package com.realdolmen.controller;

import com.realdolmen.domain.Enums;
import com.realdolmen.util.LoggerResources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.spi.InjectionPoint;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class TestHomeController implements Serializable {


    private String tester;
    @Inject
    private Logger logger;


    public void init()
    {
        tester = "dit is een ingevulde waarde";

    }

    public void loggertest()
    {
        logger.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
    }

    public Enums.Region[] getRegions() {
        return Enums.Region.values();
    }

    public String getTester() {
        return tester;
    }

    public void setTester(String tester) {
        this.tester = tester;
    }
}
