package bo.edu.umss.programming.mocking;

import bo.edu.umss.programming.mocking.domain.Personnel;
import bo.edu.umss.programming.mocking.exception.NotValidPersonnelException;
import bo.edu.umss.programming.mocking.mock.MockPersonnelService;
import org.junit.Before;
import org.junit.Test;
import java.util.Calendar;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class PersonnelServiceTest {
    private MockPersonnelService mockPersonnelService;
    private Personnel personnel;
    private Personnel personnel2;
    private Personnel personnel3;
    private Personnel personnel4;
    private Personnel personnelWithNullValues;
    private Personnel personnelWithInvalidData;

    @Before
    public void setUp() throws Exception {
        mockPersonnelService = new MockPersonnelService();
        Calendar calendar = Calendar.getInstance();

        personnel = new Personnel();
        personnel.setFullName("Pepito Juarez");
        personnel.setNationalID("654520 TJ");
        calendar.set(1999, Calendar.MAY, 6);//YYYY,MM,DD
        personnel.setBirthDate(calendar.getTime());
        personnel.setPhone(76587222);
        personnel.setAddress("B. Las Cuadras");
        personnel.setPosition("Cabo");

        personnel2 = new Personnel();
        personnel2.setFullName("Jon Doe");
        personnel2.setNationalID("212735 SC");
        calendar.set(1987, Calendar.APRIL, 7);//YYYY,MM,DD
        personnel2.setBirthDate(calendar.getTime());
        personnel2.setPhone(70770777);
        personnel2.setAddress("Av. Sucre #1999");
        personnel2.setPosition("Cabo");

        personnel3 = new Personnel();
        personnel3.setFullName("Marco Botton");
        personnel3.setNationalID("134056 CB");
        calendar.set(1989, Calendar.MAY, 13);//YYYY,MM,DD
        personnel3.setBirthDate(calendar.getTime());
        personnel3.setPhone(70770777);
        personnel3.setAddress("Av. Sucre #1999");
        personnel3.setPosition("Sargento");

        personnel4 = new Personnel();
        personnel4.setFullName("Valerie Liberty");
        personnel4.setNationalID("231256 LP");
        calendar.set(1993, Calendar.NOVEMBER, 18);//YYYY,MM,DD
        personnel4.setBirthDate(calendar.getTime());
        personnel4.setPhone(74392312);
        personnel4.setAddress("Av. Pando #121");
        personnel4.setPosition("Sargento");

        personnelWithNullValues = new Personnel();

        personnelWithInvalidData = new Personnel();
        personnelWithInvalidData.setFullName("Peter123");
        personnelWithInvalidData.setNationalID("12857263");
        calendar.set(1999, Calendar.MAY, 6);//YYYY,MM,DD
        personnelWithInvalidData.setBirthDate(calendar.getTime());
        personnelWithInvalidData.setPhone(16587222);
        personnelWithInvalidData.setAddress("4324132899839482948293");
        personnelWithInvalidData.setPosition("Cabo@Coronel.com");
    }

    //Funcionalidad 1
    @Test
    public void testRegisteredPersonnelIsReturned() throws Exception {
        assertNotNull(mockPersonnelService.registerPersonnel(personnel));
    }

    @Test
    public void testRegisteredPersonnelHasId() throws Exception {
        assertNotNull((mockPersonnelService.registerPersonnel(personnel)).getId());
    }

    @Test
    public void testRegisteredPersonnelHasRegistrationDate() throws Exception {
        assertNotNull((mockPersonnelService.registerPersonnel(personnel)).getRegistrationDate());
    }

    @Test
    public void testRegisteredPersonnelHasUniqueId() throws Exception {
        assertNotEquals(
                mockPersonnelService.registerPersonnel(personnel).getId(),
                mockPersonnelService.registerPersonnel(personnel2).getId());
    }

    @Test
    public void testPersonnelToBeRegisteredDoesNotHaveNullValues() throws Exception {
        assertFalse(mockPersonnelService.personnelHasNullValues(personnel));
    }

    @Test
    public void testPersonnelToBeRegisteredHasNullValues() throws Exception {
        assertTrue(mockPersonnelService.personnelHasNullValues(personnelWithNullValues));
    }

    @Test
    public void testPersonnelToBeRegisteredHasValidData() throws Exception {
        assertFalse(
                "Personnel has valid data",
                mockPersonnelService.personnelHasInvalidData(personnel));
    }

    @Test
    public void testPersonnelToBeRegisteredHasInvalidData() throws Exception {
        assertTrue(
                "Personnel has invalid data",
                mockPersonnelService.personnelHasInvalidData(personnelWithInvalidData));
    }

    @Test(expected = NotValidPersonnelException.class)
    public void testExceptionThrownWhenPersonnelToBeRegisteredIsNotValid() throws Exception {
        mockPersonnelService.registerPersonnel(personnelWithNullValues);
    }

    @Test(expected = NotValidPersonnelException.class)
    public void testExceptionThrownWhenPersonnelToBeRegisteredIsNotValid2() throws Exception {
        mockPersonnelService.registerPersonnel(personnelWithInvalidData);
    }

    //Funcionalidad 2
    @Test
    public void testRegisteredPersonnelListExists() throws Exception {
        assertNotNull(mockPersonnelService.retrieveRegisteredPersonnelList());
    }

    @Test
    public void testRegisteredPersonnelListIsNotEmpty() throws Exception {
        mockPersonnelService.registerPersonnel(personnel);
        assertFalse(mockPersonnelService.retrieveRegisteredPersonnelList().isEmpty());
    }

    @Test
    public void testRegisteredPersonnelIsOnRegisteredPersonnelList() throws Exception {
        mockPersonnelService.registerPersonnel(personnel);
        mockPersonnelService.registerPersonnel(personnel2);
        assertThat(mockPersonnelService.retrieveRegisteredPersonnelList(),
                hasItems(personnel, personnel2));
    }

    //Funcionalidad 3
    @Test
    public void testSortByFullNameAsc() throws Exception {
        mockPersonnelService.registerPersonnel(personnel);
        mockPersonnelService.registerPersonnel(personnel2);
        mockPersonnelService.registerPersonnel(personnel3);
        mockPersonnelService.registerPersonnel(personnel4);
        assertThat(
                mockPersonnelService.retrieveRegisteredPersonnelList(
                        "fullName","ASC"),
                contains(personnel2, personnel3, personnel, personnel4));
    }

    @Test
    public void testSortByNationalIDAsc() throws Exception {
        mockPersonnelService.registerPersonnel(personnel);
        mockPersonnelService.registerPersonnel(personnel2);
        mockPersonnelService.registerPersonnel(personnel3);
        mockPersonnelService.registerPersonnel(personnel4);
        assertThat(
                mockPersonnelService.retrieveRegisteredPersonnelList(
                        "nationalID","ASC"),
                contains(personnel3, personnel2, personnel4, personnel));
    }

    @Test
    public void testSortByBirthDateAsc() throws Exception {
        mockPersonnelService.registerPersonnel(personnel);
        mockPersonnelService.registerPersonnel(personnel2);
        mockPersonnelService.registerPersonnel(personnel3);
        mockPersonnelService.registerPersonnel(personnel4);
        assertThat(
                mockPersonnelService.retrieveRegisteredPersonnelList(
                        "birthDate","ASC"),
                contains(personnel2, personnel3, personnel4, personnel));
    }

    @Test
    public void testSortByNameDesc() throws Exception {
        mockPersonnelService.registerPersonnel(personnel);
        mockPersonnelService.registerPersonnel(personnel2);
        mockPersonnelService.registerPersonnel(personnel3);
        mockPersonnelService.registerPersonnel(personnel4);
        assertThat(
                mockPersonnelService.retrieveRegisteredPersonnelList(
                        "fullName","DESC"),
                contains(personnel4, personnel, personnel3, personnel2));

    }

    @Test
    public void testSortByNationalIDDesc() throws Exception {
        mockPersonnelService.registerPersonnel(personnel);
        mockPersonnelService.registerPersonnel(personnel2);
        mockPersonnelService.registerPersonnel(personnel3);
        mockPersonnelService.registerPersonnel(personnel4);
        assertThat(
                mockPersonnelService.retrieveRegisteredPersonnelList(
                        "nationalID","DESC"),
                contains(personnel, personnel4, personnel2, personnel3));
    }

    @Test
    public void testSortByBirthDateDesc() throws Exception {
        mockPersonnelService.registerPersonnel(personnel);
        mockPersonnelService.registerPersonnel(personnel2);
        mockPersonnelService.registerPersonnel(personnel3);
        mockPersonnelService.registerPersonnel(personnel4);
        assertThat(
                mockPersonnelService.retrieveRegisteredPersonnelList(
                        "birthDate","DESC"),
                contains(personnel, personnel4, personnel3, personnel2));
    }

    //Funcionalidad 3
    @Test
    public void testSearchByFullName() throws Exception {
        mockPersonnelService.registerPersonnel(personnel);
        mockPersonnelService.registerPersonnel(personnel2);
        mockPersonnelService.registerPersonnel(personnel3);
        mockPersonnelService.registerPersonnel(personnel4);
        assertEquals(
                mockPersonnelService.searchByFullName("Pepito Juarez").getFullName(),
                "Pepito Juarez");
    }

    @Test
    public void testSearchByFullName2() throws Exception {
        personnel = mockPersonnelService.registerPersonnel(personnel);
        personnel2 = mockPersonnelService.registerPersonnel(personnel2);
        personnel3 = mockPersonnelService.registerPersonnel(personnel3);
        personnel4 = mockPersonnelService.registerPersonnel(personnel4);
        assertEquals(
                mockPersonnelService.searchByFullName("Pepito Juarez"),
                personnel);
    }

    @Test
    public void testSearchByNationalID() throws Exception {
        mockPersonnelService.registerPersonnel(personnel);
        mockPersonnelService.registerPersonnel(personnel2);
        mockPersonnelService.registerPersonnel(personnel3);
        mockPersonnelService.registerPersonnel(personnel4);
        assertEquals(
                mockPersonnelService.searchByNationalID("134056 CB").getNationalID(),
                "134056 CB");
    }

    @Test
    public void testSearchByNationalID2() throws Exception {
        mockPersonnelService.registerPersonnel(personnel);
        mockPersonnelService.registerPersonnel(personnel2);
        mockPersonnelService.registerPersonnel(personnel3);
        mockPersonnelService.registerPersonnel(personnel4);
        assertEquals(
                mockPersonnelService.searchByNationalID("134056 CB"),
                personnel3);
    }
}

