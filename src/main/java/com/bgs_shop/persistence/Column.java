package com.bgs_shop.persistence;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
	public enum ColumnType {
		ID, COLUMN, MODEL
	}

	ColumnType value() default ColumnType.COLUMN;
}
