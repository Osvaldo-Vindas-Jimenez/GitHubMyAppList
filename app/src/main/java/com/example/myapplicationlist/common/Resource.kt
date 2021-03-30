package com.example.myapplicationlist.common


class Resource<out T>(var status: Status, val data: T?, val errorMessage: String) {

    companion object {

       //Cargando
        fun <T> loading(): Resource<T> {
            return Resource(Status.CARGANDO, null, "")
        }

        //Creado
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.CREADO, data, "")
        }

        // Mensaje de error
        fun <T> error(message: String): Resource<T> {
            return Resource(Status.ERROR, null, message)
        }
    }
}