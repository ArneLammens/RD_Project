package com.realdolmen.controller;

import com.realdolmen.util.LoggerResources;
import com.realdolmen.util.Message;

import javax.enterprise.event.Event;
import javax.inject.Inject;

/**
 * Created by ALMAU78 on 1/10/2014.
 */
public class PersonController{

    @Inject
    Event<Message> event;

    @Inject
    private LoggerResources loggerResources;


}
