import java.util.HashMap;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Date;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.*;

public class App {
  // public static void main(String[] args) {
  //   staticFileLocation("/public");
  //   String layout = "templates/layout.vtl";
  //   SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
  //
  //   get("/", (request, response) -> {
  //     Map<String, Object> model = new HashMap<String, Object>();
  //
  //     model.put("template", "templates/index.vtl");
  //     return new ModelAndView(model, layout);
  //   }, new VelocityTemplateEngine());
  //
  //   get("/doctor/new", (request, response) -> {
  //     Map<String, Object> model = new HashMap<String, Object>();
  //
  //     model.put("template", "templates/form.vtl");
  //     return new ModelAndView(model, layout);
  //   }, new VelocityTemplateEngine());
  //
  //   get("/doctors", (request, response) -> {
  //     Map<String, Object> model = new HashMap<String, Object>();
  //
  //     model.put("template", "templates/doctors.vtl");
  //     return new ModelAndView(model, layout);
  //   }, new VelocityTemplateEngine());
  //
  //   post("/doctors", (request, response) -> {
  //     Map<String, Object> model = new HashMap<String, Object>();
  //     response.redirect("/doctors");
  //     model.put("template", "templates/doctors.vtl");
  //     return new ModelAndView(model, layout);
  //   }, new VelocityTemplateEngine());
  //
  // }
}
