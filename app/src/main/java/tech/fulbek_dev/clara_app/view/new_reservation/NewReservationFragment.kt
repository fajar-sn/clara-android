package tech.fulbek_dev.clara_app.view.new_reservation

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import tech.fulbek_dev.clara_app.data.models.*
import tech.fulbek_dev.clara_app.data.remote.Resource
import tech.fulbek_dev.clara_app.data.repositories.AssetsRepository
import tech.fulbek_dev.clara_app.data.repositories.ReservationsRepository
import tech.fulbek_dev.clara_app.data.repositories.UserRepository
import tech.fulbek_dev.clara_app.databinding.NewReservationLayoutBinding
import tech.fulbek_dev.clara_app.view.base.BaseFragment
import tech.fulbek_dev.clara_app.view.base.ViewModelFactory
import tech.fulbek_dev.clara_app.view.main.AssetsViewModel
import tech.fulbek_dev.clara_app.view.main.MainActivity
import tech.fulbek_dev.clara_app.view.main.ReservationsViewModel
import tech.fulbek_dev.clara_app.view.main.UserViewModel
import tech.fulbek_dev.clara_app.view.utils.startNewActivity
import java.util.*
import kotlin.collections.ArrayList

class NewReservationFragment : BaseFragment<ReservationsViewModel, NewReservationLayoutBinding, ReservationsRepository>() {

    private lateinit var assetsViewModel: AssetsViewModel
    private lateinit var userViewModel: UserViewModel
    private lateinit var assets: List<Asset>
    private lateinit var asset: Asset
    private lateinit var user: User

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val assetsFactory = ViewModelFactory(getAssetsFragmentRepository())
        val userFactory = ViewModelFactory(getUserFragmentRepository())
        assetsViewModel = ViewModelProvider(this, assetsFactory).get(AssetsViewModel::class.java)
        userViewModel = ViewModelProvider(this, userFactory).get(UserViewModel::class.java)

        assetsViewModel.getAssets()
        userViewModel.getUser()

        assetsViewModel.allAssets.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Success -> {
                    assets = it.value
                    initializeSpinner()
                }
                is Resource.Failure -> {
                    Toast.makeText(context, "Can't load list of assets. Error code: ${it.errorCode}, Network Error: ${it.isNetworkError}, Error body: ${it.errorBody}", Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
                }
            }
        })

        userViewModel.user.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Success -> {
                    user = it.value.user
                }
            }
        })

        viewModel.reservationResponse.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Success -> {
                    Toast.makeText(context, "New reservation created successfully", Toast.LENGTH_SHORT).show()
                    requireActivity().startNewActivity(MainActivity::class.java)
                }
            }
        })

        binding.apply {
            beginDateEditText.keyListener = null
            returnDateEditText.keyListener = null

            val calendar = Calendar.getInstance()
            val thisYear = calendar.get(Calendar.YEAR)
            val thisMonth = calendar.get(Calendar.MONTH)
            val today = calendar.get(Calendar.DAY_OF_MONTH)

            beginDateEditText.setOnClickListener {

                val datePickerDialog = context?.let {
                    DatePickerDialog(
                            it,
                            { view, year, monthOfYear, dayOfMonth -> beginDateEditText.setText(dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year) },
                            thisYear,
                            thisMonth,
                            today
                    )
                }

                datePickerDialog?.show()
            }

            returnDateEditText.setOnClickListener {
                val datePickerDialog = context?.let {
                    DatePickerDialog(
                            it,
                            { view, year, monthOfYear, dayOfMonth -> returnDateEditText.setText(dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year) },
                            thisYear,
                            thisMonth,
                            today
                    )
                }

                datePickerDialog?.show()
            }

            reserveButton.setOnClickListener {
                createNewReservation()
            }
        }

    }

    override fun getViewModel() = ReservationsViewModel::class.java

    override fun getFragmentBinding(
            inflater: LayoutInflater,
            container: ViewGroup?
    ) = NewReservationLayoutBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): ReservationsRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = retrofitClient.buildApi(token)
        return ReservationsRepository(api)
    }

    private fun getAssetsFragmentRepository(): AssetsRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = retrofitClient.buildApi(token)
        return AssetsRepository(api)
    }

    private fun getUserFragmentRepository(): UserRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = retrofitClient.buildApi(token)
        return UserRepository(api)
    }

    private fun initializeSpinner() {
        val nameOfAssets = ArrayList<String>()

        assets.forEach { nameOfAssets.add(it.name) }

        val adapter = context?.let { ArrayAdapter(it, android.R.layout.simple_spinner_dropdown_item, nameOfAssets) }

        binding.assetSpinner.adapter = adapter

        binding.assetSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                asset = assets[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }

    }

    private fun createNewReservation() {
        val beginDate = binding.beginDateEditText.text.toString().trim()
        val returnDate = binding.returnDateEditText.text.toString().trim()
        val description = binding.descriptionEditText.text.toString().trim()
        val quantity = binding.quantityEditText.text.toString().toInt()
        val assetId = asset.id
        val reservation = ReservationRequest(description, beginDate, returnDate, assetId, quantity)
        viewModel.createNewReservation(reservation)
    }

}