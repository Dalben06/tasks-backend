package br.ce.wcaquino.taskbackend.controller;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;

public class TaskControllerTest {

	@Mock
	private TaskRepo taskRepo;
	
	@InjectMocks
	private TaskController controller;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() {
		try {
			Task model = new Task();
			model.setDueDate(LocalDate.now());
			//TaskController controller = new TaskController();
			controller.save(model);
			Assert.fail("Não deveria chegado nesse ponto");
		} catch (Exception c) {
			// TODO: handle exception
			Assert.assertEquals("Fill the task description", c.getMessage());
		}
		
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() {
		try {
			Task model = new Task();
			model.setTask("Task");
			//TaskController controller = new TaskController();
			controller.save(model);
			Assert.fail("Não deveria chegado nesse ponto");
		} catch (Exception c) {
			// TODO: handle exception
			Assert.assertEquals("Fill the due date", c.getMessage());
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() {
		try {
			Task model = new Task();
			model.setTask("Task");
			model.setDueDate(LocalDate.of(2010, 1, 1));
			//TaskController controller = new TaskController();
			controller.save(model);
			Assert.fail("Não deveria chegado nesse ponto");
		} catch (Exception c) {
			// TODO: handle exception
			Assert.assertEquals("Due date must not be in past", c.getMessage());
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaComSucesso() throws ValidationException {
		Task model = new Task();
		model.setTask("Task");
		model.setDueDate(LocalDate.now());
		//TaskController controller = new TaskController();
		controller.save(model);
		Mockito.verify(taskRepo).save(model);
	}
}
