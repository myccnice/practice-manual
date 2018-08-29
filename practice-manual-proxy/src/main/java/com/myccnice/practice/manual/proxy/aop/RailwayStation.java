package com.myccnice.practice.manual.proxy.aop;

/**
 * RailwayStation实现 TicketService
 * Created by louis on 2016/4/14.
 */
public class RailwayStation implements TicketService {

    public void sellTicket() {
        System.out.println("售票............");
    }

    public void inquire() {
        System.out.println("问询.............");
        throw new RuntimeException("问询抛出异常");
    }

    public void withdraw() {
        System.out.println("退票.............");
    }
}
