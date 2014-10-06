package com.realdolmen.controller;

import com.realdolmen.domain.person.PersonService;
import com.realdolmen.util.LoggerResources;
import com.realdolmen.util.Message;

import javax.enterprise.event.Event;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class PersonController implements Serializable{

    @Inject
    Event<Message> event;

    @Inject
    private PersonService personService;



}
