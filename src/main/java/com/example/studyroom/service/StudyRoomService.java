package com.example.studyroom.service;

import java.time.LocalDate;
import java.util.List;

import com.example.studyroom.dto.req.StudyRoomRequestDtoAdmin;
import com.example.studyroom.dto.req.StudyRoomRequestDtoUser;
import com.example.studyroom.dto.res.StudyRoomResponseDto;
import com.example.studyroom.dto.res.StudyRoomResponseDtoGet;

import jakarta.servlet.http.HttpServletRequest;

public interface StudyRoomService {
	StudyRoomResponseDto registrationRoom(StudyRoomRequestDtoAdmin req, HttpServletRequest request);
	List<StudyRoomResponseDtoGet> checkRoom(LocalDate date);
	StudyRoomResponseDto reservationRoom(StudyRoomRequestDtoUser req, HttpServletRequest request);
	void cancelReservation(Long id, HttpServletRequest req);
}
