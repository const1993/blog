package by.owm.domain.mapper;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DozerMapper implements MapperManagment {

    private final Mapper mapper;

    @Autowired
    public DozerMapper(final Mapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public <T> T map(final Object source, final Class<T> destination) {
        return mapper.map(source, destination);
    }
}
