package by.owm.rest.dto.mapping;

import by.owm.domain.model.Role;
import by.owm.domain.model.User;
import by.owm.rest.dto.RoleDto;
import by.owm.rest.dto.UserDto;
import by.owm.rest.dto.mapping.factory.User2UserDtoFactory;
import by.owm.rest.dto.mapping.factory.UserDto2UserFactory;
import by.owm.rest.dto.mapping.factory.Role2RoleDtoFactory;
import by.owm.rest.dto.mapping.factory.RoleDto2RoleFactory;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingOption;
import org.springframework.stereotype.Component;

import static org.dozer.classmap.MappingDirection.BI_DIRECTIONAL;

@Component
public class DTO2ModelBuilder extends BeanMappingBuilder {

    @Override
    protected void configure() {
        mapping(
                type(User.class).beanFactory(UserDto2UserFactory.class),
                type(UserDto.class).beanFactory(User2UserDtoFactory.class),
                (TypeMappingOption) builder -> builder.type(BI_DIRECTIONAL)
        );
        mapping(
                type(Role.class).beanFactory(RoleDto2RoleFactory.class),
                type(RoleDto.class).beanFactory(Role2RoleDtoFactory.class),
                (TypeMappingOption) builder -> builder.type(BI_DIRECTIONAL)
        );
    }
}
