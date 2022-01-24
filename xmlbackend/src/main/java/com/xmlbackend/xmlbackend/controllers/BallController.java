package com.xmlbackend.xmlbackend.controllers;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.xmlbackend.xmlbackend.models.Ball;
import com.xmlbackend.xmlbackend.models.BallsArray;
import com.xmlbackend.xmlbackend.services.IBallImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.xml.bind.JAXBException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController

public class BallController {
   @Autowired
   IBallImpl iBallService;

   @GetMapping("/")
   BallsArray getAll() throws JAXBException {
       return iBallService.getAll();
   }


    @PostMapping(value = "/", consumes = "application/json")
    @ResponseBody
    Ball add(@RequestBody String ball) {
        System.out.println(ball);
        ObjectMapper om = new ObjectMapper();


        try {
           Ball createdBall = om.readValue(ball, Ball.class);
            iBallService.addOne(createdBall);
            return createdBall;
        } catch (JsonMappingException e) {

            e.printStackTrace();
            return null;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }

    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    @ResponseBody
    int update(@RequestBody String stringBall, @PathVariable int id) {
        ObjectMapper om = new ObjectMapper();
        Ball newBall=new Ball();
        try {
            newBall = om.readValue(stringBall, Ball.class);
            return iBallService.update(newBall, id);
        } catch (JsonMappingException e) {

            e.printStackTrace();
        } catch (JsonProcessingException | JAXBException e) {

            e.printStackTrace();
        }
        return 0;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    Integer delete(@PathVariable("id") int id) throws JAXBException {
        return iBallService.delete(id);
    }

}
