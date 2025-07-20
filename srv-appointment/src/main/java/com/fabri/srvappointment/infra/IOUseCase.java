package com.fabri.srvappointment.infra;

public interface IOUseCase<I,O> {

    O execute(I input);
}
