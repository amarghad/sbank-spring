package ma.amarghad.sbank.mappers;

public interface Mapper<T, U> {

    U toDto(T entity);
    T toEntity(U dto);

}
