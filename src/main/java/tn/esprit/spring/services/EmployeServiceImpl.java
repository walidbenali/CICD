package tn.esprit.spring.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.repository.EmployeRepository;

import java.util.List;

@Service
public class EmployeServiceImpl implements IEmployeService {

    @Autowired
    EmployeRepository employeRepository;

    private static final Logger l = LogManager.getLogger(EmployeServiceImpl.class);


    @Override
    public List<Employe> retrieveAllEmployees() {
        List<Employe> employes = null;
        try {
            l.info("In retrieveAllUsers() : ");
            employes = (List<Employe>) employeRepository.findAll();
            for (Employe employe : employes) {
                l.debug("employe +++ : " + employe);
            }
            l.info("Out of retrieveAllEmployees() : ");
        } catch (Exception e) {
            l.error("Error in retrieveAllEmployees() : " + e);
        }

        return employes;
    }

    @Override
    public Employe addEmployee(Employe employe) {
        return employeRepository.save(employe);
    }

    @Override
    public void deleteEmployee(String id) {
        Employe employe = employeRepository.findEmployeById(Integer.parseInt(id));
        if (employe != null) {
            employeRepository.delete(employe);
        } else {
            l.info("id does not exist ");
        }

    }

    @Override
    public Employe updateEmployee(Employe employe) {
        Employe e = employeRepository.findEmployeById(employe.getId());
        if (e == null) {
            l.info("employee does not exist ");
            return null;
        }
        return employeRepository.save(employe);
    }

    @Override
    public Employe retrieveEmployee(String id) {
        Employe employe = employeRepository.findEmployeById(Integer.parseInt(id));
        if (employe == null) {
            l.info("id does not exist ");
            return null;
        }
        return employe;
    }

    @Override
    public Employe getEmployeeByName(String name) {
        return employeRepository.findEmployeByNom(name);
    }
}
