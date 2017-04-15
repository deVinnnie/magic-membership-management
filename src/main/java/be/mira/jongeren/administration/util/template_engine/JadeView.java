package be.mira.jongeren.administration.util.template_engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.View;
import ro.pippo.core.Application;
import ro.pippo.core.PippoConstants;
import ro.pippo.core.PippoSettings;
import ro.pippo.core.RuntimeMode;
import ro.pippo.jade.JadeTemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class JadeView implements View{

    private String templateName;

    private JadeTemplateEngine templateEngine;

    public JadeView(String templateName, JadeTemplateEngine templateEngine) {
        this.templateName = templateName;
        this.templateEngine = templateEngine;
    }

    @Override
    public String getContentType() {
        return "text/html";
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        templateEngine.renderResource(templateName, (Map<String, Object>) model, response.getWriter());
    }
}
