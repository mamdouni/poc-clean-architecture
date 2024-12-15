package com.architecture.java.business.domain;

import java.util.function.UnaryOperator;

public interface TransformerBusinessRule<T> extends UnaryOperator<T> {
}
