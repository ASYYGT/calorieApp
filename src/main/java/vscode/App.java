package vscode;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

public class App {
    
    public static double calorie(ArrayList<Double> inputList, double age, double size, double weight){


        double alinmasiGerekenKalori = 66 + (9.6*weight) + (1.8 * size) + (4.7 * age);
        inputList.add(age);
        inputList.add(size);
        inputList.add(weight);
        return alinmasiGerekenKalori;
    }

    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        get("/", (req, res) -> "Merhaba Dünya");
        post("/compute", (req, res) -> {

            double ageI = Integer.parseInt(req.queryParams("input1")) ;
            double sizeI = Integer.parseInt(req.queryParams("input2")) ;
            double weightI = Integer.parseInt(req.queryParams("input3")) ;
           
           
            java.util.ArrayList<Double> inputList = new java.util.ArrayList<>();
            
                inputList.add(ageI);
                inputList.add(sizeI);
                inputList.add(weightI);
            System.out.println(inputList);
            double age = inputList.get(0);
            double size = inputList.get(1);
            double weight = inputList.get(2);
            double result = App.calorie(inputList, ageI, sizeI, weightI);
            
            
            Map<String, Double> map = new HashMap<String, Double>();
            map.put("result", result );
            map.put("yas", age );
            map.put("boy", size );
            map.put("ağırlık", weight );
            return new ModelAndView(map, "compute.mustache");
        }, new MustacheTemplateEngine());
        get("/compute",
                (rq, rs) -> {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("result", "Deger Girilmedi");
                    return new ModelAndView(map, "compute.mustache");
                },
                new MustacheTemplateEngine());
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }
}