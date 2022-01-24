package com.xmlbackend.xmlbackend.models;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import java.io.File;
import java.util.ArrayList;

public class XMLCrud {

    JAXBContext context;
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;


    public XMLCrud() throws JAXBException {

        this.context=JAXBContext.newInstance(Ball.class);
        this.marshaller= context.createMarshaller();
        this.unmarshaller = context.createUnmarshaller();
    }


    public void setXmlFromObject(ArrayList list, String fileName) throws JAXBException {
        this.marshaller.marshal(list, new File(fileName));
    }

    public Object getObjectFromXml(String fileName) throws JAXBException {
        return this.unmarshaller.unmarshal(new File(fileName));
    }
}
