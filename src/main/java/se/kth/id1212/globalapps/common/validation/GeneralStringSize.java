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
import se.kth.id1212.globalapps.common.validation.GeneralStringSize.GeneralStringSizeValidator;

/**
 * Interface for String size validation with inner class that provides an 
 * implementation. 
 */

@Documented
@Constraint(validatedBy = GeneralStringSizeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface GeneralStringSize {

    String message() default "String length must be between 1 and 255";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * Validates that a string is not empty and not exceeding 255 characters
     * Null is valid
     */
    class GeneralStringSizeValidator implements ConstraintValidator<GeneralStringSize, String> {

        /**
         *
         * @param constraintAnnotation
         */
        @Override
        public void initialize(GeneralStringSize constraintAnnotation) {

        }
        
        /**
         * Controls string size
         * 
         * @param value The string to validate
         * @param context Validator Context
         * @return if the string is valid
         */
        
        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            if (isNull(value)) {
                return true;
            }
            return notEmptyString(value) && notTooLarge(value);
        }

        private boolean notEmptyString(String value) {
            return value.length() >= 1;
        }

        private boolean notTooLarge(String value) {
            return value.length() <= 10;
        }

        private boolean isNull(String value) {
            return value == null;
        }

    }
}
