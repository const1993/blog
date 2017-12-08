package by.owm.domain.mapper;

import by.owm.AbstractTest;
import by.owm.domain.model.User;
import by.owm.rest.dto.UserDto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;

public class MapperTest extends AbstractTest {

    @Autowired
    private Mapper mapper;

    @Test
    public void test_map_userDto2User_expect_success() {

        final UserDto dto = new UserDto(
                "name",
                "surname",
                "mail",
                "token",
                emptyList());

        final User user = mapper.map(dto, User.class);

        assertEquals(dto.getName(), user.getName());
        assertEquals(dto.getSurname(), user.getSurname());
        assertEquals(dto.getEmail(), user.getEmail());
        assertEquals(dto.getToken(), user.getToken());
    }


    @Test
    public void test_map_user2UserDto_expect_success() {

        final User user = new User(
                "name",
                "surname",
                "mail",
                "token",
                emptyList());

        final UserDto dto = mapper.map(user, UserDto.class);

        assertEquals(user.getName(), dto.getName());
        assertEquals(user.getSurname(), dto.getSurname());
        assertEquals(user.getEmail(), dto.getEmail());
        assertEquals(user.getToken(), dto.getToken());
    }
}
