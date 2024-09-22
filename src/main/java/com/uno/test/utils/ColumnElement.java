package com.uno.test.utils;

/**
 * Значение в столбце с определённым индексом.
 *
 * @param value - значение элемента строки
 * @param index - индекс колонки, в котором находится элемент
 */
public record ColumnElement(String value, int index) {

}
