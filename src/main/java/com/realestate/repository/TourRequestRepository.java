package com.realestate.repository;

import com.realestate.domain.Property;
import com.realestate.domain.TourRequest;
import com.realestate.domain.enums.TourRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TourRequestRepository extends JpaRepository <TourRequest, Long> {


    @Modifying
    @Query("UPDATE TourRequest tr SET tr.adult=:adult, tr.child=:child, tr.status=:status WHERE tr.id=:id")

    void update(@Param("id") Long id, @Param("adult") int adult , @Param("child") int child);


    @Query("SELECT r FROM TourRequest r " + "JOIN FETCH Property cd on r.propertyId=cd WHERE "
            + "cd.id=:propertyId and (r.status not in :status) and :tourRequestFirstTime BETWEEN r.tourRequestFirstTime and r.tourRequestLastTime "
            + "or "
            + "cd.id=:propertyId and (r.status not in :status) and :tourRequestLastTime BETWEEN r.tourRequestFirstTime and r.tourRequestLastTime "
            + "or "
            + "cd.id=:propertyId and (r.status not in :status) and (r.tourRequestFirstTime BETWEEN :tourRequestFirstTime and :tourRequestLastTime)")
    List<TourRequest> checkPropertyStatus(@Param("propertyId") Property propertyId, @Param("tourRequestFirstTime") LocalDateTime tourRequestFirstTime,
                                          @Param("tourRequestLastTime") LocalDateTime tourRequestLastTime, @Param("status") TourRequestStatus[] status);

    @Query("select t from TourRequest t where t.status = ?1")
    List<TourRequest> findTourRequestByStatus(String status);
}