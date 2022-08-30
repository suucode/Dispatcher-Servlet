package site.metacoding.ds;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//메서드, 클래스, 필드(변수)
@Target({ElementType.METHOD}) //자바에서는 {}가 배열 -> 여러개 지정 가능
@Retention(RetentionPolicy.RUNTIME) //동작 타이밍 결정, RUNTIME(런타임시)/SOURCE(컴파일시)
public @interface RequestMapping {
	String value(); //value 메서드는 고정, 이미 정해져있는 키값 -> 어노테이션의 ()안에 들어갈 값을 지정할 수 있게 해줌
}
