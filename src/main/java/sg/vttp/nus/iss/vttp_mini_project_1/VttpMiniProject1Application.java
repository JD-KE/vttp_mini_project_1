package sg.vttp.nus.iss.vttp_mini_project_1;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VttpMiniProject1Application implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(VttpMiniProject1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		String availableDate = LocalDate.now().minusDays(12L).toString();

		System.out.println(availableDate);

		System.out.println( String.format("%08d", 1));
	}

}
