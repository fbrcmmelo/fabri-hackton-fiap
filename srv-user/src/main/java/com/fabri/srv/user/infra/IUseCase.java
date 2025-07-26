package com.fabri.srv.user.infra;

public interface IUseCase<I> {

    /**
     * Executes the use case with the given input and returns the output.
     *
     * @param input the input for the use case
     */
    void execute(I input);
}
