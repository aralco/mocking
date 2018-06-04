package bo.edu.umss.programming.mocking;

import bo.edu.umss.programming.mocking.domain.Personnel;
import bo.edu.umss.programming.mocking.exception.NotValidPersonnelException;
import bo.edu.umss.programming.mocking.service.PersonnelService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PersonnelServiceTestUsingMockito {
    private PersonnelService mockPersonnelService;
    //Mock
    private List<Personnel> registeredPersonnels = new ArrayList<>();
    private Personnel personnelA;
    private Personnel personnelB;
    private Personnel personnelC;
    private Personnel personnelD;

    private Personnel personnel;
    private Personnel personnel2;
    private Personnel personnel3;
    private Personnel personnel4;
    private Personnel personnelWithNullValues;
    private Personnel personnelWithInvalidData;

    @Before
    public void setUp() throws Exception {
        Calendar calendar = Calendar.getInstance();

        //Mock Start
        mockPersonnelService = mock(PersonnelService.class);

        personnelA = new Personnel();
        personnelA.setFullName("Pepito Juarez");
        personnelA.setNationalID("654520 TJ");
        calendar.set(1999, Calendar.MAY, 6);//YYYY,MM,DD
        personnelA.setBirthDate(calendar.getTime());
        personnelA.setPhone(76587222);
        personnelA.setAddress("B. Las Cuadras");
        personnelA.setPosition("Cabo");
        //fields set by register method
        personnelA.setId(UUID.randomUUID().toString());
        personnelA.setRegistrationDate(Calendar.getInstance().getTime());


        personnelB = new Personnel();
        personnelB.setFullName("Jon Doe");
        personnelB.setNationalID("212735 SC");
        calendar.set(1987, Calendar.APRIL, 7);//YYYY,MM,DD
        personnelB.setBirthDate(calendar.getTime());
        personnelB.setPhone(70770777);
        personnelB.setAddress("Av. Sucre #1999");
        personnelB.setPosition("Cabo");
        //fields set by register method
        personnelB.setId(UUID.randomUUID().toString());
        personnelB.setRegistrationDate(Calendar.getInstance().getTime());

        personnelC = new Personnel();
        personnelC.setFullName("Marco Botton");
        personnelC.setNationalID("134056 CB");
        calendar.set(1989, Calendar.MAY, 13);//YYYY,MM,DD
        personnelC.setBirthDate(calendar.getTime());
        personnelC.setPhone(70770777);
        personnelC.setAddress("Av. Sucre #1999");
        personnelC.setPosition("Sargento");
        //fields set by register method
        personnelC.setId(UUID.randomUUID().toString());
        personnelC.setRegistrationDate(Calendar.getInstance().getTime());

        personnelD = new Personnel();
        personnelD.setFullName("Valerie Liberty");
        personnelD.setNationalID("231256 LP");
        calendar.set(1993, Calendar.NOVEMBER, 18);//YYYY,MM,DD
        personnelD.setBirthDate(calendar.getTime());
        personnelD.setPhone(74392312);
        personnelD.setAddress("Av. Pando #121");
        personnelD.setPosition("Sargento");
        //fields set by register method
        personnelD.setId(UUID.randomUUID().toString());
        personnelD.setRegistrationDate(Calendar.getInstance().getTime());

        registeredPersonnels.add(personnelA);
        registeredPersonnels.add(personnelB);
        registeredPersonnels.add(personnelC);
        registeredPersonnels.add(personnelD);

        //Mock End

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
        when(mockPersonnelService.registerPersonnel(personnel)).thenReturn(personnelA);
        assertNotNull(mockPersonnelService.registerPersonnel(personnel));
    }

    @Test
    public void testRegisteredPersonnelHasId() throws Exception {
        when(mockPersonnelService.registerPersonnel(personnel)).thenReturn(personnelA);
        assertNotNull((mockPersonnelService.registerPersonnel(personnel)).getId());
    }

    @Test
    public void testRegisteredPersonnelHasRegistrationDate() throws Exception {
        when(mockPersonnelService.registerPersonnel(personnel)).thenReturn(personnelA);
        assertNotNull((mockPersonnelService.registerPersonnel(personnel)).getRegistrationDate());
    }

    @Test
    public void testRegisteredPersonnelHasUniqueId() throws Exception {
        when(mockPersonnelService.registerPersonnel(personnel)).thenReturn(personnelA);
        when(mockPersonnelService.registerPersonnel(personnel2)).thenReturn(personnelB);
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
        when(mockPersonnelService.personnelHasNullValues(personnelWithNullValues)).thenCallRealMethod();
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
        when(mockPersonnelService.personnelHasInvalidData(personnelWithInvalidData)).thenCallRealMethod();
        assertTrue(
                "Personnel has invalid data",
                mockPersonnelService.personnelHasInvalidData(personnelWithInvalidData));
    }

    @Test(expected = NotValidPersonnelException.class)
    public void testExceptionThrownWhenPersonnelToBeRegisteredIsNotValid() throws Exception {
        when(mockPersonnelService.registerPersonnel(personnelWithNullValues)).thenThrow(NotValidPersonnelException.class);
        mockPersonnelService.registerPersonnel(personnelWithNullValues);
    }

    @Test(expected = NotValidPersonnelException.class)
    public void testExceptionThrownWhenPersonnelToBeRegisteredIsNotValid2() throws Exception {
        when(mockPersonnelService.registerPersonnel(personnelWithInvalidData)).thenThrow(NotValidPersonnelException.class);
        mockPersonnelService.registerPersonnel(personnelWithInvalidData);
    }

    //Funcionalidad 2
    @Test
    public void testRegisteredPersonnelListExists() throws Exception {
        assertNotNull(mockPersonnelService.retrieveRegisteredPersonnelList());
    }

    @Test
    public void testRegisteredPersonnelListIsNotEmpty() throws Exception {
//        mockPersonnelService.registerPersonnel(personnel);
        when(mockPersonnelService.retrieveRegisteredPersonnelList()).thenReturn(registeredPersonnels);
        assertFalse(mockPersonnelService.retrieveRegisteredPersonnelList().isEmpty());
    }

    @Test
    public void testRegisteredPersonnelIsOnRegisteredPersonnelList() throws Exception {
//        mockPersonnelService.registerPersonnel(personnel);
//        mockPersonnelService.registerPersonnel(personnel2);
        when(mockPersonnelService.retrieveRegisteredPersonnelList()).thenReturn(registeredPersonnels);
        assertThat(mockPersonnelService.retrieveRegisteredPersonnelList(),
                hasItems(personnelA, personnelB));
    }
/*
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

    //Funcionalidad 4
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
    }*/
}

