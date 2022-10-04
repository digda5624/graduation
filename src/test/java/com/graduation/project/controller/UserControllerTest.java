package com.graduation.project.controller;

import com.google.gson.Gson;
import com.graduation.project.domain.Users;
import com.graduation.project.dto.UserGetResponse;
import com.graduation.project.error.UserErrorResult;
import com.graduation.project.error.UserException;
import com.graduation.project.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController target;
    @Mock
    private UserService userService;

    private MockMvc mockMvc;
    private Gson gson;

    @BeforeEach
    void init() {
        gson = new Gson();
        mockMvc = MockMvcBuilders.standaloneSetup(target)
                .build();
    }

    @Test
    void MockMvc가Null아님() throws Exception {
        Assertions.assertThat(target).isNotNull();
        Assertions.assertThat(mockMvc).isNotNull();
    }

    @Test
    void User상세조회실패_User존재하지않음() throws Exception {
        // given
        String url = "/users/1";
        Mockito.doThrow(new UserException(UserErrorResult.USER_NOT_FOUND))
                .when(userService)
                .getUser(1L);
        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get(url)
        );
        // then
        resultActions.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void User상세조회성공() throws Exception {
        // given
        String url = "/users/1";
        Mockito.doReturn(
                UserGetResponse.builder().build()
        ).when(userService).getUser(1L);
        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get(url)
        );
        // then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }
}
