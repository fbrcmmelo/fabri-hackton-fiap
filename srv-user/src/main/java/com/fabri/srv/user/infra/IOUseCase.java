package com.fabri.srv.user.infra;

public interface IOUseCase<I, O> {

    /**
     * Executes the use case with the given input and returns the output.
     *
     * @param input the input for the use case
     * @return the output of the use case
     */
    O execute(I input);
}
