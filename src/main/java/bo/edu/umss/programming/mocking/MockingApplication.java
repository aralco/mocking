package bo.edu.umss.programming.mocking;

import bo.edu.umss.programming.mocking.domain.Personnel;
import bo.edu.umss.programming.mocking.exception.NotValidPersonnelException;
import bo.edu.umss.programming.mocking.repository.PersonnelRepository;
import bo.edu.umss.programming.mocking.service.PersonnelService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class MockingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MockingApplication.class, args);
	}

	@Bean
	public ApplicationRunner run(PersonnelService personnelService)	{
		Personnel personnel;
		Personnel personnel2;
		Personnel personnel3;
		Personnel personnel4;
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


		return args ->
		{
			Stream.of(personnel, personnel2, personnel3, personnel4)
					.forEach(x -> {
						try {
							System.out.printf("++++++++++++ Operation -> Register new Personnel: %s ++++++++++++\n", x);
							personnelService.registerPersonnel(x);
							List<Personnel> personnels = personnelService.retrieveRegisteredPersonnelList();
							displayPersonnelList(personnels);
						} catch (NotValidPersonnelException e) {
							e.printStackTrace();
						}
					});
			System.out.printf("++++++++++++ Operation -> SortBy 'FullName', ASC ++++++++++++\n");
			List<Personnel> personnels = personnelService.retrieveRegisteredPersonnelList("fullName", "ASC");
			displayPersonnelList(personnels);

			System.out.printf("++++++++++++ Operation -> SortBy 'BirthDate', DESC ++++++++++++\n");
			personnels = personnelService.retrieveRegisteredPersonnelList("birthDate", "DESC");
			displayPersonnelList(personnels);

			System.out.printf("++++++++++++ Operation -> Search for: 'Pepito Juarez' ++++++++++++\n");
			Personnel found = personnelService.searchByFullName("Pepito Juarez");
			List<Personnel> personnelList = new ArrayList<>(1);
			personnelList.add(found);
			displayPersonnelList(personnelList);

		};
	}

	public static void displayPersonnelList(List<Personnel> personnels)    {
		System.out.println("-----------------------------------------------------------------------------------------------------------");
		System.out.printf("| Id | \t Name | \t NationalID | \t BirthDate | \t Phone | \t Address | \t Position | \t Registration Date | \n");
		System.out.println("-----------------------------------------------------------------------------------------------------------");
		for(Personnel p : personnels)   {
			System.out.printf("%s \n", p);
		}
		System.out.println("\n");
	}

}
