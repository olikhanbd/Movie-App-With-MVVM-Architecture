package com.beeitstudio.movieapp.models
import com.google.gson.annotations.SerializedName

data class CreditResponse (
	@SerializedName("id") val id : Int,
	@SerializedName("cast") val cast : List<Cast>,
	@SerializedName("crew") val crew : List<Crew>
)