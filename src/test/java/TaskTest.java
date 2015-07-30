import org.junit.*;
import static org.junit.Assert.*;

public class TaskTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_savesIntoDatabase_true() {
    Task myTask = new Task("Mow the lawn", 1);
    myTask.save();
    assertEquals(Task.all().get(0).getDescription(), "Mow the lawn");
  }

  @Test
  public void find_findsTaskInDatabase_true() {
    Task myTask = new Task("Mow the lawn", 1);
    myTask.save();
    Task savedTask = Task.find(myTask.getId());
    assertEquals(savedTask.getDescription(), "Mow the lawn");
  }

  @Test
  public void save_savesCategoryIdIntoDB_true() {
  Category myCategory = new Category("Household chores");
  myCategory.save();
  Task myTask = new Task("Mow the lawn", myCategory.getId());
  myTask.save();
  Task savedTask = Task.find(myTask.getId());
  assertEquals(savedTask.getCategoryId(), myCategory.getId());
  }

}
