package by.owm.rest.dto.mapping.factory;

import by.owm.domain.mapper.MapperManagement;
import by.owm.domain.model.Role;
import by.owm.domain.model.User;
import by.owm.rest.dto.UserDto;
import org.dozer.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class UserDto2UserFactory implements BeanFactory {

    private final MapperManagement mapper;

    @Autowired
    public UserDto2UserFactory(final MapperManagement mapper) {
        this.mapper = mapper;
    }

    @Override
    public Object createBean(final Object source,
                             final Class<?> sourceClass,
                             final String targetBeanId) {

        if (!(source instanceof UserDto)) {
            throw new IllegalArgumentException("Source is null or doesn't instance of SourceDTO.");
        }

        final UserDto user = (UserDto) source;

        final List<Role> roles = user.getRoles().stream()
                .map(role -> mapper.map(role, Role.class))
                .collect(toList());

        return new User(
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getToken(),
                roles);
    }
}