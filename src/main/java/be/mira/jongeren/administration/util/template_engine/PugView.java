package be.mira.jongeren.administration.util.template_engine;

import be.mira.jongeren.administration.util.date.DateUtils;
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

public class PugView implements View{

    private String templateName;

    private JadeTemplateEngine templateEngine;

    public PugView(String templateName, JadeTemplateEngine templateEngine) {
        this.templateName = templateName;
        this.templateEngine = templateEngine;
    }

    @Override
    public String getContentType() {
        return "text/html";
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        modelFilter(model, request, response);

        templateEngine.renderResource(templateName + ".pug", (Map<String, Object>) model, response.getWriter());
    }

    private <T> void modelFilter(Map<String, T> model, HttpServletRequest request, HttpServletResponse response){
        model.put("dateHelper", (T) new DateHelper());
        model.put(PippoConstants.REQUEST_PARAMETER_LANG, (T) "nl");
    }
}
