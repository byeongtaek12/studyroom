package com.example.studyroom.service;

import com.example.studyroom.dto.req.StudyRoomRequestDtoAdmin;
import com.example.studyroom.dto.res.StudyRoomResponseDto;

import jakarta.servlet.http.HttpServletRequest;

public interface StudyRoomService {
	StudyRoomResponseDto registrationRoom(StudyRoomRequestDtoAdmin req, HttpServletRequest request);
}
