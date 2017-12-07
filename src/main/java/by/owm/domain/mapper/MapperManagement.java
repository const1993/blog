package by.owm.domain.mapper;

public interface MapperManagement {

    <T> T map(Object source, Class<T> destinationClass);
}
