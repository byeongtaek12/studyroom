package com.example.studyroom.dto.req;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class StudyRoomRequestDtoUser {
	private Long userId;
	private Long roomId;
	private LocalDateTime startAt;
	private LocalDateTime endAt;
}
