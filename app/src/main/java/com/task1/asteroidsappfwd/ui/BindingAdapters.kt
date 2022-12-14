package com.task1.asteroidsappfwd.ui

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.task1.asteroidsappfwd.R
import com.task1.asteroidsappfwd.ui.models.ImageOfDay


@BindingAdapter("statusIcon")
fun bindAsteroidStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.ic_status_potentially_hazardous)
        imageView.contentDescription = imageView.context.getString(
            R.string.potentially_hazardous_asteroid_image
        )
    } else {
        imageView.setImageResource(R.drawable.ic_status_normal)
        imageView.contentDescription = imageView.context.getString(
            R.string.not_hazardous_asteroid_image
        )
    }
}

@BindingAdapter("asteroidStatusImage")
fun bindDetailsStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.contentDescription = imageView.context.getString(
            R.string.potentially_hazardous_asteroid_image
        )
        imageView.setImageResource(R.drawable.asteroid_hazardous)
    } else {
        imageView.contentDescription = imageView.context.getString(
            R.string.not_hazardous_asteroid_image
        )
        imageView.setImageResource(R.drawable.asteroid_safe)
    }
}

@BindingAdapter("astronomicalUnitText")
fun bindTextViewToAstronomicalUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.astronomical_unit_format), number)
}

@BindingAdapter("kmUnitText")
fun bindTextViewToKmUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_unit_format), number)
}

@BindingAdapter("velocityText")
fun bindTextViewToDisplayVelocity(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_s_unit_format), number)
}

@BindingAdapter("imageUrl")
fun loadImageOfDay(imageView: ImageView, image: ImageOfDay?) {

    Picasso.get()
        .load(image?.url)
        .into(imageView)
}
