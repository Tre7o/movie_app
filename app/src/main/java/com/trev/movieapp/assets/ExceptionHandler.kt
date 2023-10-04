package com.trev.movieapp.assets

import okio.IOException

class APIException(message: String): IOException(message){}
class NoInternetException(message: String): IOException(message){}