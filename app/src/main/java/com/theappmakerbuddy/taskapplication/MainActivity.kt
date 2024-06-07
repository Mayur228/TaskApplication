package com.theappmakerbuddy.taskapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.theappmakerbuddy.taskapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setUpView()
        observeData()
    }

    private fun setUpView() {
        binding.calculateBTN.setOnClickListener {
            viewModel.calculate(
                binding.numberET1.text.toString(),
                binding.numberET2.text.toString(),
                binding.numberET3.text.toString()
            )
        }
    }

    private fun observeData() {
        viewModel.result.observe(this) {

            binding.resultTV.text = getString(
                R.string.result,
                it.intersectResult,
                it.unionResult,
                it.maxNumberResult
            )
        }

        viewModel.showToast.observe(this) {
            Toast.makeText(this@MainActivity, it, Toast.LENGTH_LONG).show()
        }
    }
}