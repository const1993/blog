package by.owm.domain.mapper;

public interface MapperManagment {

    <T> T map(Object source, Class<T> destinationClass);
}
