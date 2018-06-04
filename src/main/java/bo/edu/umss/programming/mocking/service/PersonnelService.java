package bo.edu.umss.programming.mocking.service;

import bo.edu.umss.programming.mocking.domain.Personnel;
import bo.edu.umss.programming.mocking.exception.NotValidPersonnelException;
import bo.edu.umss.programming.mocking.repository.PersonnelRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PersonnelService {

    private final PersonnelRepository personnelRepository;

    public PersonnelService(PersonnelRepository personnelRepository) {
        this.personnelRepository = personnelRepository;
    }

    public Personnel registerPersonnel(Personnel personnel) throws NotValidPersonnelException {
        if(personnelHasNullValues(personnel) || personnelHasInvalidData(personnel))   {
            throw new NotValidPersonnelException();
        }
        return personnelRepository.save(personnel);
    }

    public Boolean personnelHasNullValues(Personnel personnel)    {
        Boolean hasNullValues = false;
        if(personnel.getFullName()==null
                || personnel.getNationalID()==null
                || personnel.getBirthDate()==null
                || personnel.getPhone()==null
                || personnel.getAddress()==null
                || personnel.getPosition()==null) {
            hasNullValues = true;
        }
        return hasNullValues;
    }

    public Boolean personnelHasInvalidData(Personnel personnel)   {
        Boolean hasInvalidData= true;
        Pattern isCelPhone = Pattern.compile("[67]\\d{7}");
        Pattern isFullNameOrPosition = Pattern.compile("([a-zA-Záéíóú]+)([ ]+[a-zA-Záéíóú]+)*");
        Pattern isAddress = Pattern.compile("([a-zA-Z0-9.#áéíóú]+)( [a-zA-Z0-9.#áéíóú]+)*");
        Pattern isNationalID = Pattern.compile("\\d+ [a-zA-Z]{2,}");

        Matcher fullName = isFullNameOrPosition.matcher(personnel.getFullName());
        Matcher nationalID = isNationalID.matcher(personnel.getNationalID());
        Matcher phone = isCelPhone.matcher(personnel.getPhone().toString());
        Matcher address = isAddress .matcher(personnel.getAddress());
        Matcher position = isFullNameOrPosition.matcher(personnel.getPosition());

        if(fullName.matches()
                && nationalID.matches()
                && phone.matches()
                && address.matches()
                && position.matches())   {
            hasInvalidData = false;
        }
        return hasInvalidData;
    }

    public List<Personnel> retrieveRegisteredPersonnelList() {
        return this.personnelRepository.findAll();
    }

    public List<Personnel> retrieveRegisteredPersonnelList(String criteria, String order) {
        Sort.Direction direction = order.equals("DESC")?Sort.Direction.DESC:Sort.Direction.ASC;
        return this.personnelRepository.findAll(Sort.by(new Sort.Order(direction, criteria)));
    }

    public Personnel searchById(String s) {
        Optional<Personnel> optional = this.personnelRepository.findById(s);
        return optional.orElse(null);
    }

    public Personnel searchByFullName(String s) {
        return this.personnelRepository.findPersonnelByFullName(s);
    }

    public Personnel searchByNationalID(String s) {
        return this.personnelRepository.findPersonnelByNationalID(s);
    }

}

