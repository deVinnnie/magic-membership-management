package be.mira.jongeren.administration.config;

import org.fusesource.scalate.jade.JadeCodeGenerator;
import org.fusesource.scalate.layout.DefaultLayoutStrategy;
import org.fusesource.scalate.servlet.Config;
import org.fusesource.scalate.servlet.ServletTemplateEngine;
import org.fusesource.scalate.spring.view.ScalateViewResolver;
import org.fusesource.scalate.support.CodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.context.ServletContextAware;
import scala.Tuple2;
import scala.collection.JavaConversions;
import scala.collection.immutable.Map;
import scala.collection.mutable.Set;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@Configuration
public class ScalateConfig {

    public static final String DEFAULT_PREFIX = "/templates/";
    public static final String DEFAULT_SUFFIX = ".jade";
    public static final String DEFAULT_LAYOUT = "/templates/layouts/default.jade";

    @Configuration
    protected static class ScalateConfigConfiguration implements ServletContextAware {

        private ServletContext servletContext;

        @Override
        public void setServletContext(ServletContext servletContext) {
            this.servletContext = servletContext;
        }

        @Bean
        public Config config() {
            return new Config() {

                @Override
                public ServletContext getServletContext() {
                    return servletContext;
                }

                @Override
                public String getName() {
                    return "unknown";
                }

                @Override
                public Enumeration getInitParameterNames() {
                    return null;
                }

                @Override
                public String getInitParameter(String name) {
                    return null;
                }
            };
        }

    }

    @Configuration
    protected static class ScalateServletTemplateEngineConfiguration {

        @Autowired
        private Config config;

        @Bean
        public ServletTemplateEngine servletTemplateEngine() {
            ServletTemplateEngine engine = new ServletTemplateEngine(config);
            List layouts = new ArrayList(1);
            layouts.add(DEFAULT_LAYOUT);
            engine.layoutStrategy_$eq(new DefaultLayoutStrategy(engine,
                    JavaConversions.asScalaBuffer(layouts)));

            Map<String, CodeGenerator> newCodeGenerators = engine.codeGenerators().$plus(new Tuple2<>("pug", new JadeCodeGenerator()));
            engine.codeGenerators_$eq(newCodeGenerators);

            return engine;
        }
    }

    @Configuration
    protected static class ScalateViewResolverConfiguration {

        @Autowired
        private ServletTemplateEngine servletTemplateEngine;

        @Bean
        public ScalateViewResolver scalateViewResolver() {
            //Map<String, CodeGenerator> codeGenerators = servletTemplateEngine.codeGenerators();

            /*HashMap<String, CodeGenerator> newCodeGenerators = new HashMap<>();
            newCodeGenerators.put("ssp", new SspCodeGenerator());
            newCodeGenerators.put("scaml", new ScamlCodeGenerator());
            newCodeGenerators.put("mustache", new MustacheCodeGenerator());
            newCodeGenerators.put("jade", new JadeCodeGenerator());
            newCodeGenerators.put("pug", new JadeCodeGenerator());*/



            scala.collection.mutable.Map<String, Set<String>> stringSetMap = servletTemplateEngine.extensionToTemplateExtension();

            /*Set<String> set = new HashSet<String>();
            set.add("pug");
            stringSetMap.put("jade", set);*/

            //servletTemplateEngine.extensionToTemplateExtension_$eq(stringSetMap);

            System.out.println(servletTemplateEngine.extensions());
            System.out.println(servletTemplateEngine.templateExtensionsFor("pug"));
            System.out.println(servletTemplateEngine.codeGenerators().keySet().toList());

            ScalateViewResolver resolver = new ScalateViewResolver();
            resolver.templateEngine_$eq(servletTemplateEngine);
            resolver.setOrder(Ordered.LOWEST_PRECEDENCE - 20);
            resolver.setPrefix(DEFAULT_PREFIX);
            resolver.setSuffix(DEFAULT_SUFFIX);
            return resolver;
        }

    }

}
