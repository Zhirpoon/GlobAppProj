package se.kth.id1212.globalapps.common.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import se.kth.id1212.globalapps.common.validation.DateNotSet.DateNotSetValidator;
import se.kth.id1212.globalapps.view.DateUtil;

/**
 *  Validates that a <code>DateUtil</code> instance
 * has a <code>Java.util.Date</code> initialized
 * 
 */
@Documented
@Constraint(validatedBy = DateNotSetValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateNotSet {

    String message() default "{se.kth.id1212.globalapps.common.validation.DateNotSet}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
    public class DateNotSetValidator implements ConstraintValidator<DateNotSet, DateUtil> {
    
    @Override
    public void initialize(DateNotSet constraintAnnotation) {
        
    }
    
    @Override
    public boolean isValid(DateUtil value, ConstraintValidatorContext context) {
        return value.getDate() != null;
    }
}
}
