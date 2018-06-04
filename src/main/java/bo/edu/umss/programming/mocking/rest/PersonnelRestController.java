package bo.edu.umss.programming.mocking.rest;

import bo.edu.umss.programming.mocking.domain.Personnel;
import bo.edu.umss.programming.mocking.service.PersonnelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class PersonnelRestController {
    private final PersonnelService personnelservice;

    public PersonnelRestController(PersonnelService personnelservice) {
        this.personnelservice = personnelservice;
    }

    @GetMapping("/personnels")
    Collection<Personnel> personnelCollection() {
        return this.personnelservice.retrieveRegisteredPersonnelList();
    }

}
