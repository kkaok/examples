package eblo.example.util;

import java.util.Collection;
import java.util.Map;

import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import eblo.common.exception.AbstractEbloBaseException;
import eblo.common.exception.EbloInvalidRequestException;
import eblo.common.exception.EbloSystemException;

public class EbloAssert extends Assert {

    private static final String NUMBER_ALPHABET_PATTERN = "^[a-zA-Z\\d]+$";
    private static final String NUMERIC_PATTERN = "^[\\+\\-]{0,1}[\\d]+$";
    private static final String FLOAT_PATTERN = "^[\\+\\-]{0,1}[\\d]+[\\.][0-9]+$";

    /**
     * Assert a boolean expression, TRUE가 아닌 경우 사용자가 정의한 예외를 던진다.
     * 사용자 정의 예외는 AbstractEbloBaseException.class를 상속 받아 구현해야 한다. 
     * <pre class="code">Assert.state(id == null, "The id property must not already be initialized", EbloInvalidRequestException.class);</pre>
     * @param expression a boolean expression
     * @param message the exception message to use if the assertion fails
     * @param exceptionClass
     * @throws IllegalStateException if {@code expression} is {@code false}
     */
    public static void state(boolean expression, String message, final Class<? extends AbstractEbloBaseException> exceptionClass) {
        if (!expression) {
            throwException(message, exceptionClass);
        }
    }

    /**
     * Assert a boolean expression, TRUE가 아닌 경우 사용자가 정의한 예외를 던진다.
     * 사용자 정의 예외는 AbstractEbloBaseException.class를 상속 받아 구현해야 한다. 
     * <pre class="code">Assert.isTrue(user != null, "사용자 정보가 존재하지 않습니다.", EbloNotFoundException.class);</pre>
     * @param expression a boolean expression
     * @param message the exception message to use if the assertion fails
     * @param exceptionClass
     * @throws IllegalStateException if {@code expression} is {@code false}
     */
    public static void isTrue(boolean expression, String message, final Class<? extends AbstractEbloBaseException> exceptionClass) {
        if (!expression) {
            throwException(message, exceptionClass);
        }
    }

    /**
     * Assert that an object is {@code null}. 객체가 null이 아닌 경우 사용자가 정의한 예외를 던진다.
     * 사용자 정의 예외는 AbstractEbloBaseException.class를 상속 받아 구현해야 한다. 
     * <pre class="code">Assert.isNull(user, "기존 사용자 정보가 존재합니다.", EbloInvalidRequestException.class);</pre>
     * @param expression a boolean expression
     * @param message the exception message to use if the assertion fails
     * @param exceptionClass
     * @throws IllegalStateException if {@code expression} is {@code false}
     */
    public static void isNull(@Nullable Object object, String message, final Class<? extends AbstractEbloBaseException> exceptionClass) {
        if (object != null) {
            throwException(message, exceptionClass);
        }
    }

    /**
     * null인 경우 사용자 정의 예외 발생
     * 사용자 정의 예외는 AbstractEbloBaseException.class를 상속 받아 구현해야 한다. 
     * <pre class="code">Assert.notNull(user, "사용자 정보가 존재하지 않습니다.", EbloNotFoundException.class);</pre>
     * @param object
     * @param message
     * @param exceptionClass
     */
    public static void notNull(@Nullable Object object, String message, final Class<? extends AbstractEbloBaseException> exceptionClass) {
        if (object == null) {
            throwException(message, exceptionClass);
        }
    }

    /**
     * 전달받은 값이 null이 거나 빈값인 경우 사용자 정의 예외 발생
     * 사용자 정의 예외는 AbstractEbloBaseException.class를 상속 받아 구현해야 한다. 
     * <pre class="code">Assert.hasLength(value, "전달 받은 값이 빈값입니다.", EbloInvalidRequestException.class);</pre>
     * @param object
     * @param message
     * @param exceptionClass
     */
    public static void hasLength(@Nullable String text, String message, final Class<? extends AbstractEbloBaseException> exceptionClass) {
        if (!StringUtils.hasLength(text)) {
            throwException(message, exceptionClass);
        }
    }

    /**
     * 전달받은 값이 null이 거나 빈값인 경우 사용자 정의 예외 발생
     * 사용자 정의 예외는 AbstractEbloBaseException.class를 상속 받아 구현해야 한다. 
     * <pre class="code">Assert.hasText(value, "전달 받은 값이 빈값입니다.", EbloInvalidRequestException.class);</pre>
     * @param object
     * @param message
     * @param exceptionClass
     */
    public static void hasText(@Nullable String text, String message, final Class<? extends AbstractEbloBaseException> exceptionClass) {
        if (!StringUtils.hasText(text)) {
            throwException(message, exceptionClass);
        }
    }

