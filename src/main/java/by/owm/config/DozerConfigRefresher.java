package by.owm.config;

import org.dozer.BeanFactory;
import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import java.util.Collection;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

@Configuration
public class DozerConfigRefresher {

    @Autowired
    private Collection<BeanFactory> beanFactories;

    @Autowired
    private Collection<BeanMappingBuilder> builders;

    @Autowired
    private DozerBeanMapper mapper;

    @EventListener
    public void handleContextRefresh(final ContextRefreshedEvent event) {
        builders.forEach(mapper::addMapping);
        mapper.setFactories(beanFactories.stream()
                .collect(toMap(value -> value.getClass().getName(), identity())));
    }
}
