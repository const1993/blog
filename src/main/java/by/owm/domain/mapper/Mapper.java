package by.owm.domain.mapper;

public interface Mapper {

    <T> T map(Object source, Class<T> destinationClass);
}
