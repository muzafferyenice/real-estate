package com.realestate.repository;

import com.realestate.domain.TourRequest;
import com.realestate.domain.enums.TourRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TourRequestRepository extends JpaRepository <TourRequest, Long> {


    @Query("SELECT r FROM TourRequest r " + "JOIN FETCH Property cd on r.propertyId=cd.agentId WHERE "
            + "cd.id=:carId and (r.status not in :status) and :tourRequestFirstTime BETWEEN r.tourRequestFirstTime and r.tourRequestLastTime "
            + "or "
            + "cd.id=:carId and (r.status not in :status) and :tourRequestLastTime BETWEEN r.tourRequestFirstTime and r.tourRequestLastTime "
            + "or "
            + "cd.id=:carId and (r.status not in :status) and (r.tourRequestFirstTime BETWEEN :tourRequestFirstTime and :tourRequestLastTime)")
    List<TourRequest> yahya(@Param("propertyId") Long propertyId, @Param("tourRequestFirstTime") LocalDateTime tourRequestFirstTime,
                                     @Param("tourRequestLastTime") LocalDateTime tourRequestLastTime, @Param("status") TourRequestStatus[] status);
}
