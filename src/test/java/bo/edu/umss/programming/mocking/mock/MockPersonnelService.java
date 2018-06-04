package bo.edu.umss.programming.mocking.mock;

import bo.edu.umss.programming.mocking.domain.Personnel;
import bo.edu.umss.programming.mocking.exception.NotValidPersonnelException;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MockPersonnelService {
    private List<Personnel> registeredPersonnels = new ArrayList<>();

    public Personnel registerPersonnel(Personnel personnel) throws NotValidPersonnelException {
        if(personnelHasNullValues(personnel) || personnelHasInvalidData(personnel))   {
            throw new NotValidPersonnelException();
        }
        personnel.setId(UUID.randomUUID().toString());
        personnel.setRegistrationDate(Calendar.getInstance().getTime());
        registeredPersonnels.add(personnel);
        return personnel;
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
        return registeredPersonnels;
    }

    public List<Personnel> retrieveRegisteredPersonnelList(String criteria, String order) {
//        Comparator<Personnel> c = (o1, o2) -> o1.getBirthDate().compareTo(o2.getBirthDate();
        Comparator<Personnel> comparator = null;
        switch (criteria)   {
            case "id":
                comparator = Comparator.comparing(Personnel::getId);
                break;
            case "fullName":
                comparator = Comparator.comparing(Personnel::getFullName);
                break;
            case "birthDate":
                comparator = Comparator.comparing(Personnel::getBirthDate);
                break;
            case "nationalID":
                comparator = Comparator.comparing(Personnel::getNationalID);
                break;
            case "registrationDate":
                comparator = Comparator.comparing(Personnel::getRegistrationDate);
                break;
        }
        Collections.sort(registeredPersonnels, comparator);
        if(order.equals("DESC"))
            Collections.reverse(registeredPersonnels);
        return registeredPersonnels;
    }

    public Personnel searchById(String s) {
        Comparator<Personnel> comparator = Comparator.comparing(Personnel::getId);
        Personnel target = new Personnel();
        target.setId(s);
        registeredPersonnels.sort(comparator);
        int found = Collections.binarySearch(registeredPersonnels, target, comparator);
        return registeredPersonnels.get(found);
    }

    public Personnel searchByFullName(String s) {
        Comparator<Personnel> comparator = Comparator.comparing(Personnel::getFullName);
        Personnel target = new Personnel();
        target.setFullName(s);
        registeredPersonnels.sort(comparator);
        int found = Collections.binarySearch(registeredPersonnels, target, comparator);
        return registeredPersonnels.get(found);
    }

    public Personnel searchByNationalID(String s) {
        Comparator<Personnel> comparator = Comparator.comparing(Personnel::getNationalID);
        Personnel target = new Personnel();
        target.setNationalID(s);
        registeredPersonnels.sort(comparator);
        int found = Collections.binarySearch(registeredPersonnels, target, comparator);
        return registeredPersonnels.get(found);
    }

}

