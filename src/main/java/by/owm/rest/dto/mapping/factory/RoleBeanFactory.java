package by.owm.rest.dto.mapping.factory;

import by.owm.rest.dto.RoleDto;
import org.dozer.BeanFactory;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleBeanFactory implements BeanFactory {

    private final DozerBeanMapper mapper;

    @Autowired
    public RoleBeanFactory(final DozerBeanMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Object createBean(final Object object,
                             final Class<?> sourceClass,
                             final String targetBeanId) {

        if (!(object instanceof RoleDto)) {
            throw new IllegalArgumentException("Source is null or doesn't instance of CandidateDTO.");
        }

        final RoleDto candidateDTO = (RoleDto) object;

        return null;
    }
}
