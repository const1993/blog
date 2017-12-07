package by.owm.rest.dto.mapping.factory;

import by.owm.domain.model.Role;
import by.owm.rest.dto.RoleDto;
import org.dozer.BeanFactory;
import org.springframework.stereotype.Component;

@Component
public class Role2RoleDtoFactory implements BeanFactory {

    @Override
    public Object createBean(final Object source,
                             final Class<?> sourceClass,
                             final String targetBeanId) {

        if (!(source instanceof Role)) {
            throw new IllegalArgumentException("Source is null or doesn't instance of SourceDTO.");
        }

        final Role role = (Role) source;

        final RoleDto roleDto = new RoleDto();
        roleDto.setAuthority(role.getAuthority());

        return roleDto;
    }
}
