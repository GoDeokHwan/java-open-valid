package io.com.open.javaopenvalid.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.com.open.javaopenvalid.support.Password;
import io.com.open.javaopenvalid.support.PhoneNumber;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

public class ValidRequest {
    /**
     * 문자열 검증
     * */
    @NotBlank(message = "name cannot be empty or null.")
    private String name;

    /**
     * 숫자 검증
     * */
    @NotNull(message = "age cannot be null.")
    //음수 검증
//    @NegativeOrZero
//    @Negative
    // 양수 검증
//    @PositiveOrZero(message = "age positive number including zero")
    @Positive(message = "age positive number not including zero")
    private Integer age;

    /**
     * 이메일 검증
     * */
    @NotBlank(message = "email cannot be empty or null.")
    @Email(message = "The format of the email message is strange.")
    private String email;

    /**
     * Boolean 값 검증
     * */
    // @AssertFalse(message = "Is not False")
    @AssertTrue(message = "Is not True")
    private boolean isTrue;

    /**
     * 오늘 날짜 검증
     * */
    // 미래인지
//    @Negative
//    @NegativeOrZero
    // 과거인지
//    @PositiveOrZero
    @Past(message = "오늘 날짜보다 과거가 아닙니다.")
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    private LocalDateTime date;

    /***
     * 커스텀
     * */

    /**
     * 전화번호
     * */
    @NotBlank(message = "phoneNumber cannot be empty or null.")
    @PhoneNumber(message = "The format of the phone number message is strange.")
    private String phoneNumber;

    @NotBlank(message = "password cannot be empty or null.")
    @Size(
        min = 8, max = 16,
        message = "Password must be at least 8 digits"
    )
    @Password(
            message = "The format of the password message is strange.",
            capitalLetter = false
    )
    private String password;

    public static ValidRequest of(String name, Integer age, String email, boolean isTrue, LocalDateTime date, String phoneNumber, String password) {
        ValidRequest instance = new ValidRequest();
        instance.name = name;
        instance.age = age;
        instance.email = email;
        instance.isTrue = isTrue;
        instance.date = date;
        instance.phoneNumber = phoneNumber;
        instance.password = password;
        return instance;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setTrue(boolean aTrue) {
        isTrue = aTrue;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
