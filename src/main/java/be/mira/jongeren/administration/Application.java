package be.mira.jongeren.administration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Configuration
    //@Profile("dev")
    static class ConfigureJSFContextParameters implements ServletContextInitializer {

        @Override
        public void onStartup(ServletContext servletContext)
                throws ServletException {

            servletContext.setInitParameter("javax.faces.DEFAULT_SUFFIX",
                    ".xhtml");
            servletContext.setInitParameter(
                    "javax.faces.PARTIAL_STATE_SAVING_METHOD", "true");
            servletContext.setInitParameter("javax.faces.PROJECT_STAGE",
                    "Development");
            servletContext.setInitParameter("facelets.DEVELOPMENT", "true");
            servletContext.setInitParameter(
                    "javax.faces.FACELETS_REFRESH_PERIOD", "1");
            servletContext.setInitParameter("primefaces.THEME",
                    "delta");

        }
    }
}
