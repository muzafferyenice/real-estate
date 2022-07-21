package com.realestate.dto.mapper;

import com.realestate.domain.Property;
import com.realestate.dto.PropertyDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface PropertyMapper {

    PropertyDTO propertyToPropertyDTO(Property property);

    Property propertyDTOToProperty(PropertyDTO propertyDTO);

    List<PropertyDTO> map(List<Property> property);//entity den dto ya donder

}
