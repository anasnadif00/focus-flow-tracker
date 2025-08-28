package com.focusflow;

import org.springframework.boot.SpringApplication;

import com.focusflow.boot.FocusflowApplication;

public class TestFocusflowApplication {

	public static void main(String[] args) {
		SpringApplication.from(FocusflowApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
