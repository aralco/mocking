package bo.edu.umss.programming.mocking.repository;

import bo.edu.umss.programming.mocking.domain.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonnelRepository extends JpaRepository<Personnel, String>{
    Personnel findPersonnelByFullName(String fullName);
    Personnel findPersonnelByNationalID(String nationalID);
}
