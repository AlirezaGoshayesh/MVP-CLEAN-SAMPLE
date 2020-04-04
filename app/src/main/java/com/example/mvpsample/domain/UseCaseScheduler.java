package com.example.mvpsample.domain;

public interface UseCaseScheduler {

    void execute(Runnable runnable);


    <V extends UseCase.ResponseValue,E extends UseCase.ResponseError> void notifyResponse(final V response,
                                                          final UseCase.UseCaseCallback<V,E> useCaseCallback);

    <V extends UseCase.ResponseValue,E extends UseCase.ResponseError> void onError(final E error,
            final UseCase.UseCaseCallback<V,E> useCaseCallback);
}