package by.owm.rest.dto.mapping.factory;

import by.owm.domain.mapper.MapperManagement;
import by.owm.domain.model.User;
import by.owm.rest.dto.RoleDto;
import by.owm.rest.dto.UserDto;
import org.dozer.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class User2UserDtoFactory implements BeanFactory {

    private final MapperManagement mapper;

    @Autowired
    public User2UserDtoFactory(final MapperManagement mapper) {
        this.mapper = mapper;
    }

    @Override
    public Object createBean(final Object source,
                             final Class<?> sourceClass,
                             final String targetBeanId) {

        if (!(source instanceof User)) {
            throw new IllegalArgumentException("Source is null or doesn't instance of SourceDTO.");
        }

        final User user = (User) source;

        final List<RoleDto> roleDtos = user.getRoles().stream()
                .map(role -> mapper.map(role, RoleDto.class))
                .collect(toList());

        return new UserDto(
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getToken(),
                roleDtos);
    }
}
