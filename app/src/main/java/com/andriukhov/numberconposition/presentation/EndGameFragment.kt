package com.andriukhov.numberconposition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.andriukhov.numberconposition.databinding.FragmentEndGameBinding
import com.andriukhov.numberconposition.domain.entity.GameResult

class EndGameFragment : Fragment() {

    private var _binding: FragmentEndGameBinding? = null
    private val binding: FragmentEndGameBinding
        get() {
            return _binding ?: throw RuntimeException("FragmentEndGameBinding == null")
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEndGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gameResult = requireArguments().getParcelable<GameResult>(KEY_GAME_RESULT) as GameResult
        binding.tvRequirePercent.text = gameResult.gameSettings.minPercentOfRightAnswers.toString()
        binding.tvRequiredRightAnswer.text = gameResult.gameSettings.minCountOfRightAnswers.toString()
        binding.tvYouScore.text = gameResult.countOfRightAnswers.toString()

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                tryAgain()
            }
        })
        tryAgain()
    }

    private fun tryAgain() {
        binding.bTryAgain.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack(GameFragment.NAME, 1)
        }
    }

    companion object {

        private const val KEY_GAME_RESULT = "result"

        fun newInstance(gameResult: GameResult): EndGameFragment {
            return EndGameFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_GAME_RESULT, gameResult)
                }
            }
        }
    }
}
