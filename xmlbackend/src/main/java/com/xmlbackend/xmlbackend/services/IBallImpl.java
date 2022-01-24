package com.xmlbackend.xmlbackend.services;

import com.xmlbackend.xmlbackend.models.Ball;
import com.xmlbackend.xmlbackend.models.BallsArray;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Service;

import java.io.File;



@Service
public class IBallImpl implements IBall {
    String XmlFileName="ball.xml";
    @Override
    public BallsArray getAll() throws JAXBException {

        JAXBContext context= JAXBContext.newInstance(BallsArray.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

       BallsArray balls = (BallsArray) unmarshaller.unmarshal(new File(XmlFileName));

        return balls;
    }



    @Override
    public Ball addOne(Ball ball) throws JAXBException {
        JAXBContext context= JAXBContext.newInstance(BallsArray.class);
        Marshaller marshaller = context.createMarshaller();
       Unmarshaller unmarshaller = context.createUnmarshaller();
        BallsArray balls = (BallsArray) unmarshaller.unmarshal(new File(XmlFileName));

           if(!(balls.getLenght()>1)){
               ball.setId(1);
            }else{
               balls.sort();
                ball.setId((balls.getBall(((balls.getLenght())-1)).id)+1);
            }
            balls.addBall(ball);
            marshaller.marshal(balls,new File(this.XmlFileName));
            return  ball;
    }

    @Override
    public int update(Ball newBall, int id) throws JAXBException {
        JAXBContext context= JAXBContext.newInstance(BallsArray.class);
        Marshaller marshaller = context.createMarshaller();
        Unmarshaller unmarshaller = context.createUnmarshaller();
        BallsArray balls = (BallsArray) unmarshaller.unmarshal(new File(XmlFileName));
        balls.updateBall(newBall, id);
        marshaller.marshal(balls,new File(this.XmlFileName));
        return 1;
    }

    @Override
    public int delete(int id) throws JAXBException {
        JAXBContext context= JAXBContext.newInstance(BallsArray.class);
        Marshaller marshaller = context.createMarshaller();
        Unmarshaller unmarshaller = context.createUnmarshaller();
        BallsArray balls = (BallsArray) unmarshaller.unmarshal(new File(XmlFileName));
        if(!(balls.getLenght()>0)){
            return 0;
        }
       else{
            balls.deleteBallById(id);
            marshaller.marshal(balls, new File(XmlFileName));
            return 1;
        }

    }
}
