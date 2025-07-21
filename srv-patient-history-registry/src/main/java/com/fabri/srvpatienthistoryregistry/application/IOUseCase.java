package com.fabri.srvpatienthistoryregistry.application;

public interface IOUseCase <I, O> {
    O execute(I input);
}
