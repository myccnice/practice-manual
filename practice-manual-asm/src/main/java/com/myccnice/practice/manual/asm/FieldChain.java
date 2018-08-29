package com.myccnice.practice.manual.asm;

import java.util.AbstractList;
import java.util.Iterator;

public class FieldChain extends AbstractList<String>{

    private FieldChain next;
    private String field;

    public FieldChain() {

    }

    public FieldChain(FieldChain next, String field) {
        super();
        this.next = next;
        this.field = field;
    }

    public FieldChain getNext() {
        return next;
    }

    public void setNext(FieldChain next) {
        this.next = next;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Override
    public String get(int index) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterator<String> iterator() {
        return super.iterator();
    }
}
