package arvato.de;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@SpringBootApplication
public class DeApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(DeApplication.class, args);

        Thread.sleep(2000);

        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().browse(new URI("http://localhost:8080"));
        } else {
            System.out.println("Desktop nicht unterstützt, bitte Browser manuell öffnen: http://localhost:8080");
        }
	}

}
