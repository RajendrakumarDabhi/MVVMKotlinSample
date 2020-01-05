package com.example.ravi.mvvmsample.api.Response

data class BaseResponse(
    var status: ResponseStatus,
    var data: Any
) {
    companion object {

        fun getLoadingReponse(): BaseResponse {
            return BaseResponse(ResponseStatus.STATUS_LOADING, true)

        }
    }
}



enum class ResponseStatus(name: String) {
    STATUS_LOADING("loading"),
    STATUS_SUCCESS("success"),
    STATUS_ERROR("error")

}