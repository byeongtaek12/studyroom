package com.example.studyroom.dto.res;

import java.time.LocalDateTime;

import com.example.studyroom.entity.Reservation;

import lombok.Builder;

@Builder
public class StudyRoomResponseDtoGet {
	private Long id;
	private Long roomId;
	private LocalDateTime startAt;
	private LocalDateTime endAt;

	public static StudyRoomResponseDtoGet from(Reservation reservation) {
		return StudyRoomResponseDtoGet.builder()
			.id(reservation.getId())
			.roomId(reservation.getRoomId())
			.startAt(reservation.getStartAt())
			.endAt(reservation.getEndAt())
			.build();
	}
}
