package io.com.open.javaopenvalid.request;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ValidRequestTest {

    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    public static void init() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("name 빈문자열 전송 시 에러 발생")
    void 빈문자열_유효성_실패_테스트() {
        // given
        ValidRequest validRequest = ValidRequest.of("홍길동", 11, "system@gmail.com", true, LocalDateTime.now().minusDays(1L), "01012341234", "asdfASDF1234!@$#");
        validRequest.setName("");

        // when
        Set<ConstraintViolation<ValidRequest>> violations = validator.validate(validRequest);

        // then
        assertThat(violations).isNotEmpty();
        violations.forEach(error -> {
            assertThat(error.getMessage()).isEqualTo("name cannot be empty or null.");
        });
    }

    @Test
    @DisplayName("age null 전송 시 에러 발생")
    void 널_유효성_실패_테스트() {
        // given
        ValidRequest validRequest = ValidRequest.of("홍길동", 11, "system@gmail.com", true, LocalDateTime.now().minusDays(1L), "01012341234", "asdfASDF1234!@$#");
        validRequest.setAge(null);

        // when
        Set<ConstraintViolation<ValidRequest>> violations = validator.validate(validRequest);

        // then
        assertThat(violations).isNotEmpty();
        violations.forEach(error -> {
            assertThat(error.getMessage()).isEqualTo("age cannot be null.");
        });
    }

    @Test
    @DisplayName("age -1 전송 시 에러 발생")
    void 나이_유효성_실패_테스트() {
        // given
        ValidRequest validRequest = ValidRequest.of("홍길동", 11, "system@gmail.com", true, LocalDateTime.now().minusDays(1L), "01012341234", "asdfASDF1234!@$#");
        validRequest.setAge(-1);

        // when
        Set<ConstraintViolation<ValidRequest>> violations = validator.validate(validRequest);

        // then
        assertThat(violations).isNotEmpty();
        violations.forEach(error -> {
            assertThat(error.getMessage()).isEqualTo("age positive number not including zero");
        });
    }

    @Test
    @DisplayName("이메일 빈값 전송 시 에러 발생")
    void 이메일_유효성_실패_테스트() {
        // given
        ValidRequest validRequest = ValidRequest.of("홍길동", 11, "system@gmail.com", true, LocalDateTime.now().minusDays(1L), "01012341234", "asdfASDF1234!@$#");
        validRequest.setEmail(null);

        // when
        Set<ConstraintViolation<ValidRequest>> violations = validator.validate(validRequest);

        // then
        assertThat(violations).isNotEmpty();
        violations.forEach(error -> {
            assertThat(error.getMessage()).isEqualTo("email cannot be empty or null.");
        });
    }

    @Test
    @DisplayName("이메일 전송 시 포맷 에러 발생")
    void 이메일_포맷_유효성_실패_테스트() {
        // given
        ValidRequest validRequest = ValidRequest.of("홍길동", 11, "system@gmail.com", true, LocalDateTime.now().minusDays(1L), "01012341234", "asdfASDF1234!@$#");
        validRequest.setEmail("asfgsdagf");

        // when
        Set<ConstraintViolation<ValidRequest>> violations = validator.validate(validRequest);

        // then
        assertThat(violations).isNotEmpty();
        violations.forEach(error -> {
            assertThat(error.getMessage()).isEqualTo("The format of the email message is strange.");
        });
    }

    @Test
    @DisplayName("Boolean 유효성 에러 발생")
    void Boolean_유효성_실패_테스트() {
        // given
        ValidRequest validRequest = ValidRequest.of("홍길동", 11, "system@gmail.com", true, LocalDateTime.now().minusDays(1L), "01012341234", "asdfASDF1234!@$#");
        validRequest.setTrue(false);

        // when
        Set<ConstraintViolation<ValidRequest>> violations = validator.validate(validRequest);

        // then
        assertThat(violations).isNotEmpty();
        violations.forEach(error -> {
            assertThat(error.getMessage()).isEqualTo("Is not True");
        });
    }

    @Test
    @DisplayName("날짜 유효성 에러 발생")
    void 날짜_유효성_실패_테스트() {
        // given
        ValidRequest validRequest = ValidRequest.of("홍길동", 11, "system@gmail.com", true, LocalDateTime.now().minusDays(1L), "01012341234", "asdfASDF1234!@$#");
        validRequest.setDate(LocalDateTime.now().plusDays(2L));

        // when
        Set<ConstraintViolation<ValidRequest>> violations = validator.validate(validRequest);

        // then
        assertThat(violations).isNotEmpty();
        violations.forEach(error -> {
            assertThat(error.getMessage()).isEqualTo("오늘 날짜보다 과거가 아닙니다.");
        });
    }

    @Test
    @DisplayName("전화번호 유효성 에러 발생")
    void 전화번호_유효성_실패_테스트() {
        // given
        ValidRequest validRequest = ValidRequest.of("홍길동", 11, "system@gmail.com", true, LocalDateTime.now().minusDays(1L), "01012341234", "asdfASDF1234!@$#");
        validRequest.setPhoneNumber("02-1234");

        // when
        Set<ConstraintViolation<ValidRequest>> violations = validator.validate(validRequest);

        // then
        assertThat(violations).isNotEmpty();
        violations.forEach(error -> {
            assertThat(error.getMessage()).isEqualTo("The format of the phone number message is strange.");
        });
    }

    @Test
    @DisplayName("비밀번호 유효성 에러 발생")
    void 비밀번호_유효성_실패_테스트() {
        // given
        ValidRequest validRequest = ValidRequest.of("홍길동", 11, "system@gmail.com", true, LocalDateTime.now().minusDays(1L), "01012341234", "asdfASDF1234!@$#");
        validRequest.setPassword("AABBBbb@#sdfh");

        // when
        Set<ConstraintViolation<ValidRequest>> violations = validator.validate(validRequest);

        // then
        assertThat(violations).isNotEmpty();
        violations.forEach(error -> {
            assertThat(error.getMessage()).isEqualTo("The format of the password message is strange.");
        });
    }
}