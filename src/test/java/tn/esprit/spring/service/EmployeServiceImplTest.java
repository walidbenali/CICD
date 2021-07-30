package tn.esprit.spring.service;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.services.IEmployeService;

import java.text.ParseException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeServiceImplTest {

    @Autowired
    IEmployeService es;

    @Test
    public void testRetrieveAllEmployees() {

        // given
        List<Employe> listEmployee = es.retrieveAllEmployees();
        Integer countEmployee = listEmployee.size();
        Employe employe = new Employe("Ben Ali", "Walid", "walidbenali@esprit.tn", "5448", true, Role.INGENIEUR);
        
        // when 
        es.addEmployee(employe);

        // then
        listEmployee = es.retrieveAllEmployees();
        Assert.assertEquals(countEmployee + 1, listEmployee.size());
    }


    @Test
    public void testAddEmployee() throws ParseException {
        // given
        Employe employe = new Employe("Ben Ali", "Walid", "walidbenali@esprit.tn", "5448", true, Role.INGENIEUR);

        // when
        Employe employeeAdded = es.addEmployee(employe);
        
        //then
        Assert.assertEquals(employe.getNom(), employeeAdded.getNom());
    }

    @Test
    public void testUpdateEmployee() throws ParseException {
        // given
        Employe employe = new Employe("Ben Ali", "Walid", "walidbenali@esprit.tn", "5448", true, Role.INGENIEUR);
        Employe employeeAdded = es.addEmployee(employe);

        Employe updateEmploye = new Employe("Ben Ali", "Walou", "walidbenali@esprit.tn", "5448", true, Role.INGENIEUR);

        // when
        Employe employeeUpdated = es.updateEmployee(updateEmploye);
        
        // then
        Assert.assertEquals(employe.getId(), employeeUpdated.getId());
    }

   @Test
   public void testRetrieveEmployee() {
       // given
       Integer idEmployee = createEmployee();

       // when
       Employe employeeRetrieved = es.retrieveEmployee(Integer.toString(idEmployee));
       
       // then
       Assert.assertEquals(idEmployee, employeeRetrieved.getId());
   }

    @Test
    public void testDeleteEmployee() {
        // given
        Integer idEmployee = createEmployee();
        // when
        es.deleteEmployee(Integer.toString(idEmployee));

        // given
        Assert.assertNull(es.retrieveEmployee(Integer.toString(idEmployee)));
    }


    public Integer createEmployee() {
        Employe employe = new Employe("Ben Ali", "Walid", "walidbenali@esprit.tn", "5448", true, Role.INGENIEUR);
        Employe employeeAdded = es.addEmployee(employe);
        return employeeAdded.getId();
    }
}





