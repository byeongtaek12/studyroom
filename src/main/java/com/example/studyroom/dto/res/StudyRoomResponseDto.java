package com.example.studyroom.dto.res;

import com.example.studyroom.entity.Reservation;
import com.example.studyroom.entity.Room;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StudyRoomResponseDto {
	private Long id;

	public static StudyRoomResponseDto from(Room room) {
		return StudyRoomResponseDto.builder()
			.id(room.getId())
			.build();
	}

	public static StudyRoomResponseDto from(Reservation reservation) {
		return StudyRoomResponseDto.builder()
			.id(reservation.getId())
			.build();
	}
}
