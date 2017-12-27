package edu.swarthmore.cs71.webAppProto;

import edu.swarthore.cs71.scheduleGenerator.ScheduleGeneratorModel;
import spark.ModelAndView;
import spark.Service;
import spark.TemplateViewRoute;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.Map;

public class Main {

    /*
    * Landing page template view route that keeps track of userId (number of visitors)
    * and also loads landing.vm which contains the starting form.
    * */
    private static final TemplateViewRoute landing = (request, response) ->
    {
        int userID;
        if(request.session().attribute("userID") == null){
            userID = 0;
        } else{
            userID = request.session().attribute("userID");
        }
        userID++;
        request.session().attribute("userID", userID);

        Map<String, String> viewModel = new HashMap<>();
        viewModel.put("userID", String.valueOf(userID));
        return new ModelAndView(viewModel, "templates/landing.vm");
    };

    /*
    * Given get parameters provided by the form on landing.vm, determines whether or not
    * the user can graduate based off of the schedule generator model. (which is just an outline
    * and doesnt really mean anything). Just to show the interaction with a model and a form.
    * */
    private static final TemplateViewRoute result = (request, response) -> {
        HashMap<String, String> model =  new HashMap<>();
        String classYear = request.queryParams("classYear");
        String major = request.queryParams("major");
        String minor = request.queryParams("minor");

        ScheduleGeneratorModel myModel = new ScheduleGeneratorModel(classYear, major, minor);
        String semestersLeft = myModel.getSemestersLeft();
        Boolean canGraduate = myModel.getIfCanGraduate();
        model.put("semestersLeft", semestersLeft);
        if(canGraduate){
            model.put("canOrCannot", "can");
        }
        else{
            model.put("canOrCannot", "cannot");
        }
        model.put("major", major);
        return new ModelAndView(model, "templates/resultSchedule.vm");
    };

    /*
    * When user hits landing page they submit a form in landing.vm and the information,
    * when submitted, is passed to the result template view route.
    * */
    public static void main(String[] args) {
        Service service = Service.ignite();
        service.port(35789);
        service.staticFiles.location("/pages");

        service.get("/landing",
                landing,
                new VelocityTemplateEngine());
        service.get("/resultSchedule",
                result,
                new VelocityTemplateEngine());
        service.init();
    }

}
