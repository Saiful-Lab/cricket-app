package com.saiful.cricketapp.ui.fragment.playerStatistic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.saiful.cricketapp.adapter.CareerBattingAdapter
import com.saiful.cricketapp.databinding.FragmentBattingBinding
import com.saiful.cricketapp.sharedPreferences.UtilsSharedPreferences
import com.saiful.cricketapp.util.Constant
import com.saiful.cricketapp.viewmodels.CricketViewModel

class BattingFragment : Fragment() {
    private var _binding: FragmentBattingBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CricketViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBattingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[CricketViewModel::class.java]
        val utilsSharedPreferences = UtilsSharedPreferences(requireContext())
        val playerId = utilsSharedPreferences.getData(Constant.PLAYER_ID) ?: ""

        viewModel.playerBattingCareer(playerId).observe(viewLifecycleOwner) {
            val recycler = binding.battingRecycler
            recycler.setHasFixedSize(true)
            recycler.adapter = CareerBattingAdapter(it)
        }
    }
}