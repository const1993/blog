package by.owm.rest.dto.mapping.factory;

import by.owm.rest.dto.TokenDto;
import org.dozer.BeanFactory;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokenBeanFactory implements BeanFactory {

    private final DozerBeanMapper mapper;

    @Autowired
    public TokenBeanFactory(final DozerBeanMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Object createBean(final Object source,
                             final Class<?> sourceClass,
                             final String targetBeanId) {

        if (!(source instanceof TokenDto)) {
            throw new IllegalArgumentException("Source is null or doesn't instance of RequestDTO.");
        }

        final TokenDto requestDTO = (TokenDto) source;

        return null;
    }
}
