package vscode;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.DoubleBinaryOperator;

import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

public class App {
    
    
    public String getGreeting() {
        return "Hello world.";
    }

    public static double Hesapla(ArrayList<Double> array, double age, double size, double weight){


        double alinmasiGerekenKalori = 66 + (9.6*weight) + (1.8 * size) + (4.7 * age);
        array.add(age);
        array.add(size);
        array.add(weight);
        return alinmasiGerekenKalori;
    }

    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        get("/", (req, res) -> "Merhaba Dünya");
        post("/compute", (req, res) -> {
            
            double yasInput = Integer.parseInt(req.queryParams("input1")) ;
            double sigaraInput = Integer.parseInt(req.queryParams("input2")) ;
            double sporInput = Integer.parseInt(req.queryParams("input3")) ;
           
           
            java.util.ArrayList<Double> inputList = new java.util.ArrayList<>();
            
                inputList.add(yasInput);
                inputList.add(sigaraInput);
                inputList.add(sporInput);
            System.out.println(inputList);
            double yas = inputList.get(0);
            double sigara = inputList.get(1);
            double spor = inputList.get(2);
            double result = App.Hesapla(inputList, yasInput, sigaraInput, sporInput);
            
            
            Map<String, Double> map = new HashMap<String, Double>();
            map.put("result", result );
            map.put("yas", yas );
            map.put("sigara", sigara );
            map.put("spor", spor );
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
        return 4567; // return default port if heroku-port isn't set (i.e. on localhost)
    }
}