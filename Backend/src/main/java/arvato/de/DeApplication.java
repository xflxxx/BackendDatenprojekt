package arvato.de;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URISyntaxException;

@SpringBootApplication
public class DeApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(DeApplication.class, args);

        Thread.sleep(1000);

        java.awt.Desktop.getDesktop().browse(new java.net.URI("http://localhost:8080"));

	}

}
