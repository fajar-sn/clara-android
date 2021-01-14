package tech.fulbek_dev.clara_app.view.new_reservation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tech.fulbek_dev.clara_app.databinding.NewReservationLayoutBinding

class NewReservationActivity : AppCompatActivity() {

    private lateinit var binding: NewReservationLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = NewReservationLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}