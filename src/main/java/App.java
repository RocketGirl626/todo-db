import java.util.ArrayList;
import java.util.HashMap;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      model.put("tasks", Task.all());
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/tasks", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      String description = request.queryParams("description");
      Integer categoryId = 1;
      Task newTask = new Task(description, categoryId);

      newTask.save();
      model.put("template", "templates/success.vtl");
      return new ModelAndView(model, layout);
     }, new VelocityTemplateEngine());

      post("/tasks/:id/delete", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Task task = Task.find(Integer.parseInt(request.params("id")));
      task.delete();
      model.put("template", "templates/task.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
