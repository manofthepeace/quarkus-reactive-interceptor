package org.acme.interceptor;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.interceptor.InterceptorBinding;

@Inherited
@Documented
@InterceptorBinding
@Retention(RUNTIME)
@Target({ METHOD, TYPE })
public @interface RestPrerequisite {}
