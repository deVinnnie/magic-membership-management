package be.mira.jongeren.administration.util.template_engine;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import ro.pippo.jade.JadeTemplateEngine;

import java.util.Locale;

public class JadeViewResolver implements ViewResolver {

    private JadeTemplateEngine templateEngine;

    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
        return new JadeView(viewName, this.getTemplateEngine());
    }

    public JadeTemplateEngine getTemplateEngine() {
        return templateEngine;
    }

    public void setTemplateEngine(JadeTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }
}
