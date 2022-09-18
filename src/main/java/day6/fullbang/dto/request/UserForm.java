package day6.fullbang.dto.request;

import javax.persistence.Column;

import lombok.Getter;

@Getter
public class UserForm {
    private String id;
    private String name;
    private String password;
}
