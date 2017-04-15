package be.mira.jongeren.administration.config;

import be.mira.jongeren.administration.util.template_engine.JadeViewResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import ro.pippo.core.Application;
import ro.pippo.core.PippoConstants;
import ro.pippo.core.PippoSettings;
import ro.pippo.core.RuntimeMode;
import ro.pippo.jade.JadeTemplateEngine;

@Configuration
public class JadeTemplateConfig {

    @Bean
    public JadeTemplateEngine jadeTemplateEngine(){
        JadeTemplateEngine templateEngine = new JadeTemplateEngine();

        PippoSettings pippoSettings = new PippoSettings(RuntimeMode.DEV);
        pippoSettings.overrideSetting(PippoConstants.SETTING_TEMPLATE_PATH_PREFIX, "/templates");

        templateEngine.init(
            new Application(pippoSettings)
        );
        return templateEngine;
    }

    @Bean
    public ViewResolver jadeViewResolver(@Autowired JadeTemplateEngine templateEngine){
        JadeViewResolver resolver = new JadeViewResolver();
        resolver.setTemplateEngine(templateEngine);
        return resolver;
    }
}
