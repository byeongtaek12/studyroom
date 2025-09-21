package com.example.studyroom.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.studyroom.dto.req.StudyRoomRequestDtoAdmin;
import com.example.studyroom.dto.req.StudyRoomRequestDtoUser;
import com.example.studyroom.dto.res.StudyRoomResponseDto;
import com.example.studyroom.dto.res.StudyRoomResponseDtoGet;
import com.example.studyroom.service.StudyRoomService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class StudyRoomController {
	private final StudyRoomService studyRoomService;

	@PostMapping("/rooms")
	public ResponseEntity<StudyRoomResponseDto> registrationRoom(@RequestBody StudyRoomRequestDtoAdmin req,
		HttpServletRequest request) {
		return ResponseEntity.ok(studyRoomService.registrationRoom(req,request));
	}

	@GetMapping("/rooms")
	public ResponseEntity<List<StudyRoomResponseDtoGet>> checkRoom(@RequestParam("date") LocalDate date) {
		return ResponseEntity.ok(studyRoomService.checkRoom(date));
	}

	@PostMapping("/reservations")
	public ResponseEntity<StudyRoomResponseDto> reservationsRoom(@RequestBody StudyRoomRequestDtoUser req,
		HttpServletRequest request) {
		return ResponseEntity.ok(studyRoomService.reservationsRoom(req,request));
	}


}
