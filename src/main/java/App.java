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
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/tasks", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      ArrayList<Task> tasks = request.session().attribute("Tasks");

      if (tasks == null) {
        tasks = new ArrayList<Task>();
        request.session().attribute("tasks", tasks);
      }

      String description = request.queryParams("description");
      Integer categoryId = 1;
      Task newTask = new Task(description, categoryId);

      tasks.add(newTask);

      model.put("template", "templates/success.vtl");
      return new ModelAndView(model, layout);
     }, new VelocityTemplateEngine());

  }
}
