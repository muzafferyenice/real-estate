package com.realestate.repository;

import com.realestate.domain.Property;
import com.realestate.domain.TourRequest;
import com.realestate.domain.User;
import com.realestate.domain.enums.TourRequestStatus;
import com.realestate.dto.TourRequestDTO;
import com.realestate.dto.request.TourRequestRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TourRequestRepository extends JpaRepository <TourRequest, Long> {


    @Modifying
    @Query("UPDATE TourRequest tr SET tr.adult=:adult, tr.child=:child  WHERE tr.id=:id")

    void update(@Param("id") Long id, @Param("adult") int adult , @Param("child") int child);


    @Query("SELECT r FROM TourRequest r " + "JOIN FETCH Property pr  on r.propertyId=pr "+" JOIN  fetch  Agent a  on pr.agentId=a WHERE "
            + "pr.id=:propertyId and (r.status not in :status) and :tourRequestFirstTime BETWEEN r.tourRequestFirstTime and r.tourRequestLastTime "
            + "or "
            + "pr.id=:propertyId and (r.status not in :status) and :tourRequestLastTime BETWEEN r.tourRequestFirstTime and r.tourRequestLastTime "
            + "or "
            + "pr.id=:propertyId and (r.status not in :status) and (r.tourRequestFirstTime BETWEEN :tourRequestFirstTime and :tourRequestLastTime)")
    List<TourRequest> checkPropertyStatus(@Param("propertyId") Long propertyId, @Param("tourRequestFirstTime") LocalDateTime tourRequestFirstTime,
                                          @Param("tourRequestLastTime") LocalDateTime tourRequestLastTime, @Param("status") TourRequestStatus[] status);

    @Query("select t from TourRequest t where t.status = ?1")
    List<TourRequest> findTourRequestByStatus(String status);

    List<TourRequestDTO> findAllBy();

}