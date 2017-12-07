package by.owm.rest.dto.mapping.factory;

import by.owm.rest.dto.RegisterUserDto;
import org.dozer.BeanFactory;
import org.springframework.stereotype.Component;

@Component
public class UserBeanFactory implements BeanFactory {

    @Override
    public Object createBean(final Object source,
                             final Class<?> sourceClass,
                             final String targetBeanId) {

        if (!(source instanceof RegisterUserDto)) {
            throw new IllegalArgumentException("Source is null or doesn't instance of SourceDTO.");
        }

        final RegisterUserDto attachmentDTO = (RegisterUserDto) source;

        return null;
    }
}
