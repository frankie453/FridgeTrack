package ECSE428.Group6.FridgeTrack;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@SpringBootApplication
@RestController
public class FridgeTrackApplication {
	@GetMapping("/root")
	public String greeting(){
		return "Hello world!";
	}


	public static void main(String[] args) {
		SpringApplication.run(FridgeTrackApplication.class, args);
	}

}
