package my.member.validator;

import my.member.domain.MemberChangePwDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PasswordValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return MemberChangePwDto.class.isAssignableFrom(clazz);
        // 전달되는 클래스가 MemberChangePwDto 와 동일한가 확인!!
    }

    @Override
    public void validate(Object target, Errors errors) {
        MemberChangePwDto changePwDto = (MemberChangePwDto) target;

        if (!changePwDto.getPassword().equals(changePwDto.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "비밀번호와 일치하지 않습니다.");
        }
    }
}
