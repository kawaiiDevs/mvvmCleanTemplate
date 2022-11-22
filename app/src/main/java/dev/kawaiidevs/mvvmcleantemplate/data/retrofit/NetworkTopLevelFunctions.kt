package dev.kawaiidevs.mvvmcleantemplate.data.retrofit

import dev.kawaiidevs.mvvmcleantemplate.domain.ResultItunes
import dev.kawaiidevs.mvvmcleantemplate.domain.constants.INTERNET_CONNECTION_ERROR_MESSAGE
import retrofit2.Response

internal inline fun <T : Any> executeRetrofitRequest(request: () -> Response<T>): ResultItunes<T> {
    return try {
        val response = request.invoke()
        return if (response.isSuccessful && response.body() != null) {
            val body = response.body()
            if (body != null) {
                ResultItunes.Success(body)
            } else {
                ResultItunes.Error("Empty body found in this request")
            }
        } else {
            val errorBody = response.errorBody()
            val errorText = if (errorBody == null) "Error body null" else errorBody.string()
            ResultItunes.Error(errorText, response.code())
        }
    }
    catch (ex: Exception) {
        ResultItunes.Error(ex.message)
    }
}

fun <Api : Any, Data : Any> handleResultRetrofit(
    result: ResultItunes<Api>,
    onSuccess: (Api) -> Data
): ResultItunes<Data> {
    return when (result) {
        is ResultItunes.Error -> {
            if(result.exception?.contains(INTERNET_CONNECTION_ERROR_MESSAGE) == true) {
                ResultItunes.InternetConnectionError
            } else {
                result
            }
        }
        is ResultItunes.Success -> ResultItunes.Success(onSuccess.invoke(result.data))
        else -> ResultItunes.Loading
    }
}

internal inline fun <Api : Any, Domain : Any> mapDomainDataState(
    apiDataState: ResultItunes<Api>,
    block: Api.() -> Domain
): ResultItunes<Domain> {
    return when (apiDataState) {
        is ResultItunes.Success -> {
            return ResultItunes.Success(block.invoke(apiDataState.data))
        }
        is ResultItunes.Error -> ResultItunes.Error(apiDataState.exception)
        is ResultItunes.InternetConnectionError -> ResultItunes.InternetConnectionError
        else -> ResultItunes.Loading
    }
}

internal inline fun <Helper : Any, Api : Any, Domain : Any> mapDomainDataState(
    apiDataState: ResultItunes<Api>,
    helper: Helper,
    block: (Helper, Api) -> Domain
): ResultItunes<Domain> {
    return when (apiDataState) {
        is ResultItunes.Success -> {
            return ResultItunes.Success(block.invoke(helper, apiDataState.data))
        }
        is ResultItunes.Error -> ResultItunes.Error(apiDataState.exception)
        is ResultItunes.InternetConnectionError -> ResultItunes.InternetConnectionError
        else -> ResultItunes.Loading
    }
}