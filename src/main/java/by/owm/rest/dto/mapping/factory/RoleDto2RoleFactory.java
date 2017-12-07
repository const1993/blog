package by.owm.rest.dto.mapping.factory;

import by.owm.domain.model.Role;
import by.owm.rest.dto.RoleDto;
import org.dozer.BeanFactory;
import org.springframework.stereotype.Component;

@Component
public class RoleDto2RoleFactory implements BeanFactory {

    @Override
    public Object createBean(final Object source,
                             final Class<?> sourceClass,
                             final String targetBeanId) {

        if (!(source instanceof RoleDto)) {
            throw new IllegalArgumentException("Source is null or doesn't instance of SourceDTO.");
        }

        final RoleDto roleDto = (RoleDto) source;

        return new Role(roleDto.getAuthority());
    }
}