    /**
     * 전달받은 값이 null이 거나 빈값인 경우 사용자 정의 예외 발생
     * 사용자 정의 예외는 AbstractEbloBaseException.class를 상속 받아 구현해야 한다. 
     * <pre class="code">Assert.notEmpty(array, "전달 받은 값이 빈값입니다.", EbloInvalidRequestException.class);</pre>
     * @param object
     * @param message
     * @param exceptionClass
     */
    public static void notEmpty(@Nullable Object[] array, String message, final Class<? extends AbstractEbloBaseException> exceptionClass) {
        if (ObjectUtils.isEmpty(array)) {
            throwException(message, exceptionClass);
        }
    }

    /**
     * 전달받은 값이 null이 거나 빈값인 경우 사용자 정의 예외 발생
     * 사용자 정의 예외는 AbstractEbloBaseException.class를 상속 받아 구현해야 한다. 
     * <pre class="code">Assert.notEmpty(collection, "전달 받은 값이 빈값입니다.", EbloInvalidRequestException.class);</pre>
     * @param object
     * @param message
     * @param exceptionClass
     */
    public static void notEmpty(@Nullable Collection<?> collection, String message, final Class<? extends AbstractEbloBaseException> exceptionClass) {
        if (CollectionUtils.isEmpty(collection)) {
            throwException(message, exceptionClass);
        }
    }

    /**
     * 전달받은 값이 null이 거나 빈값인 경우 사용자 정의 예외 발생
     * 사용자 정의 예외는 AbstractEbloBaseException.class를 상속 받아 구현해야 한다. 
     * <pre class="code">Assert.notEmpty(map, "전달 받은 값이 빈값입니다.", EbloInvalidRequestException.class);</pre>
     * @param object
     * @param message
     * @param exceptionClass
     */
    public static void notEmpty(@Nullable Map<?, ?> map, String message, final Class<? extends AbstractEbloBaseException> exceptionClass) {
        if (CollectionUtils.isEmpty(map)) {
            throwException(message, exceptionClass);
        }
    }

    private static void throwException(String message, final Class<? extends AbstractEbloBaseException> exceptionClass) {
        try {
            throw exceptionClass.getDeclaredConstructor( String.class ).newInstance( message );
        } catch (Exception e) {
            e.printStackTrace();
            throw new EbloSystemException("예외 처리 중 오류가 발생했습니다. "+e.getMessage());
        }
    }
    
    /**
     * 값이 영문 알파벳, 숫자가 아닌 경우 예외 발생 
     * @param object
     * @param message
     */
    public static void isAlphaNumber(String object, String message) {
        isMatched(object, NUMBER_ALPHABET_PATTERN, message, EbloInvalidRequestException.class);
    }

    /**
     * 값이 영문 알파벳, 숫자가 아닌 경우 사용자 정의 예외 발생 
     * @param object
     * @param message
     * @param exceptionClass
     */
    public static void isAlphaNumber(String object, String message, final Class<? extends AbstractEbloBaseException> exceptionClass) {
        isMatched(object, NUMBER_ALPHABET_PATTERN, message, exceptionClass);
    }
    
    /**
     * 값이 숫자가 아닌 경우 예외 발생 
     * @param object
     * @param message
     */
    public static void isNumeric(String object, String message) {
        isMatched(object, NUMERIC_PATTERN, message, EbloInvalidRequestException.class);
    }
    
    /**
     * 값이 숫자가 아닌 경우 사용자 정의 예외 발생 
     * @param object
     * @param message
     * @param exceptionClass
     */
    public static void isNumeric(String object, String message, final Class<? extends AbstractEbloBaseException> exceptionClass) {
        isMatched(object, NUMERIC_PATTERN, message, exceptionClass);
    }
    
    /**
     * 값이 float, double이 아닌 경우 예외 발생 
     * @param object
     * @param message
     */
    public static void isFloat(String object, String message) {
        isMatched(object, FLOAT_PATTERN, message, EbloInvalidRequestException.class);
    }
    
    /**
     * 값이 float, double이 아닌 경우 사용자 정의 예외 발생 
     * @param object
     * @param message
     * @param exceptionClass
     */
    public static void isFloat(String object, String message, final Class<? extends AbstractEbloBaseException> exceptionClass) {
        isMatched(object, FLOAT_PATTERN, message, exceptionClass);
    }
    
    /**
     * 패턴 매치, 해당 패턴과 일치 하지 않는 경우 사용자 정의 예외 발생 
     * @param object
     * @param pattern
     * @param message
     * @param exceptionClass
     */
    public static void isMatched(String object, String pattern, String message, final Class<? extends AbstractEbloBaseException> exceptionClass) {
        if(object == null || "".equalsIgnoreCase(object)) return;
        
        if(!object.matches(pattern)) {
            throwException(message, exceptionClass);
        }
    }

    /**
     * 패턴 매치, 해당 패턴과 일치 하지 않는 경우 예외 발생 
     * @param object
     * @param pattern
     * @param message
     */
    public static void isMatched(String object, String pattern, String message) {
        if(object == null || "".equalsIgnoreCase(object)) return;
        
        if(!object.matches(pattern)) {
            throwException(message, EbloInvalidRequestException.class);
        }
    }
    
}
