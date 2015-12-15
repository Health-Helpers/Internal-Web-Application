package cat.ehh.web.dto;

import java.util.List;

public abstract class DTO<T> {

	public abstract DTO<T> fromEntityToDto(T entity);
	public abstract List<DTO<T>> fromEntityListToDtoList(List<T> entityList);
	
	public abstract T fromDtoToEntity(DTO<T> dto);
	public abstract List<T> fromDtoListToEntityList(List<DTO<T>> dtoList);
	
	public abstract String fromDtoToXML(DTO<T> dto);
	public abstract String fromDtoListToXML(List<DTO<T>> dto);
	
	
	public abstract DTO<T> fromXMLtoDTO(String xml);
	public abstract List<DTO<T>> fromXMLtoDTOList(String xml);
}
