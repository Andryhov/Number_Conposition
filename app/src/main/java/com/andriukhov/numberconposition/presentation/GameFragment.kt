package com.andriukhov.numberconposition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.andriukhov.numberconposition.R
import com.andriukhov.numberconposition.data.GameRepositoryImpl
import com.andriukhov.numberconposition.databinding.FragmentGameBinding
import com.andriukhov.numberconposition.domain.entity.GameResult
import com.andriukhov.numberconposition.domain.entity.Level

class GameFragment : Fragment() {

    private lateinit var level: Level

    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseLevel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvSumNumber.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(
                    R.id.container, EndGameFragment.newInstance(
                        GameResult(true, 10, 20, GameRepositoryImpl.getGameSettings(level))
                    )
                )
                .addToBackStack(null)
                .commit()
        }
    }

    private fun parseLevel() {
        level = requireArguments().getParcelable<Level>(LEVEL_KEY) as Level
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding == null
    }

    companion object {
        private const val LEVEL_KEY = "level"
        const val NAME = "game_name"

        fun newInstance(level: Level): GameFragment {
            return GameFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(LEVEL_KEY, level)
                }
            }
        }
    }
}