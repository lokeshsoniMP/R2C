package com.jsw.r2c.base




object ResponseConst {
    const val INTERNET_ERROR_MSG = "Please check your internet connectivity"
    const val API_ERROR_MSG = "Oops! Error occurred.."
}
object UserType{
    const val PRODUCTION_HEAD :String="ProductionHead"
    const val AS_MANAGER_HEAD :String="Manager"
    const val AS_STOREINCHAREGE :String="StoreIncharge"
    const val AS_PACKAGINGSUPERVISOR :String="PackingSupervisor"
    const val AS_TRASPORTPERSON :String="TransportPerson"
    const val AS_GATEKEEPER :String="GateKeeper"
    const val AS_PRODUCTIONSUPERVISOR :String="PackingSupervisor"

}

const val BASE_URL = "https://jswpoc.mobileprogramming.net/api/"