package com.booking.users.view;

import com.booking.App;
import com.booking.users.User;
import com.booking.users.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void before() {
        userRepository.deleteAll();
    }

    @AfterEach
    public void after() {
        userRepository.deleteAll();
    }


    @Test
    public void shouldLoginSuccessfully() throws Exception {
        userRepository.save(new User("test-user", "P@rul1abc"));
        mockMvc.perform(get("/login")
                .with(httpBasic("test-user", "P@rul1abc")))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldThrowErrorMessageForInvalidCredentials() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void givenLoggedInUser_whenChangingPassword_thenCorrect() throws Exception {
        userRepository.save(new User("test-user", "P@rul1abc"));
        mockMvc.perform(get("/login")
                .with(httpBasic("test-user", "P@rul1abc")));

        mockMvc.perform(post("/changePassword").param("password", "P@rul1xyz")
                .param("oldpassword", "P@rul1abc").with(user("test-user")))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldThrowErrorMessageWhenInvalidPasswordIsGivenForChangePassword() throws Exception {
        userRepository.save(new User("test-user", "P@rul1abc"));
        mockMvc.perform(get("/login")
                .with(httpBasic("test-user", "P@rul1abc")));

        mockMvc.perform(post("/changePassword").param("password", "foobar")
                        .param("oldpassword", "P@rul1abc").with(user("test-user")))
                .andExpect(status().is5xxServerError());
    }

}
