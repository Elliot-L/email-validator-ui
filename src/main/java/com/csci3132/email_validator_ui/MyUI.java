package com.csci3132.email_validator_ui;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        
        //name will store the email address to be checked
        final TextField name = new TextField();
        name.setCaption("Enter the email address to check:");

        Button button = new Button("Check email");
        button.addClickListener( e -> {
            //code to check the validity of the email goes here
        	Validator validator = new Validator();
        	
        	//test and response if the email id valid
        	if(validator.validate(name.getValue()) == true){
            	layout.addComponent(new Label("After checking: " + name.getValue() 
                + " is a valid email address"));
        	}
        	//if the email is not valid, a different label is needed
        	else{
            	layout.addComponent(new Label("After checking: " + name.getValue() 
                + " is not a valid email address"));        		
        	}

        });
        
        layout.addComponents(name, button);
        layout.setMargin(true);
        layout.setSpacing(true);
        
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
