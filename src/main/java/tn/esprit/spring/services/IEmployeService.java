package tn.esprit.spring.services;

import tn.esprit.spring.entities.Employe;

import java.util.List;

public interface IEmployeService {

    List<Employe> retrieveAllEmployees();

    Employe addEmployee(Employe employe);

    void deleteEmployee(String id);

    Employe updateEmployee(Employe employe);

    Employe retrieveEmployee(String id);

    Employe getEmployeeByName(String name);
} 
