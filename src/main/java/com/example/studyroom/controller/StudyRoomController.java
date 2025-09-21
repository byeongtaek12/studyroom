package com.example.studyroom.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.studyroom.service.StudyRoomService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class StudyRoomController {
	private final StudyRoomService studyRoomService;




}
