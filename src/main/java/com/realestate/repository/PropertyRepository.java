package com.realestate.repository;

import com.realestate.domain.Property;
import com.realestate.domain.enums.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {


//    @Query("select p from Property p where p.title=?1 or p.type=?2 or p.price=?3 or p.bedrooms=?4 or p.bathrooms=?5 " +
//            "or p.country=?6 or p.city=?7 or p.district=?8")
    List<Property> findByTitleOrTypeOrPriceOrBedroomsOrBathroomsOrCountryOrCityOrDistrict(
            String title, Type type, double price, int bedrooms, int bathrooms, String country,
            String city, String district
    );

}
