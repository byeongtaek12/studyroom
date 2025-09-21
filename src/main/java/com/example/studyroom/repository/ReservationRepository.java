package com.example.studyroom.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.studyroom.entity.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	@Query("select r "
		+ "from Reservation r "
		+ "where r.startAt >= :start and r.endAt < :end")
	List<Reservation> findByDate(LocalDateTime start, LocalDateTime end);

	@Query("select "
		+ "case when count(r) > 0 then true "
		+ "else false end "
		+ "from Reservation r "
		+ "where r.roomId = :roomId and r.startAt < :endAt and r.endAt > :startAt")
	boolean existsByRoomIdAndStartAtAndEndAt(Long roomId, LocalDateTime startAt, LocalDateTime endAt);

	Reservation findByRoomId(Long id);

}
