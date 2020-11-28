package io.company.controller;

import io.company.dto.UserDTO;
import io.company.entity.User;
import io.company.service.UserService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

@ExecuteOn(TaskExecutors.IO)
@Controller("/users")
@Secured(SecurityRule.IS_ANONYMOUS)
public class UserController {

    @Inject
    private UserService userService;

    @Get
    public List<User> all() {
        return userService.all();
    }

    @Get("/{id}")
    public HttpResponse<User> get(@PathVariable Long id) {
        User user = userService.get(id);
        return HttpResponse.status(HttpStatus.OK).body(user);
    }

    @Post
    public HttpResponse<User> add(@Body @Valid UserDTO userDTO) {
        User user = userService.add(userDTO);
        return HttpResponse.status(HttpStatus.OK).body(user);
    }

    @Put("/{id}")
    public HttpResponse<User> update(@PathVariable Long id, @Body @Valid UserDTO userDTO) {
        User user = userService.update(id, userDTO);
        return HttpResponse.status(HttpStatus.OK).body(user);
    }

    @Delete("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
