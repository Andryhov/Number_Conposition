package com.andriukhov.numberconposition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.andriukhov.numberconposition.R
import com.andriukhov.numberconposition.databinding.FragmentLevelSelectBinding
import com.andriukhov.numberconposition.domain.entity.Level
import java.lang.RuntimeException

class LevelSelectionFragment: Fragment() {

    private var _binding: FragmentLevelSelectBinding? = null
    private val binding: FragmentLevelSelectBinding
    get() = _binding ?: throw RuntimeException("FragmentLevelSelectBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLevelSelectBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectTestLevel()
        selectEasyLevel()
        selectNormalLevel()
        selectHardLevel()
    }

    private fun selectTestLevel() {
        binding.testLevelButton.setOnClickListener {
            startGameFragment(Level.TEST)
        }
    }

    private fun selectEasyLevel() {
        binding.easyLevelButton.setOnClickListener {
            startGameFragment(Level.EASY)
        }
    }

    private fun selectNormalLevel() {
        binding.normalLevelButton.setOnClickListener {
            startGameFragment(Level.NORMAL)
        }
    }

    private fun selectHardLevel() {
        binding.hardLevelButton.setOnClickListener {
            startGameFragment(Level.HARD)
        }
    }


    private fun startGameFragment(level: Level) {
        requireActivity().supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.container, GameFragment.newInstance(level))
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding == null
    }

    companion object {
        fun newInstance(): LevelSelectionFragment {
            return LevelSelectionFragment()
        }
    }
}