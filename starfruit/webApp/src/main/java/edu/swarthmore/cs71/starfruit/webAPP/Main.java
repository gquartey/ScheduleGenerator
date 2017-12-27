package edu.swarthmore.cs71.starfruit.webAPP;

import edu.swarthmore.cs71.starfruit.classes.Result;
import edu.swarthmore.cs71.starfruit.classes.ScheduledCourse;
import edu.swarthmore.cs71.starfruit.classes.SemesterSchedule;
import edu.swarthmore.cs71.starfruit.classes.departments.*;
import edu.swarthmore.cs71.starfruit.classes.departments.Math;
import edu.swarthmore.cs71.starfruit.wrapper.MiddleMan;
import edu.swarthmore.cs71.starfruit.wrapper.Questionnaire;
import org.json.JSONObject;
import spark.ModelAndView;
import spark.Route;
import spark.Service;
import spark.TemplateViewRoute;
import spark.template.velocity.VelocityTemplateEngine;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static final TemplateViewRoute landing = (request,response) ->
    {
        //TODO below is placeholder. Need to use spark session framework to pass secure tokens to keep track of visitors to the site
        int userID;
        if(request.session().attribute("userID") == null){
            userID = 0;
        } else{
            userID = request.session().attribute("userID");
        }
        userID++;
        request.session().attribute("userID", userID);

        //USER DATA questionnaire
        if(request.session().attribute("questionnaire") == null){
            request.session().attribute("questionnaire", new Questionnaire());
        }

        Map<String, String> viewModel = new HashMap<>();
        viewModel.put("userID", String.valueOf(userID));
        return new ModelAndView(viewModel, "templates/landing.vm");
    };
//    private static final TemplateViewRoute result = (request,response) ->
//    {
//        Map<String, String> viewModel = new HashMap<>();
//
//        return new ModelAndView(viewModel, "templates/result.vm");
//    };


    private static final Route storeInterest = (request, response) -> {
        //don't check if there already is  an academic interest because want to be able to overwrite
        Questionnaire temp = request.session().attribute("questionnaire");
        JSONObject message = new JSONObject(request.body());
        Collection<Department> interests = new ArrayList<>();
        String interest =  message.getString("value");
        //I am breaking OCP here because we changed department to a interface so there is not anonymous constructor available now
        if(interest.equals("hum")){
            interests.add(new English() {
            });
            interests.add(new Philosophy() {
            });
            interests.add(new Sociology() {
            });
        }
        else if(interest.equals("ss")){
            interests.add(new Econ() {
            });
            interests.add(new Psychology() {
            });
            interests.add(new PolySci() {
            });
        }
        else if(interest.equals("stem")){
            interests.add(new Math() {
            });
            interests.add(new ComputerScience() {
            });
            interests.add(new Biology() {
            });
        }
        else if(interest.equals("art")){
            interests.add(new Art() {
            });
            interests.add(new ArtHistory() {
            });
        }
        temp.setAcademicInterests(interests);

        request.session().attribute("questionnaire", temp);
        //System.out.printf("%s", temp.getAcademicInterests());
        return "success";
    };
    private static final Route storeHistory = (request, response) -> {
        //don't check if there already is  an academic interest because want to be able to overwrite
        Questionnaire temp = request.session().attribute("questionnaire");
        File transcript = request.attribute("transcript");
        //TODO
        temp.setTranscript(transcript);
        request.session().attribute("questionnaire", temp);
        //System.out.printf("%s", temp.getAcademicInterests());
        return "success";
    };

    private static final Route storeClass = (request, response) -> {
        Questionnaire temp = request.session().attribute("questionnaire");
        JSONObject message = new JSONObject(request.body());
        temp.setClassYear(Integer.valueOf(message.getString("value")));

        request.session().attribute("questionnaire", temp);
        return "success";
    };
    //TODO abroad semester is not a part of questionnaire or user data yet
    /*
    private static final Route abroadSemester = (request, response) -> {
        Questionnaire temp = request.session().attribute("questionnaire");
        JSONObject message = new JSONObject(request.body());

        request.session().attribute("questionnaire", temp);
        System.out.printf("%s", temp.getClassYear());
        return "success";
    };
    */

    private static final TemplateViewRoute result = (request, response) ->
    {
        Questionnaire temp = request.session().attribute("questionnaire");
        System.out.println(temp.getClassYear());
        System.out.println(temp.getMajor1());
        System.out.println(temp.getMajor2());
        System.out.println(temp.getMinor1());
        System.out.println(temp.getMinor2());
        //TODO Never completing the schedule
//        MiddleMan myMan =  new MiddleMan(temp);
//        Collection<SemesterSchedule> schedule = myMan.getSchedule();
//        for(SemesterSchedule semSched: schedule){
//            for (ScheduledCourse j : semSched.getSchedule()) {
//                System.out.println(j.getCourseName());
//            }
//            System.out.println("***************New Semester*******************");
//        }

        Map<String, Result> viewModel = new HashMap<>(); //will take information necessary for results page to display (result class)

        return new ModelAndView(viewModel, "templates/result.vm");

//        else{
//            return new ModelAndView((viewModel), "templates/landing.vm"); //TODO actually handle this
//        }
    };

    private static final Route storeCourse = (request, response) -> {
        Questionnaire temp = request.session().attribute("questionnaire");
        JSONObject message = new JSONObject(request.body());

        //kind of have to have an OCP violation here --> dont know how to fix yet
        //cant create an object because getting info from javascript (or would be hard)
        Boolean success;
        if(message.getString("level").equals("major1")){
           temp.setMajor1(message.getString("field"));
        }
        else if(message.getString("level").equals("major2")){
            temp.setMajor2(message.getString("field"));
        }
        else if(message.getString("level").equals("minor1")){
            temp.setMinor1(message.getString("field"));
        }
        else if(message.getString("level").equals("minor2")){
            temp.setMinor2(message.getString("field"));
        }

        request.session().attribute("questionnaire", temp);

        return "success";
    };


    public static void main(String[] args) {
        Service service = Service.ignite();
        service.port(51459);
        service.staticFiles.location("/staticFs");

        service.get("/landing",
                landing,
                new VelocityTemplateEngine());
        service.get("/result",
                result,
                new VelocityTemplateEngine());
        service.post("/storeInterest",
                storeInterest);
        service.post("/storeClass",
                storeClass);
        service.post("/storeCourse",
                storeCourse);
        service.post("/storeHistory",
                storeHistory);
//        service.get("/submit",
//                submit,
//                new VelocityTemplateEngine());
        /*service.post("/abroadSemester",

               abroadSemester);
        */
        service.init();
    }

}
