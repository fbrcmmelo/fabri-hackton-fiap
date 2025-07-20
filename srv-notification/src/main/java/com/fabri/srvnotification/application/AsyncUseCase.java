package com.fabri.srvnotification.application;

public interface AsyncUseCase<I> {

    void execute(I input);

}
