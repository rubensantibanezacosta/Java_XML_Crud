package com.xmlbackend.xmlbackend.services;

import com.xmlbackend.xmlbackend.models.Ball;
import com.xmlbackend.xmlbackend.models.BallsArray;

import javax.xml.bind.JAXBException;


import java.util.List;


public interface IBall {
    BallsArray getAll() throws JAXBException;
    Ball addOne(Ball ball) throws JAXBException;
    int update(Ball ball, int id) throws JAXBException;
    int delete(int id) throws JAXBException;
}
