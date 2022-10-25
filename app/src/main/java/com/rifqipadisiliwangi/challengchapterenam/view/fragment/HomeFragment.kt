package com.rifqipadisiliwangi.challengchapterenam.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.rifqipadisiliwangi.challengchapterenam.R
import com.rifqipadisiliwangi.challengchapterenam.view.adapters.MovieAdapter
import com.rifqipadisiliwangi.challengchapterenam.databinding.FragmentHomeBinding
import com.rifqipadisiliwangi.challengchapterenam.viewmodel.FavoriteViewModel
import com.rifqipadisiliwangi.challengchapterenam.viewmodel.ViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var vidioAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpMovie()
        binding.ivPerson.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }

        binding.ivFav.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_favoriteFragment)
        }
    }

    private fun setUpMovie(){
        val viewModel = ViewModelProvider(requireActivity()).get(ViewModel::class.java)
        viewModel.getliveDataVidio().observe(viewLifecycleOwner, Observer {
            viewModel.loading.observe(viewLifecycleOwner, Observer {
                when(it){
                    true -> binding.homeProgressBar.visibility = View.VISIBLE
                    false -> binding.homeProgressBar.visibility = View.GONE
                }
            })

            if (it != null){
                binding.rvItem.layoutManager = GridLayoutManager(context,2)
                vidioAdapter = MovieAdapter(it.results)
                binding.rvItem.adapter = vidioAdapter


                vidioAdapter.onAddFavorites={
                    binding.homeProgressBar.visibility = View.VISIBLE
                    val favViewModel = ViewModelProvider(requireActivity()).get(FavoriteViewModel::class.java)
                    favViewModel.callPostMovie(it.posterPath, it.originalTitle, it.voteAverage.toString(), it.releaseDate, it.originalLanguage, it.overview)
                    favViewModel.postFavMovie().observe(viewLifecycleOwner, Observer {
                        if(it != null){
                            binding.homeProgressBar.visibility = View.GONE
                            Toast.makeText(requireActivity(), context?.getString(R.string.tambah_film_fav), Toast.LENGTH_SHORT).show()
                        }
                        binding.homeProgressBar.visibility = View.GONE

                    })
                }
            }else {
                Toast.makeText(requireActivity(),"Data Tidak Tampil", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.CallApiVidio()
    }
}